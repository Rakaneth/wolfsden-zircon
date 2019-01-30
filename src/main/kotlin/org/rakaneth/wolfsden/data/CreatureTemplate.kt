package org.rakaneth.wolfsden.data

data class CreatureTemplate(
    val unarmed: String = "hands",
    val capacity: Int = 2,
    val stats: CreatureTemplateStats = CreatureTemplateStats(),
    val enemies: List<String> = listOf(),
    val allies: List<String> = listOf(),
    val startItems: List<String> = listOf(),
    val info: String = "No info"
) : BaseTemplate()