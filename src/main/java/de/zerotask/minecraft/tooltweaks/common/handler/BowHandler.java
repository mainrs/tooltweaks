package de.zerotask.minecraft.tooltweaks.common.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BowHandler extends AbstractHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onBowCocked(LivingEntityUseItemEvent event) {
        // Only trigger if the entity is a player, else skeletons wouldn't be able to use the bow.
        Entity entity = event.getEntity();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            ItemStack mainHand = player.getHeldItemMainhand();

            if (!mainHand.isEmpty() && isBannedItem(mainHand)) {
                event.setCanceled(true);
            }
        }
    }
}