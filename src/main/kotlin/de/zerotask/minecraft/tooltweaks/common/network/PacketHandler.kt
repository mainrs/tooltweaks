package de.zerotask.minecraft.tooltweaks.common.network

import de.zerotask.minecraft.tooltweaks.ToolTweaks.MODID
import de.zerotask.minecraft.tooltweaks.common.config.ToolTweaksConfiguration
import de.zerotask.minecraft.tooltweaks.common.network.handler.ConfigurationMessageHandler
import de.zerotask.minecraft.tooltweaks.common.network.message.ConfigurationMessage
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class PacketHandler {

    companion object {

        val CHANNEL: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID)

        fun init() {
            // make sure to register the packet message handler as client sided.
            CHANNEL.registerMessage<ConfigurationMessage, IMessage>(ConfigurationMessageHandler::class.java,
                    ConfigurationMessage::class.java, 0, Side.CLIENT)
        }
    }

    @SideOnly(Side.SERVER)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onPlayerLogin(event: PlayerEvent.PlayerLoggedInEvent) {
        // sync the server config with the client config if it has been allowed.
        if (!event.player.entityWorld.isRemote && ToolTweaksConfiguration.getValidConfiguration().syncConfigToClient) {
            // we are on the server world. Send the config.
            CHANNEL.sendTo(ConfigurationMessage(), event.player as EntityPlayerMP)
        }
    }
}
