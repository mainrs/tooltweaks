package de.zerotask.minecraft.vanillatools;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.minecraftforge.common.config.Configuration;

/**
 * Created by Sven on 13.05.2017.
 */
public class VanillaConfiguration {

    private Configuration configuration;

    private static final String CATEGORY_GENERAL = "general";
    private static final String KEY_BLACKLIST = "blacklist";
    private static final String DESC_BLACKLIST = "Specify which items to disable";
    private static final String KEY_WHITELIST = "whitelist";
    private static final String DESC_WHITELIST = "Specify which items to enable, no matter what";
    private static final String KEY_DISABLE_ALL_EXCEPT_TIC = "disableAllToolsExceptTiC";
    private static final String DESC_DISABLE_ALL = "Disable all tools (even mod tools)";

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

    private boolean disableAllTools;

    private Set<String> blacklist;

    private Set<String> whitelist;

    public VanillaConfiguration(Configuration configuration) {
        this.configuration = configuration;
        blacklist = new HashSet<>();
        whitelist = new HashSet<>();

        try {
            configuration.load();
            init();
        } finally {
            if(configuration.hasChanged()) {
                configuration.save();
            }
        }
    }

    public boolean isDisableAllTools() {
        return disableAllTools;
    }

    public Set<String> getBlacklist() {
        return blacklist;
    }

    public Set<String> getWhitelist() {
        return whitelist;
    }

    public void init() {
        // disable all
        disableAllTools = configuration.get(CATEGORY_GENERAL, KEY_DISABLE_ALL_EXCEPT_TIC, false, DESC_DISABLE_ALL).getBoolean();
        if(!disableAllTools) {
            // blacklist
            String[] list = configuration.get(CATEGORY_GENERAL, KEY_BLACKLIST, DEFAULT_BLACKLIST, DESC_BLACKLIST)
                    .getStringList();
            Arrays.stream(list).forEach(blacklist::add);
        } else {
            // just generate an empty array if all are disabled
            configuration.get(CATEGORY_GENERAL, KEY_BLACKLIST, new String[0], DESC_BLACKLIST)
                    .getStringList();
        }

        // whitelist
        String[] list = configuration.get(CATEGORY_GENERAL, KEY_WHITELIST, new String[0], DESC_WHITELIST)
                .getStringList();
        Arrays.stream(list).forEach(whitelist::add);
    }
}
