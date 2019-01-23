package org.rakaneth.wolfsden.world

import org.rakaneth.wolfsden.attributes.types.Player
import org.rakaneth.wolfsden.extensions.GameEntity

class Game(
    var world: World,
    val player: GameEntity<Player>
)