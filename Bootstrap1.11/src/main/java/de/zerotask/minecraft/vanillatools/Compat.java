package de.zerotask.minecraft.vanillatools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class Compat implements CompatInterface {
    @Override
    public boolean isAnItem(ItemStack itemStack) {
        return (!itemStack.isEmpty());
    }

    @Override
    public Entity getSenderEntityFromEvent(LivingHurtEvent event) {
        if( event.getSource() == null ) {
            return null;
        }
        return event.getSource().getEntity();
    }
}
