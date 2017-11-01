package de.zerotask.minecraft.vanillatools.handler;

import de.zerotask.minecraft.vanillatools.VanillaToolsInterface;
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
 * @author Sven Lechner (SirWindfield), Sylvain Viste (Fanged Hex)
 * @version 1.0.7
 */
public class SwordHandler extends AbstractHandler {

    public SwordHandler(VanillaToolsInterface modInstance) {
        super(modInstance);
    }

    /**
     * Gets called when any living creature takes damage.
     *
     * @param event The event.
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onDamageTaken(LivingHurtEvent event) {
        // only consider any calculations if the source is the player itself
        Entity entity = event.getSource().getTrueSource();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            // only look for the main hand
            ItemStack stack = player.getHeldItemMainhand();
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof ItemSword) {
                    if(isBannedItem(stack)) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
