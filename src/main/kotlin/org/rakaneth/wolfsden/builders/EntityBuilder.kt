package org.rakaneth.wolfsden.builders

import org.rakaneth.wolfsden.GameConfig
import org.rakaneth.wolfsden.attributes.CreatureStats
import org.rakaneth.wolfsden.attributes.EntityID
import org.rakaneth.wolfsden.attributes.EntityPosition
import org.rakaneth.wolfsden.attributes.EntityTile
import org.rakaneth.wolfsden.attributes.flags.BlockOccupier
import org.rakaneth.wolfsden.attributes.types.Player
import org.rakaneth.wolfsden.blocks.GameTileRepository
import org.rakaneth.wolfsden.data.CreatureTemplate
import org.rakaneth.wolfsden.data.buildTemplateRepository
import org.rakaneth.wolfsden.extensions.newGameEntityOfType
import org.rakaneth.wolfsden.systems.CameraMover
import org.rakaneth.wolfsden.systems.Movable
import org.rakaneth.wolfsden.systems.PlayerInputHandler
import org.rakaneth.wolfsden.systems.StairUser

object EntityBuilder {
    private val rng = GameConfig.RNG
    private const val CREATURE_FILE = "creatures.yml"
    private val creatureTemplates = buildTemplateRepository<CreatureTemplate>(CREATURE_FILE)
    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(
            BlockOccupier,
            EntityPosition(),
            EntityTile(GameTileRepository.PLAYER),
            EntityID.create("Player", "The player"),
            CreatureStats.create()
        )
        behaviors(PlayerInputHandler)
        facets(
            CameraMover,
            Movable,
            StairUser
        )
    }

    fun testTemplates() {
        creatureTemplates.forEach { k, v ->
            println("$k: $v")
        }
    }
}