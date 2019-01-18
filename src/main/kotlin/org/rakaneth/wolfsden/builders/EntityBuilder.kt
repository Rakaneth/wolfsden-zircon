package org.rakaneth.wolfsden.builders

import org.hexworks.cobalt.databinding.api.property.Property
import org.rakaneth.wolfsden.GameConfig
import org.rakaneth.wolfsden.attributes.*
import org.rakaneth.wolfsden.attributes.flags.BlockOccupier
import org.rakaneth.wolfsden.attributes.types.*
import org.rakaneth.wolfsden.blocks.GameTileRepository
import org.rakaneth.wolfsden.extensions.GameEntity
import org.rakaneth.wolfsden.extensions.newGameEntityOfType
import org.rakaneth.wolfsden.systems.*

object EntityBuilder {
    private val rng = GameConfig.RNG
    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(
            BlockOccupier,
            EntityPosition(),
            EntityTile(GameTileRepository.PLAYER),
            EntityID.create("Player", "The player"),
            CreatureStats.create())
        behaviors(PlayerInputHandler)
        facets(
            CameraMover,
            Movable,
            StairUser)
    }
}