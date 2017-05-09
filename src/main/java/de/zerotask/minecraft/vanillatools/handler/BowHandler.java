package de.zerotask.minecraft.vanillatools.handler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Sven on 09.05.17.
 */
public class BowHandler {

    /**
     * Gets called each time a player starts to use a bow.
     * @param event The event.
     */
    @SubscribeEvent
    public void onHit(ArrowNockEvent event) {
        // not sure if machines can trigger this event
        if(event.getEntityPlayer() != null) {
            ItemStack stack = event.getAction().getResult();

        }
    }
}
