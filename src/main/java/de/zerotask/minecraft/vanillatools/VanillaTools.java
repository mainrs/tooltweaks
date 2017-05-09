package de.zerotask.minecraft.vanillatools;

import de.zerotask.minecraft.vanillatools.handler.BowHandler;
import de.zerotask.minecraft.vanillatools.handler.HoeHandler;
import de.zerotask.minecraft.vanillatools.handler.SwordHandler;
import de.zerotask.minecraft.vanillatools.handler.ToolHandler;
import de.zerotask.minecraft.vanillatools.reference.IReference;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * A simple mod used to disable all vanilla bows, hoes, swords and tools.
 *
 * @author Sven Lechner (SirWindfield)
 * @version 1.0
 */
@Mod(modid = IReference.ID, version = IReference.VERSION)
public class VanillaTools {

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
