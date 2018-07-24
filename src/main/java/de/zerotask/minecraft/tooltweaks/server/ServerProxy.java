package de.zerotask.minecraft.tooltweaks.server;

import de.zerotask.minecraft.tooltweaks.common.CommonProxy;
import de.zerotask.minecraft.tooltweaks.common.network.PacketHandler;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy extends CommonProxy {

    @Override
    public void init() {
        super.init();
        MinecraftForge.EVENT_BUS.register(new PacketHandler());
    }
}
