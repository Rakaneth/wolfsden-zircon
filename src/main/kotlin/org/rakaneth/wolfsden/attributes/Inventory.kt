package org.rakaneth.wolfsden.attributes

import org.hexworks.amethyst.api.Attribute
import org.rakaneth.wolfsden.attributes.types.Equipment
import org.rakaneth.wolfsden.attributes.types.Item
import org.rakaneth.wolfsden.extensions.*

class Inventory(
    val capacity: Int = 2,
    val inventory: MutableList<GameEntity<Item>> = mutableListOf()
) : Attribute {
    val bagsFull: Boolean
        get() = inventory.size >= capacity

    val equippedAtp: Int
        get() = equippedItems.sumBy { it.atp }

    val equippedDfp: Int
        get() = equippedItems.sumBy { it.dfp }

    val equippedTou: Int
        get() = equippedItems.sumBy { it.tou }

    val equippedRes: Int
        get() = equippedItems.sumBy { it.res }

    val equippedDmg: Int
        get() = equippedItems.sumBy { it.dmg }

    val equippedWil: Int
        get() = equippedItems.sumBy { it.wil }

    val equippedPwr: Int
        get() = equippedItems.sumBy { it.pwr }

    val equippedItems = inventory.filterIsInstance<GameEntity<Equipment>>()
        .filter { it.equipped }

}