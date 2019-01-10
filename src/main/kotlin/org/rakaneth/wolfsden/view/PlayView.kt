package org.rakaneth.wolfsden.view

import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.GameComponents
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.api.mvc.base.BaseView
import org.rakaneth.wolfsden.GameConfig
import org.rakaneth.wolfsden.blocks.GameBlock
import org.rakaneth.wolfsden.world.Game

class PlayView(private val game: Game = Game.create()): BaseView() {
    override val theme = GameConfig.THEME
    override fun onDock() {
        val statPanel = Components.panel()
            .withSize(GameConfig.STAT_W, GameConfig.STAT_H)
            .withTitle("Stats")
            .wrapWithBox()
            .withAlignmentWithin(screen, ComponentAlignment.TOP_RIGHT)
            .build()
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

        val msgPanel = Components.logArea()
            .withTitle("Messages")
            .wrapWithBox()
            .withSize(GameConfig.MSG_W, GameConfig.MSG_H)
            .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_LEFT)
            .build()
        screen.addComponent(msgPanel)

        val gameComponent = GameComponents.newGameComponentBuilder<Tile, GameBlock>()
            .withGameArea(game.world)
            .withVisibleSize(game.world.visibleSize())
            .withProjectionMode(ProjectionMode.TOP_DOWN)
            .withAlignmentWithin(screen, ComponentAlignment.TOP_LEFT)
            .build()
        screen.addComponent(gameComponent)
    }
}