package de.zerotask.minecraft.tooltweaks.common.network.message

import de.zerotask.minecraft.tooltweaks.common.config.ToolTweaksConfiguration
import io.netty.buffer.ByteBuf
import net.minecraftforge.fml.common.network.ByteBufUtils
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

/**
 * The packet message that gets send to the client. Used to update the configuration.
 */
class ConfigurationMessage : IMessage {

    /*
     * The received configuration from the server.
     */
    var configuration: ToolTweaksConfiguration.ConfigurationOptions? = null
        private set

    override fun fromBytes(buf: ByteBuf) {
        // overwrite old configuration if present.
        configuration = ToolTweaksConfiguration.ConfigurationOptions()

        // read the array size and iterate over it.
        var size = buf.readInt()
        var array = ArrayList<String>()
        for (i in 0 until size) {
            array.add(ByteBufUtils.readUTF8String(buf))
        }
        configuration?.blacklist = array.toTypedArray()

        // now read whitelist size and copy into whitelist configuration section.
        size = buf.readInt()
        array.clear()
        for (i in 0 until size) {
            array.add(ByteBufUtils.readUTF8String(buf))
        }
        configuration?.whitelist = array.toTypedArray()
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeInt(ToolTweaksConfiguration.getValidConfiguration().blacklist.size)
        for (itemId in ToolTweaksConfiguration.getValidConfiguration().blacklist) {
            ByteBufUtils.writeUTF8String(buf, itemId)
        }

        buf.writeInt(ToolTweaksConfiguration.getValidConfiguration().whitelist.size)
        for (itemId in ToolTweaksConfiguration.getValidConfiguration().whitelist) {
            ByteBufUtils.writeUTF8String(buf, itemId)
        }
    }
}
