package de.zerotask.minecraft.vanillatools;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface CompatInterface {
    boolean isAnItem(ItemStack itemStack);
    Entity getSenderEntityFromEvent(LivingHurtEvent event);
}
