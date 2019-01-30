package org.rakaneth.wolfsden.data

data class MaterialTemplate(
    val hardness: Int = 5,
    val axe: EquipTemplateStats = EquipTemplateStats(),
    val sword: EquipTemplateStats = EquipTemplateStats(),
    val rapier: EquipTemplateStats = EquipTemplateStats(),
    val armor: EquipTemplateStats = EquipTemplateStats(),
    val hammer: EquipTemplateStats = EquipTemplateStats(),
    val staff: EquipTemplateStats = EquipTemplateStats()
): BaseTemplate(glyph = '\u0000', type = "material")