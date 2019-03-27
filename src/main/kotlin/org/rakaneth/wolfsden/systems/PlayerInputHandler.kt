package org.rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.rakaneth.wolfsden.commands.CameraMoveDirection
import org.rakaneth.wolfsden.commands.MoveCamera
import org.rakaneth.wolfsden.commands.MoveTo
import org.rakaneth.wolfsden.commands.UseStairs
import org.rakaneth.wolfsden.extensions.GameEntity
import org.rakaneth.wolfsden.extensions.position
import org.rakaneth.wolfsden.world.GameContext

object PlayerInputHandler : BaseBehavior<GameContext>() {
    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val (world, screen, input, player) = context
        val curPos = player.position
        if (input is KeyboardEvent) {
            val (newPos, direction) = when (input.code) {
                KeyCode.NUMPAD_8, KeyCode.UP -> curPos.withRelativeY(-1) to CameraMoveDirection.N
                KeyCode.NUMPAD_9 -> curPos.withRelativeY(-1).withRelativeX(1) to CameraMoveDirection.NE
                KeyCode.NUMPAD_6, KeyCode.RIGHT -> curPos.withRelativeX(1) to CameraMoveDirection.E
                KeyCode.NUMPAD_3 -> curPos.withRelativeX(1).withRelativeY(1) to CameraMoveDirection.SE
                KeyCode.NUMPAD_2, KeyCode.DOWN -> curPos.withRelativeY(1) to CameraMoveDirection.S
                KeyCode.NUMPAD_1 -> curPos.withRelativeX(-1).withRelativeY(1) to CameraMoveDirection.SW
                KeyCode.NUMPAD_4, KeyCode.LEFT -> curPos.withRelativeX(-1) to CameraMoveDirection.W
                KeyCode.NUMPAD_7 -> curPos.withRelativeX(-1).withRelativeY(-1) to CameraMoveDirection.NW
                else -> curPos to null
            }
            direction?.let {
                player.executeCommand(MoveTo(context, player, newPos))
                player.executeCommand(
                    MoveCamera(
                        context = context,
                        source = player,
                        oldPos = curPos,
                        newPos = newPos,
                        cameraMoveDirection = it
                    )
                )
            }

        }


        input.whenCharacterIs('>') {
            player.executeCommand(
                UseStairs(
                    context = context,
                    source = player,
                    position = newPos
                )
            )

        }
        input.whenCharacterIs('<') {
            player.executeCommand(
                UseStairs(
                    context = context,
                    source = player,
                    position = curPos
                )
            )
        }

        return true
    }
}