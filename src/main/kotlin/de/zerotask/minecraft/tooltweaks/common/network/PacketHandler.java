package de.zerotask.minecraft.tooltweaks.common.network;

public class PacketHandler {

    //public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    public static void init() {
        // INSTANCE.registerMessage(ConfigurationMessageHandler.class, ConfigurationMessage.class, 0, Side.CLIENT);
    }
/*
    @SideOnly(Side.SERVER)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        // sync the server config with the client config.
        if(!event.player.getEntityWorld().isRemote) {
            // we are on the server world. Send the config.
            INSTANCE.sendTo(new ConfigurationMessage(), (EntityPlayerMP) event.player);
        }
    }*/
}
