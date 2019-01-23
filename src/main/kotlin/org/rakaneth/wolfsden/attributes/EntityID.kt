package org.rakaneth.wolfsden.attributes

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.property.Property

class EntityID(
    val nameProp: Property<String>,
    val descProp: Property<String>
) : Attribute {
    val name: String by nameProp.asDelegate()
    val desc: String by descProp.asDelegate()

    companion object {
        fun create(name: String, desc: String) = EntityID(
            nameProp = createPropertyFrom(name),
            descProp = createPropertyFrom(desc)
        )
    }
}