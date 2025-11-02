
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.saucer.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.saucer.SaucerMod;

public class SaucerModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SaucerMod.MODID);
	public static final RegistryObject<SoundEvent> HOVER = REGISTRY.register("hover", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("saucer", "hover")));
	public static final RegistryObject<SoundEvent> LASER = REGISTRY.register("laser", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("saucer", "laser")));
}
