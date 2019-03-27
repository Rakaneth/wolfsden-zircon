package org.rakaneth.wolfsden.view

import org.hexworks.cobalt.events.api.subscribe
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.GameComponents
import org.hexworks.zircon.api.Sizes
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.extensions.onKeyboardEvent
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.api.mvc.base.BaseView
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.internal.Zircon
import org.rakaneth.wolfsden.GameConfig
import org.rakaneth.wolfsden.blocks.GameBlock
import org.rakaneth.wolfsden.builders.EntityBuilder
import org.rakaneth.wolfsden.events.GameLogEvent
import org.rakaneth.wolfsden.extensions.desc
import org.rakaneth.wolfsden.extensions.dispName
import org.rakaneth.wolfsden.view.fragment.StatBlock
import org.rakaneth.wolfsden.world.Game

class PlayView(private val game: Game) : BaseView() {
    override val theme = GameConfig.THEME
    override fun onDock() {
        EntityBuilder.testTemplates()
        var counter = 0
        val statPanel = Components.panel()
            .withSize(GameConfig.STAT_W, GameConfig.STAT_H)
            .withTitle("Stats")
            .wrapWithBox()
            .withAlignmentWithin(screen, ComponentAlignment.TOP_RIGHT)
            .build().apply {
                val nameLbl = Components.label()
                    .withSize(this.width - 2, 1)
                    .withAlignmentWithin(this, ComponentAlignment.TOP_LEFT)
                    .build().apply {
                        textProperty.bind(game.player.dispName)
                        counter += height
                    }
                val descLbl = Components.label()
                    .withSize(this.width - 2, 1)
                    .withAlignmentWithin(this, ComponentAlignment.TOP_LEFT)
                    .build().apply {
                        textProperty.bind(game.player.desc)
                        moveDownBy(counter)
                        counter += height
                    }
                val playerStats = StatBlock(game.player).apply {
                    root.moveDownBy(counter)
                    counter += height
                }
                addComponent(nameLbl)
                addComponent(descLbl)
                addFragment(playerStats)
            }
        screen.addComponent(statPanel)

        val infoPanel = Components.panel()
            .withSize(GameConfig.INFO_W, GameConfig.INFO_H)
            .withTitle("Info")
            .wrapWithBox()
            .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_RIGHT)
            .build()
        screen.addComponent(infoPanel)

        val skillPanel = Components.panel()
            .withTitle("Skills")
            .withSize(GameConfig.SKL_W, GameConfig.SKL_H)
            .wrapWithBox()
            .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_CENTER)
            .build()
        screen.addComponent(skillPanel)

        val msgPanel = Components.panel()
            .withTitle("Messages")
            .wrapWithBox()
            .withSize(GameConfig.MSG_W, GameConfig.MSG_H)
            .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_LEFT)
            .build().also {
                val logArea = Components.logArea()
                    .withSize(it.size - Sizes.create(2, 2))
                    .build()
                it.addComponent(logArea)
                Zircon.eventBus.subscribe<GameLogEvent> { (text) ->
                    logArea.addParagraph(text, false)
                }
            }
        screen.addComponent(msgPanel)

        val gameComponent = GameComponents.newGameComponentBuilder<Tile, GameBlock>()
            .withGameArea(game.world)
            .withVisibleSize(game.world.visibleSize())
            .withProjectionMode(ProjectionMode.TOP_DOWN)
            .withAlignmentWithin(screen, ComponentAlignment.TOP_LEFT)
            .build()
        screen.addComponent(gameComponent)

        screen.onKeyboardEvent(KeyboardEventType.KEY_PRESSED) { ev, _ ->
            game.world.update(screen, ev, game)
            Processed
        }
    }
}