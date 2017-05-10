package de.zerotask.minecraft.vanillatools.handler;

import mcjty.lib.tools.ItemStackTools;

import net.minecraft.item.ItemHoe;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * The event handler used to disable all vanilla hoes.
 *
 * @author Sven Lechner (SirWindfield)
 * @version 1.0
 */
public class HoeHandler {

    /**
     * Gets called each time the player uses the hoe.
     *
     * @param event The event.
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onHoeing(UseHoeEvent event) {
        if (!ItemStackTools.isEmpty(event.getCurrent())) {
            if (event.getCurrent().getItem() instanceof ItemHoe) {
                event.setCanceled(true);
            }
        }
    }
}
