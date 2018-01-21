package de.zerotask.minecraft.tooltweaks.common.network.message

class ConfigurationMessage/* : IMessage {

    var configuration: ToolTweaksConfiguration.SidedConfiguration? = null
        private set

    override fun fromBytes(buf: ByteBuf) {
        configuration = ToolTweaksConfiguration.SidedConfiguration()

        var size = buf.readInt()
        var array = arrayOfNulls<String>(size)
        for (i in 0 until size) {
            array[i] = ByteBufUtils.readUTF8String(buf)
        }
        configuration!!.blacklist = array.filterNotNull()

        size = buf.readInt()
        array = arrayOfNulls(size)
        for (i in 0 until size) {
            array[i] = ByteBufUtils.readUTF8String(buf)
        }
        configuration!!.whitelist = array
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeInt(ToolTweaksConfiguration.serverSide.blacklist.size)
        for (itemId in ToolTweaksConfiguration.serverSide.blacklist) {
            ByteBufUtils.writeUTF8String(buf, itemId)
        }

        buf.writeInt(ToolTweaksConfiguration.serverSide.whitelist.size)
        for (itemId in ToolTweaksConfiguration.serverSide.whitelist) {
            ByteBufUtils.writeUTF8String(buf, itemId)
        }
    }
}*/
