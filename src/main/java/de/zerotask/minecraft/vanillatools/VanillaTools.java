package de.zerotask.minecraft.vanillatools;

import de.zerotask.minecraft.vanillatools.handler.BowHandler;
import de.zerotask.minecraft.vanillatools.handler.HoeHandler;
import de.zerotask.minecraft.vanillatools.handler.SwordHandler;
import de.zerotask.minecraft.vanillatools.handler.ToolHandler;
import de.zerotask.minecraft.vanillatools.reference.IReference;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * A simple mod used to disable all vanilla bows, hoes, swords and tools.
 *
 * @author Sven Lechner (SirWindfield)
 * @version 1.0
 */
@Mod(modid = IReference.ID, version = IReference.VERSION,
        dependencies = "required-after:compatlayer@[0.2.8,)",
        useMetadata = true,
        acceptedMinecraftVersions = "[1.10.2,1.12)")
public class VanillaTools {

    @Mod.Instance
    private static VanillaTools instance;

    /**
     * The configuration used to read all blacklisted items.
     */
    private Configuration configuration;

    private final String[] DEFAULT_BLACKLIST = new String[]{
            "minecraft:wooden_axe",
            "minecraft:wooden_hoe",
            "minecraft:wooden_pickaxe",
            "minecraft:wooden_shovel",
            "minecraft:wooden_sword",
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

    private Set<String> blacklist;

    public VanillaTools() {
        blacklist = new HashSet<>();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configuration = new Configuration(event.getSuggestedConfigurationFile());
        try {
            configuration.load();
            initConfig();
        } finally {
            if(configuration.hasChanged()) {
                configuration.save();
            }
        }
    }

    private void initConfig() {
        String[] list = configuration.get("general", "blacklist", DEFAULT_BLACKLIST, "Specify any tools to disable")
                .getStringList();
        Arrays.stream(list).forEach(blacklist::add);
    }

    public Set<String> getBlacklist() {
        return blacklist;
    }

    public static VanillaTools getInstance() {
        return instance;
    }

    /**
     * Registers all needed Forge event handlers.
     *
     * @param event The initialization event.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new BowHandler());
        MinecraftForge.EVENT_BUS.register(new HoeHandler());
        MinecraftForge.EVENT_BUS.register(new SwordHandler());
        MinecraftForge.EVENT_BUS.register(new ToolHandler());
    }
}
