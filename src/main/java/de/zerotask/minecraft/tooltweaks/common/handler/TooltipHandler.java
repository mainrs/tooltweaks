package de.zerotask.minecraft.tooltweaks.common.handler;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TooltipHandler extends AbstractHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onTooltipCreation(ItemTooltipEvent event) {
        if (isBannedItem(event.getItemStack())) {
            event.getToolTip().addAll(createTooltip());
        }
    }

    private Collection<String> createTooltip() {
        List<String> list = new ArrayList<>();

        list.add("");
        list.add(I18n.format("tooltip.not_usable", TextFormatting.DARK_RED, TextFormatting.RESET));
        list.add(I18n.format("tooltip.crafting_only", TextFormatting.DARK_RED, TextFormatting.RESET));

        return list;
    }
}
