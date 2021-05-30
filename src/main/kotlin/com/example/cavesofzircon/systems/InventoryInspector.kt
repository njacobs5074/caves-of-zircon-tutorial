package com.example.cavesofzircon.systems

import com.example.cavesofzircon.GameConfig
import com.example.cavesofzircon.attributes.types.EnergyUser
import com.example.cavesofzircon.attributes.types.Food
import com.example.cavesofzircon.attributes.types.inventory
import com.example.cavesofzircon.extensions.whenTypeIs
import com.example.cavesofzircon.messages.DropItem
import com.example.cavesofzircon.messages.Eat
import com.example.cavesofzircon.messages.InspectInventory
import com.example.cavesofzircon.view.fragment.InventoryFragment
import com.example.cavesofzircon.world.GameContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.platform.Dispatchers
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.ComponentDecorations.shadow
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.builder.component.ModalBuilder
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.internal.component.modal.EmptyModalResult

object InventoryInspector : BaseFacet<GameContext, InspectInventory>(InspectInventory::class) {

    val DIALOG_SIZE = Size.create(35, 15)

    override suspend fun receive(message: InspectInventory): Response {
        val (context, itemHolder, position) = message

        val screen = context.screen

        val panel = Components.panel()
            .withSize(DIALOG_SIZE)
            .withDecorations(box(title = "Inventory"), shadow())
            .build()

        val fragment = InventoryFragment(
            inventory = itemHolder.inventory,
            width = DIALOG_SIZE.width - 3,
            onDrop = { item ->
                CoroutineScope(Dispatchers.Single).launch {
                    itemHolder.receiveMessage(DropItem(context, itemHolder, item, position))
                }
            },
            onEat = { item ->
                CoroutineScope(Dispatchers.Single).launch {
                    itemHolder.whenTypeIs<EnergyUser> { eater ->
                        item.whenTypeIs<Food> { food ->
                            itemHolder.inventory.removeItem(food)
                            itemHolder.receiveMessage(Eat(context, eater, food))
                        }
                    }
                }
            })

        panel.addFragment(fragment)

        val modal = ModalBuilder.newBuilder<EmptyModalResult>()
            .withParentSize(screen.size)
            .withComponent(panel)
            .withCenteredDialog(true)
            .build()

        panel.addComponent(Components.button()
            .withText("Close")
            .withAlignmentWithin(panel, ComponentAlignment.BOTTOM_LEFT)
            .build().apply {
                onActivated {
                    modal.close(EmptyModalResult)
                    Processed
                }
            })

        modal.theme = GameConfig.THEME
        screen.openModal(modal)
        return Consumed
    }
}
