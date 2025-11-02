package net.mcreator.saucer.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;

import net.mcreator.saucer.entity.SaucerEntity;
import net.mcreator.saucer.SaucerMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class SaucerDespawnProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof SaucerEntity && world instanceof Level _lvl1 && _lvl1.isDay()) {
			entity.setDeltaMovement(new Vec3(0, 6, 0));
			SaucerMod.queueServerWork(100, () -> {
				if (!entity.level().isClientSide())
					entity.discard();
			});
		}
	}
}
