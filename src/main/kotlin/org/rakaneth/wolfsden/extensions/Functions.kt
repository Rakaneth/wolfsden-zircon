package org.rakaneth.wolfsden.extensions

import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.internal.Zircon
import org.rakaneth.wolfsden.events.GameLogEvent
import org.rakaneth.wolfsden.world.GameContext

fun <T: EntityType> newGameEntityOfType(type: T, builder: EntityBuilder<T, GameContext>.() -> Unit) {
    return newGameEntityOfType(type, builder)
}

fun logGameEvent(text: String) { Zircon.eventBus.publish(GameLogEvent(text))}