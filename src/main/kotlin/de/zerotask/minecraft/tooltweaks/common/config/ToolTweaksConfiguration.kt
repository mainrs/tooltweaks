package de.zerotask.minecraft.tooltweaks.common.config

import de.zerotask.minecraft.tooltweaks.ToolTweaks.MODID
import net.minecraftforge.common.config.Config

/**
 * The configuration class used to hold the user defined item ids.
 */
@Config(modid = MODID, category = "")
object ToolTweaksConfiguration {

    var clientSide = SidedConfiguration()

    @Config.Comment("Server sided configurations.")
    @Config.Name("general")
    @JvmField
    var serverSide = SidedConfiguration()

    class SidedConfiguration {

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
    }
}
