package de.zerotask.minecraft.vanillatools.handler;

import net.minecraft.item.ItemHoe;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * The event handler used to disable all vanilla hoes.
 *
 * @author Sven Lechner (SirWindfield), Sylvain Viste (Fanged Hex)
 * @version 1.0.7
 */
public class HoeHandler extends AbstractHandler {

    /**
     * Gets called each time the player uses the hoe.
     *
     * @param event The event.
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onHoeing(UseHoeEvent event) {
        if (!event.getCurrent().isEmpty()) {
            if (event.getCurrent().getItem() instanceof ItemHoe) {
                if(isBannedItem(event.getCurrent())) {
                    event.setCanceled(true);
                }
            }
        }
    }
}
