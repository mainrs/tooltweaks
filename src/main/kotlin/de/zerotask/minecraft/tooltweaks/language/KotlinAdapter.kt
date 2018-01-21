package de.zerotask.minecraft.tooltweaks.language

import net.minecraftforge.fml.common.FMLModContainer
import net.minecraftforge.fml.common.ILanguageAdapter
import net.minecraftforge.fml.common.ModContainer
import net.minecraftforge.fml.relauncher.Side
import org.apache.logging.log4j.LogManager
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * TODO: PR forgelin for factory method support.
 */
class KotlinAdapter : ILanguageAdapter {

    private val logger = LogManager.getLogger("KotlinAdapter")

    @Throws(Exception::class)
    override fun getNewInstance(mod: FMLModContainer, objectClass: Class<*>, loader: ClassLoader, factoryMethod: Method?): Any? {
        logger.debug("Forge asked for a new instance of class ${objectClass.simpleName}.")

        // make sure to support static classes too.
        return when {
            // checks for forge factory methods.
            factoryMethod != null -> {
                logger.debug("Instantiating mod class using factory method: ${factoryMethod.name}.")
                factoryMethod(null)
            }

            // the default implementation, just instantiates a new class instance.
            else -> {
                logger.debug("Instantiating new class instance or returning singleton instance.")
                objectClass.kotlin.objectInstance?: objectClass.newInstance()
            }
        }
    }

    override fun supportsStatics(): Boolean {
        return false
    }

    @Throws(IllegalArgumentException::class, IllegalAccessException::class, NoSuchFieldException::class, SecurityException::class)
    override fun setProxy(target: Field, proxyTarget: Class<*>, proxy: Any) {
        logger.debug("Setting proxy for field name ${target.name} to $proxy")
        target.set(proxyTarget, proxy)
    }

    override fun setInternalProxies(mod: ModContainer, side: Side, loader: ClassLoader) = Unit
}
