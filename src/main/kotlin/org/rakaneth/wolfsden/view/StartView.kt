package org.rakaneth.wolfsden.view

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.extensions.onComponentEvent
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.mvc.base.BaseView
import org.hexworks.zircon.api.uievent.ComponentEventType
import org.hexworks.zircon.api.uievent.Processed
import org.rakaneth.wolfsden.GameConfig
import org.rakaneth.wolfsden.builders.GameBuilder

class StartView : BaseView() {

    override val theme = ColorThemes.arc()
    override fun onDock() {
        val msg = "Welcome to Wolf's Den II"
        val header = Components.textBox()
            .withContentWidth(msg.length)
            .addHeader(msg)
            .addNewLine()
            .withAlignmentWithin(screen, ComponentAlignment.CENTER)
            .build()
        val startButton = Components.button()
            .withAlignmentAround(header, ComponentAlignment.BOTTOM_CENTER)
            .withText("Start!")
            .wrapSides(false)
            .withBoxType(BoxType.SINGLE)
            .wrapWithBox()
            .build()
        startButton.onComponentEvent(ComponentEventType.ACTIVATED) {
            val game = GameBuilder(GameConfig.WORLD_SIZE).buildGame()
            replaceWith(PlayView(game))
            close()
            Processed
        }
        screen.addComponent(header)
        screen.addComponent(startButton)
    }


}