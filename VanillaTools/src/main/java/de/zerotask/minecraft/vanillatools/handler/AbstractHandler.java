package de.zerotask.minecraft.vanillatools.handler;

import de.zerotask.minecraft.vanillatools.VanillaToolsInterface;
import net.minecraft.item.ItemStack;

abstract class AbstractHandler {

    private VanillaToolsInterface modInstance;

    AbstractHandler(VanillaToolsInterface modInstance) {
        this.modInstance = modInstance;
    }

    boolean isBannedItem(ItemStack stack) {
        // check for white listed
        String id = stack.getItem().getRegistryName().toString();
        boolean whitelisted = this.modInstance.getConfiguration().getWhitelist().contains(id);
        if (whitelisted) {
            return false;
        }

        // check if all tools are disabled, no need to check blacklist then
        boolean allDisabled = this.modInstance.getConfiguration().isDisableAllTools();
        return allDisabled || this.modInstance.getConfiguration().getBlacklist().contains(id);

    }
}
