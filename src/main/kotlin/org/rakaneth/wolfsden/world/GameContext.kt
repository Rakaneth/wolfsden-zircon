package org.rakaneth.wolfsden.world

import org.hexworks.amethyst.api.Context
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent
import org.rakaneth.wolfsden.attributes.types.Player
import org.rakaneth.wolfsden.extensions.GameEntity

data class GameContext(
    val world: World,
    val screen: Screen,
    val input: UIEvent,
    val player: GameEntity<Player>
) : Context