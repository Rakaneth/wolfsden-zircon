package org.rakaneth.wolfsden.extensions

import org.hexworks.amethyst.api.Entities.newEntityOfType
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.internal.Zircon
import org.rakaneth.wolfsden.events.GameLogEvent
import org.rakaneth.wolfsden.world.GameContext

fun <T: EntityType> newGameEntityOfType(type: T, builder: EntityBuilder<T, GameContext>.() -> Unit) =
    newEntityOfType(type, builder)


fun logGameEvent(text: String) { Zircon.eventBus.publish(GameLogEvent(text))}

fun <T: Comparable<T>> clamp(value: T, low: T, high: T): T {
    return when {
        value.compareTo(low) == -1 -> low
        value.compareTo(high) == 1 -> high
        else -> value
    }
}

fun <T: Comparable<T>> between(value: T, low: T, high: T): Boolean = clamp(value, low, high) == value