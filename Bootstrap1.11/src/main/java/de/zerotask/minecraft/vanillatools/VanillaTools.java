package de.zerotask.minecraft.vanillatools;

import de.zerotask.minecraft.vanillatools.handler.*;
import de.zerotask.minecraft.vanillatools.reference.IReference;

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
        useMetadata = true,
        acceptedMinecraftVersions = "[1.10,1.11)")
public class VanillaTools implements VanillaToolsInterface {

    /**
     * The configuration used to read all blacklisted items.
     */
    private VanillaConfiguration configuration;

    private CompatInterface compatInstance = new Compat();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configuration = new VanillaConfiguration(new Configuration(event.getSuggestedConfigurationFile()));
    }

    /**
     * Registers all needed Forge event handlers.
     *
     * @param event The initialization event.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new BowHandler(this));
        MinecraftForge.EVENT_BUS.register(new HoeHandler(this));
        MinecraftForge.EVENT_BUS.register(new SwordHandler(this));
        MinecraftForge.EVENT_BUS.register(new ToolHandler(this));
        MinecraftForge.EVENT_BUS.register(new TooltipHandler(this));
    }

    @Override
    public CompatInterface getCompatInterface() {
        return this.compatInstance;
    }

    @Override
    public VanillaConfiguration getConfiguration() {
        return this.configuration;
    }
}
