
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.saucer.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.saucer.client.particle.AlienBeamParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SaucerModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(SaucerModParticleTypes.ALIEN_BEAM.get(), AlienBeamParticle::provider);
	}
}
