package org.rakaneth.wolfsden.data

data class CreatureTemplate(
    override val id: String = "No id",
    val name: String = "No name",
    val desc: String = "No desc",
    val type: String = "No type",
    val unarmed: String = "hands",
    val capacity: Int = 2,
    val glyph: Char = 0.toChar(),
    val color: String = "0,0,0",
    val stats: CreatureTemplateStats,
    val tags: List<String> = listOf(),
    val enemies: List<String> = listOf(),
    val allies: List<String> = listOf(),
    override val rarity: Int = 0
) : BaseTemplate