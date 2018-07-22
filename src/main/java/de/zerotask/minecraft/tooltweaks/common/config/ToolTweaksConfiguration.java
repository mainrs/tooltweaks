package de.zerotask.minecraft.tooltweaks.common.config;

import de.zerotask.minecraft.tooltweaks.ToolTweaks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

/**
 * The configuration class used to hold the user defined item ids.
 */
@Config(modid = ToolTweaks.MODID, category = "")
public class ToolTweaksConfiguration {

    @Config.Comment("Client sided configurations.")
    @Config.Name("general")
    public static SidedConfiguration clientSide = new SidedConfiguration();

    @Config.Ignore
    public static SidedConfiguration serverSide;

    public static class SidedConfiguration {

        @Config.Comment("An array holding every item id that should be blacklisted for player usage.")
        public String[] blacklist = new String[]{
                "minecraft:wooden_axe",
                "minecraft:wooden_hoe",
                "minecraft:wooden_pickaxe",
                "minecraft:wooden_shovel",
                "minecraft:wooden_sword",
                "minecraft:stone_axe",
                "minecraft:stone_hoe",
                "minecraft:stone_pickaxe",
                "minecraft:stone_shovel",
                "minecraft:stone_sword",
                "minecraft:golden_axe",
                "minecraft:golden_hoe",
                "minecraft:golden_pickaxe",
                "minecraft:golden_shovel",
                "minecraft:golden_sword",
                "minecraft:iron_axe",
                "minecraft:iron_hoe",
                "minecraft:iron_pickaxe",
                "minecraft:iron_shovel",
                "minecraft:iron_sword",
                "minecraft:diamond_axe",
                "minecraft:diamond_hoe",
                "minecraft:diamond_pickaxe",
                "minecraft:diamond_shovel",
                "minecraft:diamond_sword",
                "minecraft:bow"
        };

        @Config.Comment({"An array holding each item id that should be allowed by the player to be used.",
                "Overwrites blacklist."})
        public String[] whitelist = new String[0];

        @Config.Comment("If set to true, the server will send its configuration file to the client.")
        public boolean syncConfigToClient = true;
    }

    public static void saveServerConfigToClient() {
        if(serverSide != null) {
            clientSide = serverSide;

            MinecraftForge.EVENT_BUS.post(new ConfigChangedEvent.OnConfigChangedEvent(ToolTweaks.MODID, "", true, false));
        }
    }

    public static SidedConfiguration getValidConfiguration() {
        if(serverSide != null) {
            return serverSide;
        }
        return clientSide;
    }
}
