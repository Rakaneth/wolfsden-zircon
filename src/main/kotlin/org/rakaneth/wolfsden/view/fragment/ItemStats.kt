package org.rakaneth.wolfsden.view.fragment

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.Sizes
import org.hexworks.zircon.api.component.Fragment
import org.rakaneth.wolfsden.attributes.types.Stattable


class ItemStats(selectedItem: ObservableValue<Stattable>) : Fragment {
    override val root = Components.panel()
        .withSize(10, 10)
        .build().apply {
            val item = selectedItem.value
            val header = Components.header()
                .withText("Item Info")
                .build()
            val itemName = Components.label()
                .withText(item.name)
                .withPosition(0, 0)
                .build()
            val itemDesc = Components.paragraph()
                .withText(item.description)
                .withPosition(0, 1)
                .build()

        }

    companion object {
        private val BLOCK_SIZE = Sizes.create(21, 3)
    }
}