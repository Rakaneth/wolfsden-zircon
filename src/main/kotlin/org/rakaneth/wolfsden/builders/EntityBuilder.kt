package org.rakaneth.wolfsden.builders

import org.rakaneth.wolfsden.GameConfig
import org.rakaneth.wolfsden.attributes.CreatureStats
import org.rakaneth.wolfsden.attributes.EntityID
import org.rakaneth.wolfsden.attributes.EntityPosition
import org.rakaneth.wolfsden.attributes.EntityTile
import org.rakaneth.wolfsden.attributes.flags.BlockOccupier
import org.rakaneth.wolfsden.attributes.types.Creature
import org.rakaneth.wolfsden.attributes.types.Player
import org.rakaneth.wolfsden.blocks.GameTileRepository
import org.rakaneth.wolfsden.data.CreatureTemplate
import org.rakaneth.wolfsden.data.EquipTemplate
import org.rakaneth.wolfsden.data.MaterialTemplate
import org.rakaneth.wolfsden.data.buildTemplateRepository
import org.rakaneth.wolfsden.extensions.newGameEntityOfType
import org.rakaneth.wolfsden.systems.CameraMover
import org.rakaneth.wolfsden.systems.Movable
import org.rakaneth.wolfsden.systems.PlayerInputHandler
import org.rakaneth.wolfsden.systems.StairUser
import java.lang.IllegalArgumentException

object EntityBuilder {
    private val rng = GameConfig.RNG
    private const val CREATURE_FILE = "creatures.yml"
    private const val EQUIP_FILE = "equip.yml"
    private const val MAT_FILE = "materials.yml"
    private val creatureTemplates = buildTemplateRepository<CreatureTemplate>(CREATURE_FILE)
    private val equipTemplates = buildTemplateRepository<EquipTemplate>(EQUIP_FILE)
    private val matTemplates = buildTemplateRepository<MaterialTemplate>(MAT_FILE)

    fun newPlayer(raceID: String, name: String) = newGameEntityOfType(Player) {
        val template = creatureTemplates[raceID] ?: throw IllegalArgumentException("$raceID is not a valid race.")
        attributes(
            BlockOccupier,
            EntityPosition(),
            EntityTile(GameTileRepository.PLAYER),
            EntityID.create(name, template.desc),
            CreatureStats.fromStatTemplate(template.stats)
        )
        facets(
            Movable,
            StairUser,
            CameraMover
        )
        behaviors(
            PlayerInputHandler
        )
    }

    fun newCreature(buildID: String, name: String? = null) = newGameEntityOfType(Creature) {
        val template = creatureTemplates[buildID] ?: throw IllegalArgumentException("Build id $buildID not present in templates")
        attributes(
            BlockOccupier,
            EntityPosition(),
            EntityTile(GameTileRepository.tileFrom(template.glyph, template.color)),
            EntityID.create(name ?: template.name, template.desc),
            CreatureStats.fromStatTemplate(template.stats)
        )
        facets(
            Movable,
            StairUser
        )
    }

    fun testTemplates() {
        creatureTemplates.forEach { k, v ->
            println("$k: $v")
        }
        matTemplates.forEach { k, v ->
            println("$k: $v")
        }
        equipTemplates.forEach { k, v ->
            println("$k: $v")
        }
    }
}