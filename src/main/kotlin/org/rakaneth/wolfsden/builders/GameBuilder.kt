package org.rakaneth.wolfsden.builders

import org.hexworks.zircon.api.data.impl.Size3D
import org.rakaneth.wolfsden.GameConfig
import org.rakaneth.wolfsden.attributes.types.Player
import org.rakaneth.wolfsden.extensions.GameEntity
import org.rakaneth.wolfsden.world.Game

class GameBuilder(val worldSize: Size3D) {
    private val worldBuilder =  WorldBuilder(worldSize)
    private val visibleSize = GameConfig.VIEWPORT
    val world = worldBuilder.createCaveComplex(visibleSize)

    fun buildGame(): Game {
        val player = makePlayer()
        val startPos = world.randomFloor(0)
        world.addEntity(player, startPos)
        world.centerOn(player)
        return Game(world, player)
    }

    private fun makePlayer(): GameEntity<Player> = EntityBuilder.newPlayer()

}