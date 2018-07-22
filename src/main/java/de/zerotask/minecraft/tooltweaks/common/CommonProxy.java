package de.zerotask.minecraft.tooltweaks.common;

import de.zerotask.minecraft.tooltweaks.common.handler.BowHandler;
import de.zerotask.minecraft.tooltweaks.common.handler.HoeHandler;
import de.zerotask.minecraft.tooltweaks.common.handler.SwordHandler;
import de.zerotask.minecraft.tooltweaks.common.handler.ToolHandler;
import de.zerotask.minecraft.tooltweaks.common.network.PacketHandler;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public void preInit() {
        PacketHandler.init();
    }

    public void init() {
        MinecraftForge.EVENT_BUS.register(new BowHandler());
        MinecraftForge.EVENT_BUS.register(new HoeHandler());
        MinecraftForge.EVENT_BUS.register(new SwordHandler());
        MinecraftForge.EVENT_BUS.register(new ToolHandler());
    }
}
