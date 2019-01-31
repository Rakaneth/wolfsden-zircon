package org.rakaneth.wolfsden.builders

import org.hexworks.zircon.api.data.impl.Size3D
import org.rakaneth.wolfsden.GameConfig
import org.rakaneth.wolfsden.world.Game

class GameBuilder(val worldSize: Size3D) {
    private val worldBuilder = WorldBuilder(worldSize)
    private val visibleSize = GameConfig.VIEWPORT
    val world = worldBuilder.createCaveComplex(visibleSize)

    fun buildGame(): Game {
        val player = EntityBuilder.newPlayer("wolfborn", "Braw")
        val wolf = EntityBuilder.newCreature("wolf")
        val oakStaff = EntityBuilder.newEquip("staff", "oak")
        val startPos = world.randomFloor(0)
        val wolfStart = world.randomFloorWithin(startPos, 3)
        val oakStart = world.randomFloorWithin(startPos, 3)
        world.addEntity(player, startPos)
        world.addEntity(wolf, wolfStart)
        world.addEntity(oakStaff, oakStart)
        world.centerOn(player)
        return Game(world, player)
    }


}