package org.rakaneth.wolfsden.extensions

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.cobalt.datatypes.extensions.orElseThrow
import org.hexworks.zircon.api.data.Tile
import org.rakaneth.wolfsden.attributes.EntityPosition
import org.rakaneth.wolfsden.attributes.EntityTile
import org.rakaneth.wolfsden.attributes.flags.BlockOccupier
import org.rakaneth.wolfsden.attributes.flags.VisionBlocker
import org.rakaneth.wolfsden.attributes.types.Player

inline fun <reified T: Attribute> AnyGameEntity.attribute(): T = attribute(T::class).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${T::class.simpleName}'.")
}

inline fun <reified T: Attribute> AnyGameEntity.hasAttribute(): Boolean = attribute(T::class).isPresent
inline fun <reified T: Attribute> AnyGameEntity.whenHasAttribute(crossinline fn: (T) -> Unit) {
    attribute(T::class).map(fn)
}

@Suppress("UNCHECKED_CAST")
fun AnyGameEntity.whenIsPlayer(fn: (AnyGameEntity) -> Unit) {
    if (this.isPlayer) {
        fn(this as GameEntity<Player>)
    }
}

var AnyGameEntity.position
    get() = attribute<EntityPosition>().position
    set(value) {
        attribute(EntityPosition::class).map {
            it.position = value
        }
    }

val AnyGameEntity.isPlayer
    get() = this.type == Player

val AnyGameEntity.tile: Tile
    get() = attribute<EntityTile>().tile

val AnyGameEntity.occupiesBlock: Boolean
    get() = hasAttribute<BlockOccupier>()

val AnyGameEntity.blocksVision: Boolean
    get() = hasAttribute<VisionBlocker>()