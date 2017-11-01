package de.zerotask.minecraft.vanillatools.handler;

import de.zerotask.minecraft.vanillatools.VanillaTools;

import net.minecraft.item.ItemStack;

/**
 * Created by Sven on 13.05.2017.
 */
abstract class AbstractHandler {

    protected boolean isBannedItem(ItemStack stack) {
        // check for white listed
        String id = stack.getItem().getRegistryName().toString();
        boolean whitelisted = VanillaTools.getInstance().getConfiguration().getWhitelist().contains(id);
        if(whitelisted) {
            return false;
        }

        // check if all tools are disabled, no need to check blacklist then
        boolean allDisabled = VanillaTools.getInstance().getConfiguration().isDisableAllTools();
        if(allDisabled) {
            return true;
        }

        return VanillaTools.getInstance().getConfiguration().getBlacklist().contains(id);
    }
}
