package de.zerotask.minecraft.tooltweaks.common.network.handler;

import de.zerotask.minecraft.tooltweaks.ToolTweaks;
import de.zerotask.minecraft.tooltweaks.common.config.ToolTweaksConfiguration;
import de.zerotask.minecraft.tooltweaks.common.network.message.ConfigurationMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Arrays;

public class ConfigurationMessageHandler implements IMessageHandler<ConfigurationMessage, IMessage> {

    public IMessage onMessage(ConfigurationMessage message, MessageContext context) {
        ToolTweaksConfiguration.serverSide = message.getConfiguration();

        ToolTweaks.LOG.info("Saving server configuration to client's configuration file");
        String allowedTools = Arrays.toString(message.getConfiguration().whitelist);
        String bannedTools = Arrays.toString(message.getConfiguration().blacklist);

        ToolTweaks.LOG.debug(allowedTools);
        ToolTweaks.LOG.debug(bannedTools);
        ToolTweaksConfiguration.saveServerConfigToClient();
        return null;
    }
}
