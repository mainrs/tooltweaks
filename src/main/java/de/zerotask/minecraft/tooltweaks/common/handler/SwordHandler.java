package de.zerotask.minecraft.tooltweaks.common.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SwordHandler extends AbstractHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onSwordAttack(AttackEntityEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack mainHand = player.getHeldItemMainhand();

        // check if item is banned.
        if (!mainHand.isEmpty() && isBannedItem(mainHand)) {
            event.setCanceled(true);
        }
    }
}
