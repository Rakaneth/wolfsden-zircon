package org.rakaneth.wolfsden.extensions

import org.hexworks.cobalt.databinding.api.property.Property
import org.rakaneth.wolfsden.attributes.EntityEquip
import org.rakaneth.wolfsden.attributes.EquipStats
import org.rakaneth.wolfsden.attributes.types.Equipment

var GameEntity<Equipment>.equipped: Boolean
    get() = attribute<EntityEquip>().equipped
    set(value) {
        attribute<EntityEquip>().equipped = value
    }

val GameEntity<Equipment>.atp: Int
    get() = attribute<EquipStats>().atp

val GameEntity<Equipment>.atpProp: Property<Int>
    get() = attribute<EquipStats>().atpProp

val GameEntity<Equipment>.dfp: Int
    get() = attribute<EquipStats>().dfp

val GameEntity<Equipment>.dfpProp: Property<Int>
    get() = attribute<EquipStats>().dfpProp

val GameEntity<Equipment>.tou: Int
    get() = attribute<EquipStats>().tou

val GameEntity<Equipment>.touProp: Property<Int>
    get() = attribute<EquipStats>().touProp

val GameEntity<Equipment>.res: Int
    get() = attribute<EquipStats>().res

val GameEntity<Equipment>.resProp: Property<Int>
    get() = attribute<EquipStats>().resProp

val GameEntity<Equipment>.dmg: Int
    get() = attribute<EquipStats>().dmg

val GameEntity<Equipment>.dmgProp: Property<Int>
    get() = attribute<EquipStats>().dmgProp

val GameEntity<Equipment>.wil: Int
    get() = attribute<EquipStats>().wil

val GameEntity<Equipment>.wilProp: Property<Int>
    get() = attribute<EquipStats>().wilProp

val GameEntity<Equipment>.pwr: Int
    get() = attribute<EquipStats>().pwr

val GameEntity<Equipment>.pwrProp: Property<Int>
    get() = attribute<EquipStats>().pwrProp