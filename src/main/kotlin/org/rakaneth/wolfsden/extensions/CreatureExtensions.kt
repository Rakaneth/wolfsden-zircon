package org.rakaneth.wolfsden.extensions

import org.rakaneth.wolfsden.attributes.CreatureStats
import org.rakaneth.wolfsden.attributes.types.Stattable

val GameEntity<Stattable>.stats: CreatureStats
    get() = attribute()
