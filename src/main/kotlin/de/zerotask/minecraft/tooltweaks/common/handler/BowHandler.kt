package de.zerotask.minecraft.tooltweaks.common.handler

import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class BowHandler : AbstractHandler() {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onBowCocked(event: LivingEntityUseItemEvent.Start) {
        // make sure to only trigger if the entity is actually a player trying to fire the bow.
        val entity = event.entity
        if (entity is EntityPlayer) {
            val mainHand = entity.heldItemMainhand

            // check if the player is holding an item and if it is banned.
            if (!mainHand.isEmpty && isBannedItem(mainHand)) {
                event.isCanceled = true
            }
        }
    }
}
