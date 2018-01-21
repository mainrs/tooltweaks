package de.zerotask.minecraft.tooltweaks.common.handler

import net.minecraft.client.resources.I18n
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class TooltipHandler : AbstractHandler() {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    fun onTooltipCreation(event: ItemTooltipEvent) {
        if(isBannedItem(event.itemStack)) {
            event.toolTip.addAll(createTooltip())
        }
    }

    private fun createTooltip(): Collection<String> {
        val list = ArrayList<String>()

        list.add("")
        list.add(I18n.format("tooltip.not_usable", TextFormatting.DARK_RED, TextFormatting.RESET))
        list.add(I18n.format("tooltip.crafting_only", TextFormatting.DARK_RED, TextFormatting.RESET))

        return list
    }
}
