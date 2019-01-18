package org.rakaneth.wolfsden.attributes

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.property.Property

data class CreatureStats(val strProp: Property<Int>,
                   val stamProp: Property<Int>,
                   val sklProp: Property<Int>,
                   val spdProp: Property<Int>,
                   val sagProp: Property<Int>,
                   val smtProp: Property<Int>): Attribute {

    val str: Int by strProp.asDelegate()
    val stam: Int by stamProp.asDelegate()
    val spd: Int by spdProp.asDelegate()
    val skl: Int by sklProp.asDelegate()
    val sag: Int by sagProp.asDelegate()
    val smt: Int by smtProp.asDelegate()

    val strBonus: Int
        get() = str / 10

    val stamBonus: Int
        get() = stam / 10

    val spdBonus: Int
        get() = spd / 10

    val sklBonus: Int
        get() = skl / 10

    val sagBonus: Int
        get() = sag / 10

    val smtBonus: Int
        get() = smt / 10

    companion object {
        fun create(str: Int=10,
                   stam: Int=10,
                   spd: Int=10,
                   skl: Int=10,
                   sag: Int=10,
                   smt: Int=10) = CreatureStats(
            strProp = createPropertyFrom(str),
            stamProp = createPropertyFrom(stam),
            spdProp = createPropertyFrom(spd),
            sklProp = createPropertyFrom(skl),
            sagProp = createPropertyFrom(sag),
            smtProp = createPropertyFrom(smt)
        )
    }
}