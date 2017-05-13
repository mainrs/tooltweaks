package de.zerotask.minecraft.vanillatools.handler;

import de.zerotask.minecraft.vanillatools.VanillaTools;

import net.minecraft.item.ItemStack;

/**
 * Created by Sven on 13.05.2017.
 */
abstract class AbstractHandler {

    protected boolean isBannedItem(ItemStack stack) {
        String id = stack.getItem().getRegistryName().toString();
        return VanillaTools.getInstance().getBlacklist().contains(id);
    }
}
