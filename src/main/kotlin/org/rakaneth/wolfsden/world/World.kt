package org.rakaneth.wolfsden.world

import org.hexworks.amethyst.api.Engines
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.Identifier
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.zircon.api.builder.game.GameAreaBuilder
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.impl.Position3D
import org.hexworks.zircon.api.data.impl.Size3D
import org.hexworks.zircon.api.game.GameArea
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.KeyboardEvent
import org.rakaneth.wolfsden.GameConfig
import org.rakaneth.wolfsden.blocks.GameBlock
import org.rakaneth.wolfsden.blocks.GameBlockFactory
import org.rakaneth.wolfsden.extensions.*
import squidpony.squidmath.GreasedRegion

class World(
    startingBlocks: Map<Position3D, GameBlock>,
    visibleSize: Size3D,
    actualSize: Size3D,
    private val rawMap: Array<Array<CharArray>>
) : GameArea<Tile, GameBlock> by GameAreaBuilder.newBuilder<Tile, GameBlock>()
    .withVisibleSize(visibleSize)
    .withActualSize(actualSize)
    .withDefaultBlock(DEFAULT_BLOCK)
    .withLayersPerBlock(2)
    .build() {

    private val engine = Engines.newEngine<GameContext>()
    private val entityPosLookup = mutableMapOf<Identifier, Position3D>()

    init {
        startingBlocks.forEach { pos, block ->
            setBlockAt(pos, block)
            block.entities.forEach {
                entityPosLookup[it.id] = pos
                engine.addEntity(it)
                it.position = pos
            }
        }
    }

    private fun floors(z: Int) = GreasedRegion(rawMap[z], '.')

    fun moveEntity(entity: GameEntity<EntityType>, pos: Position3D): Boolean {
        return if (actualSize().containsPosition(pos) && pos.x > 0 && pos.y > 0) {
            entityPosLookup.remove(entity.id)?.let { oldPos ->
                fetchBlockAt(oldPos).map { oldBlock ->
                    oldBlock.removeEntity(entity)
                }
                fetchBlockAt(pos).map { newBlock ->
                    newBlock.addEntity(entity)
                }
                entityPosLookup[entity.id] = pos
                entity.position = pos
                true
            } ?: false
        } else {
            false
        }
    }

    fun addEntity(entity: AnyGameEntity, pos: Position3D) {
        engine.addEntity(entity)
        entityPosLookup[entity.id] = pos
        fetchBlockAt(pos).map {
            it.addEntity(entity)
        }
        entity.position = pos
    }

    fun removeEntity(entity: AnyGameEntity) {
        engine.removeEntity(entity)
        entity.position = Position3D.unknown()
        entityPosLookup.remove(entity.id)?.let { psn ->
            fetchBlockAt(psn).map { it.removeEntity(entity) }
        }
    }

    fun update(screen: Screen, input: KeyboardEvent, game: Game) {
        engine.update(
            GameContext(
                world = this,
                screen = screen,
                input = input,
                player = game.player
            )
        )
    }

    fun randomFloor(z: Int): Position3D = floors(z)
        .singleRandom(GameConfig.RNG)
        .toPos3D(z)


    fun randomFloorWithin(pos: Position3D, radius: Int): Position3D {
        return GreasedRegion(actualSize().xLength, actualSize().yLength)
            .insert(pos.toCoord())
            .flood(floors(pos.z), radius)
            .singleRandom(GameConfig.RNG)
            .toPos3D(pos.z)
    }

    fun centerOn(entity: AnyGameEntity) {
        val cam = { pt: Int, s: Int, m: Int -> clamp(pt - s / 2, 0, Math.max(0, m - s)) }
        val (x, y) =
            cam(entity.position.x, GameConfig.MAP_W, actualSize().xLength) to
                    cam(entity.position.y, GameConfig.MAP_H, actualSize().yLength)
        scrollForwardBy(y)
        scrollRightBy(x)
    }

    fun withBlockAt(pos: Position3D, fn: (GameBlock) -> Unit) {
        fetchBlockAt(pos).map(fn)
    }


    companion object {
        private val DEFAULT_BLOCK = GameBlockFactory.stoneFloor()
    }
}