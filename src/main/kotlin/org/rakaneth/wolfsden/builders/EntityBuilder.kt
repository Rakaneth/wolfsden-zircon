package org.rakaneth.wolfsden.builders

import org.rakaneth.wolfsden.GameConfig
import org.rakaneth.wolfsden.attributes.*
import org.rakaneth.wolfsden.attributes.flags.BlockOccupier
import org.rakaneth.wolfsden.attributes.types.Creature
import org.rakaneth.wolfsden.attributes.types.Equipment
import org.rakaneth.wolfsden.attributes.types.Player
import org.rakaneth.wolfsden.blocks.GameTileRepository
import org.rakaneth.wolfsden.data.*
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

    fun newEquip(buildID: String, matID: String? = null) = newGameEntityOfType(Equipment) {
        val eqTemplate = equipTemplates[buildID]
            ?: throw IllegalArgumentException("Build id $buildID not present in equipment templates")
        val baseStats = EquipStats.fromStatTemplate(eqTemplate.stats)
        attributes(EntityPosition())
        if (matID == null) {
            if (eqTemplate.material)
                throw IllegalArgumentException("Equipment $buildID must have a material type")
            attributes(baseStats,
                       EntityTile(GameTileRepository.tileFrom(eqTemplate.glyph, eqTemplate.color)),
                       EntityID.create(eqTemplate.name, eqTemplate.desc))
        } else {
            val mat = matTemplates[matID]
                ?: throw IllegalArgumentException("Mat id $matID not present in material templates")
            val statSet = when (eqTemplate.equipType) {
                EquipType.SWORD -> mat.sword
                EquipType.AXE -> mat.axe
                EquipType.ARMOR -> mat.armor
                EquipType.RAPIER -> mat.rapier
                EquipType.STAFF -> mat.staff
                else -> EquipTemplateStats()
            }
            baseStats.applyMatTemplate(statSet)
            val newDesc = eqTemplate.desc.replace("<material>", mat.name)
            val newName = "${mat.name} ${eqTemplate.name}"
            attributes(baseStats,
                       EntityTile(GameTileRepository.tileFrom(eqTemplate.glyph, mat.color)),
                       EntityID.create(newName, newDesc))
        }
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