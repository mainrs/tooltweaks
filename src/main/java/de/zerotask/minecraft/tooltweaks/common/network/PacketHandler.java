package de.zerotask.minecraft.tooltweaks.common.network;

import de.zerotask.minecraft.tooltweaks.ToolTweaks;
import de.zerotask.minecraft.tooltweaks.common.config.ToolTweaksConfiguration;
import de.zerotask.minecraft.tooltweaks.common.network.message.ConfigurationMessage;
import de.zerotask.minecraft.tooltweaks.common.network.handler.ConfigurationMessageHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ToolTweaks.MODID);

    public static void init() {
        INSTANCE.registerMessage(ConfigurationMessageHandler.class, ConfigurationMessage.class, 0, Side.CLIENT);
    }

    @SideOnly(Side.SERVER)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        // sync the server config with the client config.
        if(!event.player.getEntityWorld().isRemote && ToolTweaksConfiguration.getValidConfiguration().syncConfigToClient) {
            // we are on the server world. Send the config.
            INSTANCE.sendTo(new ConfigurationMessage(), (EntityPlayerMP) event.player);
        }
    }
}
