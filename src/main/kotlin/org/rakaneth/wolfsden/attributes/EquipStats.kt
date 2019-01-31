package org.rakaneth.wolfsden.attributes

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.property.Property
import org.rakaneth.wolfsden.data.EquipTemplate
import org.rakaneth.wolfsden.data.EquipTemplateStats
import org.rakaneth.wolfsden.data.Slot

data class EquipStats(
    val slotProp: Property<Slot>,
    val atpProp: Property<Int>,
    val dfpProp: Property<Int>,
    val touProp: Property<Int>,
    val resProp: Property<Int>,
    val dmgProp: Property<Int>,
    val wilProp: Property<Int>,
    val pwrProp: Property<Int>
) : Attribute {
    val atp: Int by atpProp.asDelegate()
    val dfp: Int by dfpProp.asDelegate()
    val tou: Int by touProp.asDelegate()
    val res: Int by resProp.asDelegate()
    val dmg: Int by dmgProp.asDelegate()
    val wil: Int by wilProp.asDelegate()
    val pwr: Int by pwrProp.asDelegate()
    val slot: Slot by slotProp.asDelegate()

    fun applyMatTemplate(mat: EquipTemplateStats) {
        atpProp.value += mat.atp
        dfpProp.value += mat.dfp
        touProp.value += mat.tou
        resProp.value += mat.res
        dmgProp.value += mat.dmg
        wilProp.value += mat.wil
        pwrProp.value += mat.pwr
    }

    companion object {
        fun create(
            atp: Int = 0,
            dfp: Int = 0,
            tou: Int = 0,
            res: Int = 0,
            dmg: Int = 0,
            wil: Int = 0,
            pwr: Int = 0,
            slot: Slot = Slot.TRINKET
        ) = EquipStats(
            atpProp = createPropertyFrom(atp),
            dfpProp = createPropertyFrom(dfp),
            touProp = createPropertyFrom(tou),
            resProp = createPropertyFrom(res),
            dmgProp = createPropertyFrom(dmg),
            wilProp = createPropertyFrom(wil),
            pwrProp = createPropertyFrom(pwr),
            slotProp = createPropertyFrom(slot)
        )

        fun fromEquipTemplate(temp: EquipTemplate) = create(
            atp = temp.stats.atp,
            dfp = temp.stats.dfp,
            tou = temp.stats.tou,
            res = temp.stats.res,
            dmg = temp.stats.dmg,
            wil = temp.stats.wil,
            pwr = temp.stats.pwr,
            slot = temp.slot
        )
    }
}