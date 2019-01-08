package org.rakaneth.wolfsden

import org.hexworks.zircon.api.AppConfigs
import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Sizes

object GameConfig {
    val TILESET = CP437TilesetResources.rogueYun16x16()
    val THEME = ColorThemes.zenburnVanilla()
    const val WINDOW_WIDTH = 90
    const val WINDOW_HEIGHT = 40
    const val MAP_W = 60
    const val MAP_H = 30
    const val MSG_W = 30
    const val MSG_H = 10
    const val SKL_W = 30
    const val SKL_H = 10
    const val INFO_W = 30
    const val INFO_H = 10
    const val STAT_W = 30
    const val STAT_H = 30
    fun buildAppConfig() = AppConfigs.newConfig()
        .enableBetaFeatures()
        .withDefaultTileset(TILESET)
        .withSize(Sizes.create(WINDOW_WIDTH, WINDOW_HEIGHT))
        .build()
}