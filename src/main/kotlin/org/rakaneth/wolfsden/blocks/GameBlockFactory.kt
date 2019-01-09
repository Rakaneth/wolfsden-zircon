package org.rakaneth.wolfsden.blocks

object GameBlockFactory {
    fun stoneFloor() = GameBlock(GameTileRepository.STONE_FLOOR)
    fun stoneWall() = GameBlock(GameTileRepository.STONE_WALL)
    fun woodFloor() = GameBlock(GameTileRepository.WOOD_FLOOR)
    fun woodWall() = GameBlock(GameTileRepository.WOOD_WALL)
    fun stairsDown() = GameBlock(GameTileRepository.STAIRS_DOWN)
    fun stairsUp() = GameBlock(GameTileRepository.STAIRS_UP)
}