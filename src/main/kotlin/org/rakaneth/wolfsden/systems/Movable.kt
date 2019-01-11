package org.rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.rakaneth.wolfsden.commands.MoveTo
import org.rakaneth.wolfsden.extensions.responseWhenCommandIs
import org.rakaneth.wolfsden.extensions.whenHasBlockAt
import org.rakaneth.wolfsden.world.GameContext

object Movable : BaseFacet<GameContext>() {
    override fun executeCommand(command: Command<out EntityType, GameContext>): Response {
        return command.responseWhenCommandIs<MoveTo> { (context, entity, pos) ->
            val world = context.world
            world.whenHasBlockAt(pos) { block ->
                if (!block.isOccupied) {
                    //TODO: interactions
                    world.moveEntity(entity, pos)
                }
            }
            Consumed
        }
    }
}
