package org.rakaneth.wolfsden.view.fragment

import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.expression.concatWithConvert
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.*
import org.hexworks.zircon.api.data.Size
import org.rakaneth.wolfsden.attributes.types.Stattable
import org.rakaneth.wolfsden.extensions.GameEntity
import org.rakaneth.wolfsden.extensions.produceLabelAlign
import org.rakaneth.wolfsden.extensions.stats

class StatBlock(creature: GameEntity<Stattable>) : Fragment {
    private fun produceLbl(
        caption: String,
        align: ComponentAlignment,
        panel: Panel,
        prop: ObservableValue<Any>
    ): Label {
        return Components.label()
            .withSize(LBL_SIZE)
            .withAlignmentWithin(panel, align)
            .build().apply {
                val lblText = createPropertyFrom(caption).concatWithConvert(prop)
                textProperty.bind(lblText)
            }
    }

    override val root: Component by lazy {
        val panel = Components.panel()
            .withSize(PANEL_SIZE)
            .build()
        val strLbl = produceLabelAlign(
            "Str ",
            ComponentAlignment.TOP_LEFT,
            LBL_SIZE,
            panel,
            creature.stats.strProp
        )
        val stamLbl = produceLabelAlign(
            "Stm ",
            ComponentAlignment.TOP_CENTER,
            LBL_SIZE,
            panel,
            creature.stats.stamProp
        )
        val spdLbl = produceLabelAlign(
            "Spd ",
            ComponentAlignment.TOP_RIGHT,
            LBL_SIZE,
            panel,
            creature.stats.spdProp
        )
        val sklLbl = produceLabelAlign(
            "Skl ",
            ComponentAlignment.BOTTOM_LEFT,
            LBL_SIZE,
            panel,
            creature.stats.sklProp
        )
        val sagLbl = produceLabelAlign(
            "Sag ",
            ComponentAlignment.BOTTOM_CENTER,
            LBL_SIZE,
            panel,
            creature.stats.sagProp
        )
        val smtLbl = produceLabelAlign(
            "Smt ",
            ComponentAlignment.BOTTOM_RIGHT,
            LBL_SIZE,
            panel,
            creature.stats.smtProp
        )

        panel.apply {
            addComponent(strLbl)
            addComponent(stamLbl)
            addComponent(spdLbl)
            addComponent(sklLbl)
            addComponent(sagLbl)
            addComponent(smtLbl)
        }
    }

    companion object {
        private val PANEL_SIZE: Size = Size.create(21, 2)
        private val LBL_SIZE: Size = Size.create(7, 1)
    }

}