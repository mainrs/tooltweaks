package de.zerotask.minecraft.tooltweaks.common.network.handler;

import de.zerotask.minecraft.tooltweaks.common.config.ToolTweaksConfiguration;
import de.zerotask.minecraft.tooltweaks.common.network.message.ConfigurationMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationMessageHandler implements IMessageHandler<ConfigurationMessage, IMessage> {

    private Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    public IMessage onMessage(ConfigurationMessage message, MessageContext context) {
        logger.debug("Received configuration file from server. Setting field now");
        ToolTweaksConfiguration.serverSide = message.getConfiguration();

        logger.debug("Saving server configuration to client's configuration file");
        ToolTweaksConfiguration.saveServerConfigToClient();
        return null;
    }
}
