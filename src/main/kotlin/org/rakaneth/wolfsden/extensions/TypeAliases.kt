package org.rakaneth.wolfsden.extensions

import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.rakaneth.wolfsden.world.GameContext

typealias AnyGameEntity = Entity<EntityType, GameContext>
typealias GameEntity<T> = Entity<T, GameContext>
typealias GameCommand<T> = Command<T, GameContext>