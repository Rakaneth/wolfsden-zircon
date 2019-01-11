package org.rakaneth.wolfsden.blocks

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.BlockSide
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BlockBase
import org.rakaneth.wolfsden.extensions.GameEntity
import org.rakaneth.wolfsden.extensions.occupiesBlock
import org.rakaneth.wolfsden.extensions.tile

class GameBlock(private var defaultTile: Tile = GameTileRepository.STONE_FLOOR,
                private val currentEntities: MutableList<GameEntity<EntityType>> = mutableListOf())
    : BlockBase<Tile>() {
    val isFloor: Boolean
        get() = defaultTile == GameTileRepository.STONE_FLOOR || defaultTile == GameTileRepository.WOOD_FLOOR

    val isWall: Boolean
        get() = defaultTile == GameTileRepository.STONE_WALL || defaultTile == GameTileRepository.WOOD_WALL

    val isOccupied: Boolean
        get() = currentEntities.any { it.occupiesBlock }

    val entities: Iterable<GameEntity<EntityType>>
        get() = currentEntities.toList()

    fun addEntity(entity: GameEntity<EntityType>) {
        currentEntities.add(entity)
    }

    fun removeEntity(entity: GameEntity<EntityType>) {
        currentEntities.remove(entity)
    }

    override val layers
        get() = mutableListOf(defaultTile, currentEntities.map {
            it.tile
        }.lastOrNull() ?: GameTileRepository.EMPTY)

    override fun fetchSide(side: BlockSide): Tile {
        return GameTileRepository.EMPTY
    }
}