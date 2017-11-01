package de.zerotask.minecraft.vanillatools.handler;

import de.zerotask.minecraft.vanillatools.VanillaToolsInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * The event handler used to disable all vanilla tools.
 *
 * @author Sven Lechner (SirWindfield), Sylvain Viste (Fanged Hex)
 * @version 1.0.7
 */
public class ToolHandler extends AbstractHandler {

    public ToolHandler(VanillaToolsInterface modInstance) {
        super(modInstance);
    }

    /**
     * Gets called each time the user tries to break blocks with his tool.
     *
     * @param event The event.
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onItemUse(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player != null) {
            ItemStack stack = player.getHeldItemMainhand();
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof ItemTool) {
                    if(isBannedItem(stack)) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
