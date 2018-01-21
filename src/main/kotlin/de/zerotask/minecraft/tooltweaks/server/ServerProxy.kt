package de.zerotask.minecraft.tooltweaks.server

import de.zerotask.minecraft.tooltweaks.common.CommonProxy
import de.zerotask.minecraft.tooltweaks.common.network.PacketHandler
import net.minecraftforge.common.MinecraftForge

class ServerProxy: CommonProxy() {

    override fun init() {
        super.init()
        MinecraftForge.EVENT_BUS.register(PacketHandler())
    }
}