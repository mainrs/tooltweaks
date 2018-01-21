package de.zerotask.minecraft.tooltweaks

import de.zerotask.minecraft.tooltweaks.ToolTweaks.MCVERSION
import de.zerotask.minecraft.tooltweaks.ToolTweaks.MODID
import de.zerotask.minecraft.tooltweaks.common.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager

@Mod(
        acceptedMinecraftVersions = MCVERSION,
        certificateFingerprint = "291a6dbeba80e50ca46c28c20d934b08a84efc8d", // comment out in dev!
        modid = MODID,
        modLanguage = "kotlin",
        modLanguageAdapter = "de.zerotask.minecraft.tooltweaks.language.KotlinAdapter",
        version = "1.0.7"
)
object ToolTweaks {

    const val MODID = "tooltweaks"
    const val MCVERSION = "[1.12,1.13)"

    val logger = LogManager.getLogger(this.javaClass.simpleName)

    @SidedProxy(
            clientSide = "de.zerotask.minecraft.tooltweaks.client.ClientProxy",
            serverSide = "de.zerotask.minecraft.tooltweaks.common.CommonProxy"
    )
    private lateinit var proxy: CommonProxy

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        proxy.preInit()
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init()
    }

    @Mod.EventHandler
    fun onFingerprintViolation(event: FMLFingerprintViolationEvent) {
        // we wil ignore it in dev environment. FIXME: does not work for kotlin?
        if(!event.isDirectory) {
            logger.warn("FML has failed to verify the integrity of ToolTweaks.")
            logger.warn("This mod could be running in a development environment, corrupted, or repackaged and " +
                    "distributed unofficially.")
            logger.warn("We do not provide support for modified versions! Proceed at your own risk.")

            logger.warn("Expected fingerprint: ${event.expectedFingerprint}")
            logger.warn("Source file: ${event.source}")
        }
    }
}
