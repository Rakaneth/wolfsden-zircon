package org.rakaneth.wolfsden.blocks

import org.hexworks.zircon.api.TileColors
import org.hexworks.zircon.api.color.TileColor

object Swatch {
    val STONE_WALL_BG = TileColors.create(95, 95, 95)
    val STONE_FLOOR_BG = TileColors.create(127, 127, 127)
    val WOOD_WALL_BG = TileColors.create(94, 75, 47)
    val WOOD_FLOOR_BG = TileColors.create(127, 101, 63)

    val FLOOR_FG = TileColors.create(255, 255, 255)
    val WALL_FG = FLOOR_FG
    val STAIRS_DOWN_FG = FLOOR_FG
    val STAIRS_UP_FG = FLOOR_FG
    val STAIRS_BG = TileColors.transparent()
    val PLAYER_FG = FLOOR_FG
    val PLAYER_BG = TileColors.transparent()
    val PLAYER_LABEL_FG = TileColors.create(255, 255, 0)

    fun fromRGBCommaString(rgb: String): TileColor {
        val rawList = rgb.split(",")
        val r = rawList[0].toInt()
        val g = rawList[1].toInt()
        val b = rawList[2].toInt()
        return TileColors.create(r, g, b)
    }
}