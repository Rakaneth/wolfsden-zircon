package org.rakaneth.wolfsden

import org.hexworks.zircon.api.SwingApplications
import org.rakaneth.wolfsden.view.StartView

fun main(args: Array<String>) {
    val app = SwingApplications.startApplication(GameConfig.buildAppConfig())
    app.dock(StartView())
}


