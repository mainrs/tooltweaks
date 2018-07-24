package de.zerotask.minecraft.tooltweaks;

import de.zerotask.minecraft.tooltweaks.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        acceptedMinecraftVersions = ToolTweaks.MCVERSION,
        certificateFingerprint = "291a6dbeba80e50ca46c28c20d934b08a84efc8d",
        modid = ToolTweaks.MODID,
        version = "1.0.8"
)
public class ToolTweaks {

    public static final String MODID = "tooltweaks";
    public static final String MCVERSION = "[1.12,1.13)";
    public static final Logger LOG = LogManager.getLogger(ToolTweaks.class.getName());

    @SidedProxy(
            clientSide = "de.zerotask.minecraft.tooltweaks.client.ClientProxy",
            serverSide = "de.zerotask.minecraft.tooltweaks.server.ServerProxy"
    )
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOG.debug("PreInitializing ToolTweaks");
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOG.debug("Initializing ToolTweaks");
        proxy.init();
    }

    @Mod.EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
        if (!event.isDirectory()) {
            LOG.warn("FML has failed to verify the integrity of ToolTweaks.");
            LOG.warn("This mod could be running in a development environment, corrupted, or repackaged and " +
                    "distributed unofficially.");
            LOG.warn("We do not provide support for modified versions! Proceed at your own risk.");

            LOG.warn("Expected fingerprint: {}", event.getExpectedFingerprint());
            LOG.warn("Source file: {}", event.getSource());
        }
    }
}
