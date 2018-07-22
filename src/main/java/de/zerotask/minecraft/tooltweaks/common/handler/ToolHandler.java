package de.zerotask.minecraft.tooltweaks.common.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ToolHandler extends AbstractHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onItemUse(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack mainHand = player.getHeldItemMainhand();

        // check for banned item.
        if (!mainHand.isEmpty() && isBannedItem(mainHand)) {
            event.setCanceled(true);
        }
    }
}
