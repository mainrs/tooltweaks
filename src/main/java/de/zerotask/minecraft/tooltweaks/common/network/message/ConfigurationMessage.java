package de.zerotask.minecraft.tooltweaks.common.network.message;

import de.zerotask.minecraft.tooltweaks.common.config.ToolTweaksConfiguration;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ConfigurationMessage implements IMessage {

    private ToolTweaksConfiguration.SidedConfiguration configuration = null;

    @Override
    public void fromBytes(ByteBuf buf) {
        configuration = new ToolTweaksConfiguration.SidedConfiguration();

        int size = buf.readInt();
        String[] array = new String[size];
        for(int i = 0; i < size; i++) {
            array[i] = ByteBufUtils.readUTF8String(buf);
        }
        configuration.blacklist = array;

        size = buf.readInt();
        array = new String[size];
        for(int i = 0; i < size; i++) {
            array[i] = ByteBufUtils.readUTF8String(buf);
        }
        configuration.whitelist = array;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(ToolTweaksConfiguration.getValidConfiguration().blacklist.length);
        for(String itemId : ToolTweaksConfiguration.getValidConfiguration().blacklist) {
            ByteBufUtils.writeUTF8String(buf, itemId);
        }

        buf.writeInt(ToolTweaksConfiguration.getValidConfiguration().whitelist.length);
        for(String itemId : ToolTweaksConfiguration.getValidConfiguration().whitelist) {
            ByteBufUtils.writeUTF8String(buf, itemId);
        }
    }

    public ToolTweaksConfiguration.SidedConfiguration getConfiguration() {
        return configuration;
    }
}
