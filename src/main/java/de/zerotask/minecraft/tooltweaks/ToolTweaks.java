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
        certificateFingerprint = "291a6dbeba80e50ca46c28c20d934b08a84efc8d", // comment out in dev!
        modid = ToolTweaks.MODID,
        version = "1.0.7"
)
public class ToolTweaks {

    public static final String MODID = "tooltweaks";
    public static final String MCVERSION = "[1.12,1.13)";
    private static final Logger logger = LogManager.getLogger(ToolTweaks.class.getName());

    @SidedProxy(
            clientSide = "de.zerotask.minecraft.tooltweaks.client.ClientProxy",
            serverSide = "de.zerotask.minecraft.tooltweaks.common.CommonProxy"
    )
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
        // we wil ignore it in dev environment. FIXME: does not work for kotlin?
        if(!event.isDirectory()) {
            logger.warn("FML has failed to verify the integrity of ToolTweaks.");
            logger.warn("This mod could be running in a development environment, corrupted, or repackaged and " +
                    "distributed unofficially.");
            logger.warn("We do not provide support for modified versions! Proceed at your own risk.");

            logger.warn("Expected fingerprint: ${event.expectedFingerprint}");
            logger.warn("Source file: ${event.source}");
        }
    }
}
