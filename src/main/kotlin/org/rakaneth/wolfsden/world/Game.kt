package org.rakaneth.wolfsden.world

import org.hexworks.zircon.api.data.impl.Size3D
import org.rakaneth.wolfsden.GameConfig

class Game(val world: World) {
    companion object {
        fun create(worldSize: Size3D = GameConfig.WORLD_SIZE,
                   visibleSize: Size3D = GameConfig.VIEWPORT): Game {
            return Game(WorldBuilder(worldSize).createCaveComplex(visibleSize))
        }
    }
}