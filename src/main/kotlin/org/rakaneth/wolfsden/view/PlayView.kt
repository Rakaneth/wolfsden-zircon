package org.rakaneth.wolfsden.view

import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.mvc.base.BaseView
import org.rakaneth.wolfsden.GameConfig

class PlayView: BaseView() {
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
    }
}