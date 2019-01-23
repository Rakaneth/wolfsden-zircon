package org.rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.input.InputType
import org.hexworks.zircon.api.kotlin.whenKeyStroke
import org.rakaneth.wolfsden.commands.CameraMoveDirection
import org.rakaneth.wolfsden.commands.MoveCamera
import org.rakaneth.wolfsden.commands.MoveTo
import org.rakaneth.wolfsden.commands.UseStairs
import org.rakaneth.wolfsden.extensions.GameEntity
import org.rakaneth.wolfsden.extensions.position
import org.rakaneth.wolfsden.extensions.whenCharacterIs
import org.rakaneth.wolfsden.world.GameContext

object PlayerInputHandler : BaseBehavior<GameContext>() {
    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val (world, screen, input, player) = context
        val curPos = player.position
        input.whenKeyStroke { ks ->
            val (newPos, direction) = when (ks.inputType()) {
                InputType.Numpad8, InputType.ArrowUp -> curPos.withRelativeY(-1) to CameraMoveDirection.N
                InputType.Numpad9 -> curPos.withRelativeY(-1).withRelativeX(1) to CameraMoveDirection.NE
                InputType.Numpad6, InputType.ArrowRight -> curPos.withRelativeX(1) to CameraMoveDirection.E
                InputType.Numpad3 -> curPos.withRelativeX(1).withRelativeY(1) to CameraMoveDirection.SE
                InputType.Numpad2, InputType.ArrowDown -> curPos.withRelativeY(1) to CameraMoveDirection.S
                InputType.Numpad1 -> curPos.withRelativeX(-1).withRelativeY(1) to CameraMoveDirection.SW
                InputType.Numpad4, InputType.ArrowLeft -> curPos.withRelativeX(-1) to CameraMoveDirection.W
                InputType.Numpad7 -> curPos.withRelativeX(-1).withRelativeY(-1) to CameraMoveDirection.NW
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
        }
        return true
    }
}