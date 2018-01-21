package de.zerotask.minecraft.tooltweaks.common.handler

import net.minecraftforge.event.entity.player.UseHoeEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class HoeHandler : AbstractHandler() {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onHoeUse(event: UseHoeEvent) {
        val player = event.entityPlayer
        val mainHand = player.heldItemMainhand

        // check for banned item.
        if (!mainHand.isEmpty && isBannedItem(mainHand)) {
            event.isCanceled = true
        }
    }
}
