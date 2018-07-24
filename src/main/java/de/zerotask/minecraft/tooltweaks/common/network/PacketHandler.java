package de.zerotask.minecraft.tooltweaks.common.network;

import de.zerotask.minecraft.tooltweaks.ToolTweaks;
import de.zerotask.minecraft.tooltweaks.common.config.ToolTweaksConfiguration;
import de.zerotask.minecraft.tooltweaks.common.network.handler.ConfigurationMessageHandler;
import de.zerotask.minecraft.tooltweaks.common.network.message.ConfigurationMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketHandler {

    private static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(ToolTweaks.MODID);

    public static void init() {
        CHANNEL.registerMessage(ConfigurationMessageHandler.class, ConfigurationMessage.class, 0, Side.CLIENT);
    }

    public static void sendConfigurationMessage(EntityPlayerMP player) {
        CHANNEL.sendTo(new ConfigurationMessage(), player);
    }

    @SideOnly(Side.SERVER)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        ToolTweaks.LOG.debug("Player joined server, checking for config sync...");
        // sync the server config with the client config.
        if (!event.player.getEntityWorld().isRemote && ToolTweaksConfiguration.getValidConfiguration().syncConfigToClient) {
            // we are on the server world. Send the config.
            ToolTweaks.LOG.debug("Sending configuration to client!");
            sendConfigurationMessage((EntityPlayerMP) event.player);
        }
    }
}
