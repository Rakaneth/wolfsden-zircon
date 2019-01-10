package org.rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.input.InputType
import org.hexworks.zircon.api.kotlin.whenKeyStroke
import org.rakaneth.wolfsden.extensions.GameEntity
import org.rakaneth.wolfsden.extensions.logGameEvent
import org.rakaneth.wolfsden.extensions.position
import org.rakaneth.wolfsden.world.GameContext

object PlayerInputHandler: BaseBehavior<GameContext>() {
    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val (world, screen, input, player) = context
        val curPos = player.position
        input.whenKeyStroke { ks ->

        }
    }
}