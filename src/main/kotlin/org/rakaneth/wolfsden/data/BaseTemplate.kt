package org.rakaneth.wolfsden.data

abstract class BaseTemplate(
    val id: String = "No id",
    val rarity: Int = 0,
    val glyph: Char = '@',
    val color: String = "0,0,0",
    val name: String = "No name",
    val type: String = "No type",
    val desc: String = "No desc",
    val tags: List<String> = listOf()
)
