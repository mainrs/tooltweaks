package de.zerotask.minecraft.tooltweaks.client

import de.zerotask.minecraft.tooltweaks.common.CommonProxy
import de.zerotask.minecraft.tooltweaks.common.handler.TooltipHandler
import net.minecraftforge.common.MinecraftForge

class ClientProxy: CommonProxy() {

    override fun init() {
        super.init()
        MinecraftForge.EVENT_BUS.register(TooltipHandler())
    }
}