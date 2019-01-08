package org.rakaneth.wolfsden.blocks

import org.hexworks.zircon.api.data.BlockSide
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BlockBase

class GameBlock(private var defaultTile: Tile = GameTileRepository.STONE_FLOOR)
    : BlockBase<Tile>() {
    val isFloor: Boolean
        get() = defaultTile == GameTileRepository.STONE_FLOOR || defaultTile == GameTileRepository.WOOD_FLOOR

    val isWall: Boolean
        get() = defaultTile == GameTileRepository.STONE_WALL || defaultTile == GameTileRepository.WOOD_WALL

    override val layers
        get() = mutableListOf(defaultTile)

    override fun fetchSide(side: BlockSide): Tile {
        return GameTileRepository.EMPTY
    }
}