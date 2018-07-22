package de.zerotask.minecraft.tooltweaks.client;

import de.zerotask.minecraft.tooltweaks.common.CommonProxy;
import de.zerotask.minecraft.tooltweaks.common.handler.TooltipHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        super.init();
        MinecraftForge.EVENT_BUS.register(new TooltipHandler());
    }
}
