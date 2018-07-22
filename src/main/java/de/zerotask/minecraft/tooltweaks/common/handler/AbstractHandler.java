package de.zerotask.minecraft.tooltweaks.common.handler;

import de.zerotask.minecraft.tooltweaks.ToolTweaks;
import de.zerotask.minecraft.tooltweaks.common.config.ToolTweaksConfiguration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.actors.threadpool.Arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AbstractHandler {
    private Set<String> blacklist = new HashSet<>();
    private Set<String> whitelist = new HashSet<>();

    public AbstractHandler() {
        updateFields();
    }

    private void updateFields() {
        List<String> list = Arrays.asList(ToolTweaksConfiguration.getValidConfiguration().blacklist);
        blacklist = list.stream().filter(Objects::nonNull).collect(Collectors.toSet());

        list = Arrays.asList(ToolTweaksConfiguration.getValidConfiguration().whitelist);
        whitelist = list.stream().filter(Objects::nonNull).collect(Collectors.toSet());
    }

    @SubscribeEvent
    public void onUpdatedConfiguration(ConfigChangedEvent event) {
        if(event.getModID() == ToolTweaks.MODID) {
            ConfigManager.sync(ToolTweaks.MODID, Config.Type.INSTANCE);
            updateFields();
        }
    }

    protected boolean isBannedItem(ItemStack stack) {
        String itemId = stack.getItem().getRegistryName().toString();
        if (whitelist.contains(itemId)) {
            return false;
        } else {
            return blacklist.contains(itemId);
        }
    }
}
