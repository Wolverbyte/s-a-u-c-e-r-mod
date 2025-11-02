
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.saucer.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.item.Item;

import net.mcreator.saucer.SaucerMod;

public class SaucerModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, SaucerMod.MODID);
	public static final RegistryObject<Item> SAUCER_SPAWN_EGG = REGISTRY.register("saucer_spawn_egg", () -> new ForgeSpawnEggItem(SaucerModEntities.SAUCER, -1, -13369549, new Item.Properties()));
	// Start of user code block custom items
	// End of user code block custom items
}
