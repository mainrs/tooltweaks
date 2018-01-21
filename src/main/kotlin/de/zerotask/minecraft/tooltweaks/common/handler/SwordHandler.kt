package de.zerotask.minecraft.tooltweaks.common.handler

import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class SwordHandler : AbstractHandler() {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onSwordAttack(event: AttackEntityEvent) {
        val player = event.entityPlayer
        val mainHand = player.heldItemMainhand

        // check if item is banned.
        if (!mainHand.isEmpty && isBannedItem(mainHand)) {
            event.isCanceled = true
        }
    }
}
