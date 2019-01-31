package org.rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.impl.Position3D
import org.rakaneth.wolfsden.attributes.Inventory
import org.rakaneth.wolfsden.commands.PickUp
import org.rakaneth.wolfsden.commands.PutDown
import org.rakaneth.wolfsden.extensions.*
import org.rakaneth.wolfsden.world.GameContext

object Carrier : BaseFacet<GameContext>() {
    override fun executeCommand(command: GameCommand<out EntityType>): Response {
        when (command) {
            is PickUp -> {
                command.source.whenHasAttribute<Inventory> {
                    if (it.bagsFull) {
                        command.source.whenIsPlayer {
                            logGameEvent("Bags full: cannot pick up ${command.source.dispName}")
                        }
                    } else {
                        command.item.position = Position3D.unknown()
                        it.inventory.add(command.item)
                    }
                }
            }
            is PutDown -> {
                command.source.whenHasAttribute<Inventory> {
                    command.source.whenIsPlayer {
                        logGameEvent("You drop the ${command.item.dispName}.")
                    }
                    it.inventory.remove(command.item)
                    command.item.position = command.source.position
                }

            }
            else -> {
            }
        }
        return Consumed
    }
}