package org.rakaneth.wolfsden.commands

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.impl.Position3D
import org.rakaneth.wolfsden.extensions.GameCommand
import org.rakaneth.wolfsden.extensions.GameEntity
import org.rakaneth.wolfsden.world.GameContext

data class MoveTo(
    override val context: GameContext,
    override val source: GameEntity<EntityType>,
    val position: Position3D
) : GameCommand<EntityType>
