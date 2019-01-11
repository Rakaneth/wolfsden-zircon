package org.rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.rakaneth.wolfsden.commands.UseStairs
import org.rakaneth.wolfsden.extensions.*
import org.rakaneth.wolfsden.world.GameContext

object StairUser: BaseFacet<GameContext>() {
    override fun executeCommand(command: Command<out EntityType, GameContext>): Response {
        return command.responseWhenCommandIs<UseStairs> { (context, entity, position) ->
            val world = context.world
            world.withBlockAt(position) { block ->
                when {
                    block.hasUpStair -> {
                        world.moveEntity(entity, position.withRelativeZ((-1)))
                        entity.whenIsPlayer {
                            world.scrollOneDown()
                        }
                    }
                    block.hasDownStair -> {
                        world.moveEntity(entity, position.withRelativeZ(1))
                        entity.whenIsPlayer {
                            world.scrollOneUp()
                        }
                    }
                    else -> entity.whenIsPlayer {
                        logGameEvent("No stairs here.")
                    }
                }
            }
            Consumed
        }
    }
}