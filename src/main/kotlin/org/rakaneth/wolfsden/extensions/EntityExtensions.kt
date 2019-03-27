package org.rakaneth.wolfsden.extensions

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.cobalt.datatypes.extensions.orElseThrow
import org.hexworks.zircon.api.data.Tile
import org.rakaneth.wolfsden.attributes.EntityID
import org.rakaneth.wolfsden.attributes.EntityPosition
import org.rakaneth.wolfsden.attributes.EntityTile
import org.rakaneth.wolfsden.attributes.TagList
import org.rakaneth.wolfsden.attributes.flags.BlockOccupier
import org.rakaneth.wolfsden.attributes.flags.VisionBlocker
import org.rakaneth.wolfsden.attributes.types.Player

inline fun <reified T : Attribute> AnyGameEntity.attribute(): T = findAttribute(T::class)
    .orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${T::class.simpleName}'.")
}

inline fun <reified T: Attribute> AnyGameEntity.hasAttribute(): Boolean =
    this.findAttribute(T::class).isPresent

inline fun <reified T: Attribute> AnyGameEntity.whenHasAttribute(crossinline fn:
    (T) -> Unit) {
    findAttribute(T::class).map(fn)
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
        findAttribute(EntityPosition::class).map {
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

val AnyGameEntity.dispName: Property<String>
    get() = attribute<EntityID>().nameProp

val AnyGameEntity.desc: Property<String>
    get() = attribute<EntityID>().descProp

val AnyGameEntity.tags: MutableSet<String>
    get() = attribute<TagList>().tags

fun AnyGameEntity.hasTag(tag: String) = tags.contains(tag)

fun AnyGameEntity.addTag(tag: String) = tags.add(tag)

fun AnyGameEntity.removeTag(tag: String) = tags.remove(tag)


