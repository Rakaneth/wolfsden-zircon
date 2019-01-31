package org.rakaneth.wolfsden.attributes.types

import org.hexworks.amethyst.api.base.BaseEntityType

open class Stattable(name: String) : BaseEntityType(name)
open class Item(name: String) : BaseEntityType(name)

object Player : Stattable(name = "player")
object Creature : Stattable(name = "creature")
object Equipment : Item(name = "equip")
object Consumable : Item(name = "item")