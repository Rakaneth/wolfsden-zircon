package org.rakaneth.wolfsden.commands

import org.hexworks.amethyst.api.entity.EntityType
import org.rakaneth.wolfsden.attributes.types.Equipment
import org.rakaneth.wolfsden.extensions.GameCommand
import org.rakaneth.wolfsden.extensions.GameEntity
import org.rakaneth.wolfsden.world.GameContext

data class DequipItem(
    override val context: GameContext,
    override val source: GameEntity<EntityType>,
    val item: GameEntity<Equipment>
) : GameCommand<EntityType>