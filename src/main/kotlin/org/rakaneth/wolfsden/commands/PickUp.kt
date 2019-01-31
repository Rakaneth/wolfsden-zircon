package org.rakaneth.wolfsden.commands

import org.hexworks.amethyst.api.entity.EntityType
import org.rakaneth.wolfsden.attributes.types.Item
import org.rakaneth.wolfsden.extensions.GameCommand
import org.rakaneth.wolfsden.extensions.GameEntity
import org.rakaneth.wolfsden.world.GameContext

data class PickUp(
    override val context: GameContext,
    override val source: GameEntity<EntityType>,
    val item: GameEntity<Item>
) : GameCommand<EntityType>