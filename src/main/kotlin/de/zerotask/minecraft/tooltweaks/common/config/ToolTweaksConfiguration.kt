package de.zerotask.minecraft.tooltweaks.common.config

import de.zerotask.minecraft.tooltweaks.ToolTweaks.MODID
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.config.Config
import net.minecraftforge.fml.client.event.ConfigChangedEvent

/**
 * The configuration class used to hold the user defined item ids.
 */
@Config(modid = MODID, category = "")
object ToolTweaksConfiguration {

    @Config.Comment("Server sided configurations.")
    @Config.Name("general")
    @JvmField
    var clientSide = ConfigurationOptions()

    /**
     * This field gets set after synchronization with the server config file.
     * TODO: @Ignore if PR gets through.
     */
    @JvmField
    var serverSide: ConfigurationOptions? = null

    class ConfigurationOptions {

        @Config.Comment("An array holding every item id that should be blacklisted for player usage.")
        @JvmField
        var blacklist = arrayOf(
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
        )

        @Config.Comment("An array holding each item id that should be allowed by the player to be used.",
                "Overwrites blacklist.")
        @JvmField
        var whitelist = arrayOfNulls<String>(0)

        @Config.Comment("If set to true, the server will send its configuration file to the client.")
        @JvmField
        var syncConfigToClient = true
    }

    fun saveServerConfigToClient() {
        if(serverSide != null) {
            // update client config to server config. Cast should be safe here!
            clientSide = serverSide!!

            // trigger config update event to enable set updates within AbstractHandler.
            // only the first parameter should actually be important.
            MinecraftForge.EVENT_BUS.post(ConfigChangedEvent.OnConfigChangedEvent(MODID, "", true, false))
        }
    }

    fun getValidConfiguration(): ConfigurationOptions {
        return serverSide?: clientSide
    }
}
