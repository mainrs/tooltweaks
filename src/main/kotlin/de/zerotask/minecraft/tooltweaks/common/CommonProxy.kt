package de.zerotask.minecraft.tooltweaks.common

import de.zerotask.minecraft.tooltweaks.common.handler.BowHandler
import de.zerotask.minecraft.tooltweaks.common.handler.HoeHandler
import de.zerotask.minecraft.tooltweaks.common.handler.SwordHandler
import de.zerotask.minecraft.tooltweaks.common.handler.ToolHandler
import de.zerotask.minecraft.tooltweaks.common.network.PacketHandler
import net.minecraftforge.common.MinecraftForge

open class CommonProxy {

    open fun preInit() {
        PacketHandler.init()
    }

    open fun init() {
        MinecraftForge.EVENT_BUS.register(BowHandler())
        MinecraftForge.EVENT_BUS.register(HoeHandler())
        MinecraftForge.EVENT_BUS.register(SwordHandler())
        MinecraftForge.EVENT_BUS.register(ToolHandler())
    }
}