package org.rakaneth.wolfsden.world

import org.hexworks.zircon.api.data.impl.Position3D
import org.hexworks.zircon.api.data.impl.Size3D
import org.rakaneth.wolfsden.GameConfig
import org.rakaneth.wolfsden.blocks.GameBlock
import org.rakaneth.wolfsden.blocks.GameBlockFactory
import squidpony.squidgrid.mapping.SerpentDeepMapGenerator


class WorldBuilder (private val worldSize: Size3D){
    private val width = worldSize.xLength
    private val height = worldSize.yLength
    private val depth = worldSize.zLength
    private var blocks: MutableMap<Position3D, GameBlock> = mutableMapOf()

    private fun createRaw(caveCarvers: Int = 1, roundCarvers: Int = 0, boxCarvers: Int = 0): Array<Array<CharArray>> {
        val gen = SerpentDeepMapGenerator(width, height, depth, GameConfig.RNG)
        gen.putCaveCarvers(caveCarvers)
        gen.putRoundRoomCarvers(roundCarvers)
        gen.putBoxRoomCarvers(boxCarvers)
        return gen.generate()
    }

    fun createCaveComplex(visibleSize: Size3D): World {
        val raw = createRaw(caveCarvers=1, roundCarvers=2, boxCarvers=3)
        for ((z, m) in raw.withIndex()) {
            for ((x, c) in m.withIndex()) {
                for ((y, r) in c.withIndex()) {
                    blocks[Position3D.create(x, y, z)] = when(r) {
                        '#' -> GameBlockFactory.stoneWall()
                        '.' -> GameBlockFactory.stoneFloor()
                        '>' -> GameBlockFactory.stairsDown()
                        '<' -> GameBlockFactory.stairsUp()
                        else -> GameBlockFactory.stoneFloor()
                    }
                }
            }
        }
        return World(blocks, visibleSize, worldSize)
    }
}