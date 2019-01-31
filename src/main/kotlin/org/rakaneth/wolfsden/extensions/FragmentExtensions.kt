package org.rakaneth.wolfsden.extensions

import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.expression.concatWithConvert
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.component.Fragment
import org.hexworks.zircon.api.component.Label
import org.hexworks.zircon.api.component.Panel
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.data.Size

fun Fragment.produceLabel(
    caption: String,
    pos: Position,
    size: Size,
    prop: ObservableValue<Any>
): Label {
    return Components.label()
        .withSize(size)
        .withPosition(pos)
        .build().apply {
            val lblText = createPropertyFrom(caption).concatWithConvert(prop)
            textProperty.bind(lblText)
        }
}

fun Fragment.produceLabelAlign(
    caption: String,
    align: ComponentAlignment,
    size: Size,
    parent: Panel,
    prop: ObservableValue<Any>
): Label {
    return Components.label()
        .withSize(size)
        .withAlignmentWithin(parent, align)
        .build().apply {
            val lblText = createPropertyFrom(caption).concatWithConvert(prop)
            textProperty.bind(lblText)
        }
}