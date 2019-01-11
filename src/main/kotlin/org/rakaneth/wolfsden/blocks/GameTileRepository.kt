package org.rakaneth.wolfsden.blocks

import org.hexworks.zircon.api.Tiles
import org.hexworks.zircon.api.color.TileColor
import org.hexworks.zircon.api.data.CharacterTile
import org.hexworks.zircon.api.graphics.Symbols

object GameTileRepository {
    val EMPTY: CharacterTile =  Tiles.empty()
    val STONE_FLOOR: CharacterTile = Tiles.newBuilder()
        .withCharacter(Symbols.INTERPUNCT)
        .withForegroundColor(Swatch.FLOOR_FG)
        .withBackgroundColor(Swatch.STONE_FLOOR_BG)
        .buildCharacterTile()
    val STONE_WALL: CharacterTile = Tiles.newBuilder()
        .withCharacter('#')
        .withForegroundColor(Swatch.WALL_FG)
        .withBackgroundColor(Swatch.STONE_WALL_BG)
        .buildCharacterTile()
    val WOOD_FLOOR: CharacterTile = Tiles.newBuilder()
        .withCharacter(Symbols.INTERPUNCT)
        .withForegroundColor(Swatch.FLOOR_FG)
        .withBackgroundColor(Swatch.WOOD_FLOOR_BG)
        .buildCharacterTile()
    val WOOD_WALL: CharacterTile = Tiles.newBuilder()
        .withCharacter('#')
        .withForegroundColor(Swatch.WALL_FG)
        .withBackgroundColor(Swatch.WOOD_WALL_BG)
        .buildCharacterTile()
    val STAIRS_DOWN: CharacterTile = Tiles.newBuilder()
        .withCharacter('>')
        .withForegroundColor(Swatch.STAIRS_DOWN_FG)
        .withBackgroundColor(Swatch.STAIRS_BG)
        .buildCharacterTile()
    val STAIRS_UP: CharacterTile = Tiles.newBuilder()
        .withCharacter('<')
        .withForegroundColor(Swatch.STAIRS_UP_FG)
        .withBackgroundColor(Swatch.STAIRS_BG)
        .buildCharacterTile()
    val PLAYER: CharacterTile = Tiles.newBuilder()
        .withCharacter('@')
        .withForegroundColor(Swatch.PLAYER_FG)
        .withBackgroundColor(Swatch.PLAYER_BG)
        .buildCharacterTile()
}