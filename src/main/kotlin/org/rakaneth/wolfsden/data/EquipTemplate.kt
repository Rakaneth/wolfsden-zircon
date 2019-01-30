package org.rakaneth.wolfsden.data

data class EquipTemplate(
    val slot: Slot = Slot.TRINKET,
    val stats: EquipTemplateStats = EquipTemplateStats(),
    val material: Boolean = false,
    val equipType: EquipType = EquipType.TRINKET,
    val damageType: DamageType = DamageType.NONE
) : BaseTemplate()