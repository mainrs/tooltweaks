package de.zerotask.minecraft.vanillatools.handler;

import java.util.List;
import java.util.ArrayList;

import de.zerotask.minecraft.vanillatools.VanillaToolsInterface;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

/**
 * A class that modify the tooltip of tools and weapons modified by the mod
 *
 * @author Sylvain VISTE (Fanged Hex)
 * @version 1.0
 */
public class TooltipHandler extends AbstractHandler {
	public TooltipHandler(VanillaToolsInterface modInstance) {
		super(modInstance);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOWEST)	
	public void onItemToolTip(ItemTooltipEvent event) {
		// When "we" ask for the item tooltip
		
		if (event.getEntityPlayer() != null) {
			if (isBannedItem(event.getItemStack())) {
				// If the item is banned

				boolean valid = false;
				Item item = event.getItemStack().getItem();
				List<String> tooltip = event.getToolTip();

				if (tooltip != null) {					
					if (item instanceof ItemPickaxe) {
						// If the item is a pickaxe
						tooltip.addAll( this.createItemTooltip("pickaxe", "mine anything") );
					} else if (item instanceof ItemAxe) {
						// If the item is an axe
						tooltip.addAll( this.createItemTooltip("axe", "chop anything") );
					} else if (item instanceof ItemSpade) {
						// If the item is a shovel
						tooltip.addAll( this.createItemTooltip("shovel", "dig anything") );
					} else if (item instanceof ItemSword) {
						// If the item is sword
						tooltip.addAll( this.createItemTooltip("sword", "damage any entity") );
					} else if (item instanceof ItemBow) {
						// If the item is bow
						tooltip.addAll( this.createItemTooltip("bow", "damage any entity") );
					} else if (item instanceof ItemHoe) {
						// If the item is hoe
						tooltip.addAll( this.createItemTooltip("hoe", "hoe anything") );
					}
				}

			}
		}

	}

	private List<String> createItemTooltip(String itemName, String action) {
		List<String> tooltip = new ArrayList<String>();

		// Empty line
		tooltip.add("");

		// Formatted line 1
		tooltip.add(TextFormatting.DARK_RED + String.format("This %s cannot %s !", itemName, action) );

		// Line 2
		tooltip.add(TextFormatting.DARK_RED + "It can only be used for crafting");

		return tooltip;
	}
}
