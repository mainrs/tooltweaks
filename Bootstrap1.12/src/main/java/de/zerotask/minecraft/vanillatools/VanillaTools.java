package de.zerotask.minecraft.vanillatools;

import de.zerotask.minecraft.vanillatools.handler.BowHandler;
import de.zerotask.minecraft.vanillatools.handler.HoeHandler;
import de.zerotask.minecraft.vanillatools.handler.SwordHandler;
import de.zerotask.minecraft.vanillatools.handler.ToolHandler;
import de.zerotask.minecraft.vanillatools.handler.TooltipHandler;
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
        acceptedMinecraftVersions = "[1.12,1.13)")
public class VanillaTools implements VanillaToolsInterface {

    @Mod.Instance
    private static VanillaTools instance;

    private CompatInterface compatIntance = new Compat();

    /**
     * The configuration used to read all blacklisted items.
     */
    private VanillaConfiguration configuration;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configuration = new VanillaConfiguration(new Configuration(event.getSuggestedConfigurationFile()));
    }

    @Override
    public CompatInterface getCompatInterface() {
        return this.compatIntance;
    }

    public VanillaConfiguration getConfiguration() {
        return configuration;
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
        MinecraftForge.EVENT_BUS.register(new BowHandler(this));
        MinecraftForge.EVENT_BUS.register(new HoeHandler(this));
        MinecraftForge.EVENT_BUS.register(new SwordHandler(this));
        MinecraftForge.EVENT_BUS.register(new ToolHandler(this));
        MinecraftForge.EVENT_BUS.register(new TooltipHandler(this));
    }
}
