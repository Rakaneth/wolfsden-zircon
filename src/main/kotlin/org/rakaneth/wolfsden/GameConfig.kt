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
    fun buildAppConfig() = AppConfigs.newConfig()
        .enableBetaFeatures()
        .withDefaultTileset(TILESET)
        .withSize(Sizes.create(WINDOW_WIDTH, WINDOW_HEIGHT))
        .build()
}