package org.rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.rakaneth.wolfsden.commands.CameraMoveDirection.*
import org.rakaneth.wolfsden.commands.MoveCamera
import org.rakaneth.wolfsden.extensions.GameCommand
import org.rakaneth.wolfsden.extensions.position
import org.rakaneth.wolfsden.extensions.responseWhenCommandIs
import org.rakaneth.wolfsden.world.GameContext

object CameraMover : BaseFacet<GameContext>() {
    override fun executeCommand(command: GameCommand<out EntityType>) =
        command.responseWhenCommandIs<MoveCamera> { cmd ->
            val (world, _, _, player) = cmd.context
            val screenPos = player.position - world.visibleOffset()
            val halfHeight = world.visibleSize().yLength / 2
            val halfWidth = world.visibleSize().xLength / 2
            val checkN = {
                if (screenPos.y < halfHeight) {
                    world.scrollOneBackward()
                }
            }
            val checkS = {
                if (screenPos.y > halfHeight) {
                    world.scrollOneForward()
                }
            }
            val checkE = {
                if (screenPos.x > halfWidth) {
                    world.scrollOneRight()
                }
            }
            val checkW = {
                if (screenPos.x < halfWidth) {
                    world.scrollOneLeft()
                }
            }

            when (cmd.cameraMoveDirection) {
                N -> checkN()
                NE -> {
                    checkN()
                    checkE()
                }
                E -> checkE()
                SE -> {
                    checkS()
                    checkE()
                }
                S -> checkS()
                SW -> {
                    checkS()
                    checkW()
                }
                W -> checkW()
                NW -> {
                    checkN()
                    checkW()
                }
            }
            Consumed
        }
}