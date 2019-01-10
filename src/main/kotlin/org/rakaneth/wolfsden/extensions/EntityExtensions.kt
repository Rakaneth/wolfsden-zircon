package org.rakaneth.wolfsden.extensions

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.cobalt.datatypes.extensions.orElseThrow
import org.hexworks.zircon.api.data.Tile
import org.rakaneth.wolfsden.attributes.EntityPosition
import org.rakaneth.wolfsden.attributes.EntityTile

inline fun <reified T: Attribute> AnyGameEntity.attribute(): T = attribute(T::class).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${T::class.simpleName}'.")
}

inline fun <reified T: Attribute> AnyGameEntity.hasAttribute(): Boolean = attribute(T::class).isPresent
inline fun <reified T: Attribute> AnyGameEntity.whenHasAttribute(crossinline fn: (T) -> Unit) {
    attribute(T::class).map(fn)
}

var AnyGameEntity.position
    get() = attribute<EntityPosition>().position
    set(value) {
        attribute(EntityPosition::class).map {
            it.position = value
        }
    }

val AnyGameEntity.tile: Tile
    get() = attribute<EntityTile>().tile