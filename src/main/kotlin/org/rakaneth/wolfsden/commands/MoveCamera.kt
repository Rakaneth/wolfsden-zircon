package org.rakaneth.wolfsden.commands

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.impl.Position3D
import org.rakaneth.wolfsden.extensions.GameCommand
import org.rakaneth.wolfsden.extensions.GameEntity
import org.rakaneth.wolfsden.world.GameContext

data class MoveCamera(override val context: GameContext,
                      override val source: GameEntity<EntityType>,
                      val oldPos: Position3D,
                      val newPos: Position3D,
                      val cameraMoveDirection: CameraMoveDirection): GameCommand<EntityType>