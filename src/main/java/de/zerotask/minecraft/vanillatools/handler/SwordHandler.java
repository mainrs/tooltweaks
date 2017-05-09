package de.zerotask.minecraft.vanillatools.handler;

import mcjty.lib.tools.ItemStackTools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * The event handler used to disable all vanilla swords.
 *
 * @author Sven Lechner (SirWindfield)
 * @version 1.0
 */
public class SwordHandler {

    /**
     * Gets called when any living creature takes damage.
     *
     * @param event The event.
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onDamageTaken(LivingHurtEvent event) {
        // only consider any calculations if the source is the player itself
        if (event.getSource().getDamageType().equals("player")) {
            Entity entity = event.getSource().getEntity();
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                // only look for the main hand
                ItemStack stack = player.getHeldItemMainhand();
                if (!ItemStackTools.isEmpty(stack)) {
                    if (stack.getItem() instanceof ItemSword) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
