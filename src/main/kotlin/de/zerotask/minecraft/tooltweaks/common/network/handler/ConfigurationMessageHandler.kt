package de.zerotask.minecraft.tooltweaks.common.network.handler

import de.zerotask.minecraft.tooltweaks.common.config.ToolTweaksConfiguration
import de.zerotask.minecraft.tooltweaks.common.network.message.ConfigurationMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConfigurationMessageHandler : IMessageHandler<ConfigurationMessage, IMessage> {

    private val logger: Logger = LogManager.getLogger(this.javaClass.simpleName)

    override fun onMessage(message: ConfigurationMessage, ctx: MessageContext): IMessage? {
        // just set the serverSide config to the new one.
        logger.debug("Received configuration file from server. Setting field now.")
        ToolTweaksConfiguration.serverSide = message.configuration
        // We should be running on the client side here. Just check again to be sure. Flush config to file.
        logger.debug("Saving server configuration to client configuration file.")
        ToolTweaksConfiguration.saveServerConfigToClient()
        return null
    }
}
