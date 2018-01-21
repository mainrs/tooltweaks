package de.zerotask.minecraft.tooltweaks.common.handler

import de.zerotask.minecraft.tooltweaks.ToolTweaks.MODID
import de.zerotask.minecraft.tooltweaks.common.config.ToolTweaksConfiguration
import net.minecraft.item.ItemStack
import net.minecraftforge.common.config.Config
import net.minecraftforge.common.config.ConfigManager
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.util.Arrays

/**
 * Base class for all handlers used to control the various tools.
 */
open class AbstractHandler {

    /**
     * Holds all items that aren't allowed to be used.
     */
    private var blacklist: Set<String> = HashSet()

    /**
     * Holds all items that are allowed to be used no matter what.
     */
    private var whitelist: Set<String> = HashSet()

    init {
        // make sure to populate the blacklist and whitelist before using them.
        updateFields()
    }

    @SubscribeEvent
    fun onUpdatedConfiguration(event: ConfigChangedEvent) {
        if (event.modID == MODID) {
            // make sure to sync GUI config back to the actual file.
            ConfigManager.sync(MODID, Config.Type.INSTANCE)

            // TODO: update sets if configuration got updated (either from server or client side editing through GUI).
            updateFields()
        }
    }

    private fun updateFields() {
        // we need to filter since we do not know if the user actually adds entries or just adds empty lines.
        blacklist = HashSet(Arrays.asList(*ToolTweaksConfiguration.serverSide.blacklist).filterNotNull())
        whitelist = HashSet(Arrays.asList(*ToolTweaksConfiguration.serverSide.whitelist).filterNotNull())
    }

    protected fun isBannedItem(stack: ItemStack): Boolean {
        val itemId = stack.item.registryName!!.toString()

        return if (whitelist.contains(itemId)) {
            false
        } else {
            blacklist.contains(itemId)
        }
    }
}
