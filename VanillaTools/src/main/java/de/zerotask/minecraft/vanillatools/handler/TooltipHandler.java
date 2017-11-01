package de.zerotask.minecraft.vanillatools.handler;

import java.util.List;

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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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

	@SubscribeEvent
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
						tooltip.add(TextFormatting.DARK_RED + "This pickaxe cannot mine anything!");
						valid = true;
					} else if (item instanceof ItemAxe) {
						// If the item is an axe
						tooltip.add(TextFormatting.DARK_RED + "This axe cannot chop anything!");
						valid = true;
					} else if (item instanceof ItemSpade) {
						// If the item is a shovel
						tooltip.add(TextFormatting.DARK_RED + "This shovel cannot dig anything!");
						valid = true;
					} else if (item instanceof ItemSword) {
						// If the item is sword
						tooltip.add(TextFormatting.DARK_RED + "This sword cannot damage any entity!");
						valid = true;
					} else if (item instanceof ItemBow) {
						// If the item is bow
						tooltip.add(TextFormatting.DARK_RED + "This bow cannot damage any entity!");
						valid = true;
					} else if (item instanceof ItemHoe) {
						// If the item is hoe
						tooltip.add(TextFormatting.DARK_RED + "This hoe cannot hoe anything!");
						valid = true;
					}

					if (valid) {
						tooltip.add(TextFormatting.DARK_RED + "It can only be used for crafting");
					}
				}

			}
		}

	}
}
