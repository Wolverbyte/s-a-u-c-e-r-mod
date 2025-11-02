package net.mcreator.saucer.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.saucer.init.SaucerModEntities;
import net.mcreator.saucer.entity.SaucerEntity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class SaucerNaturalEntitySpawningConditionProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level0 && _level0.isVillage(BlockPos.containing(x, y, z))) {
			if (Math.random() < 0.0015 && !(world instanceof Level _lvl1 && _lvl1.isDay()) && !(!world.getEntitiesOfClass(SaucerEntity.class, AABB.ofSize(new Vec3(x, y, z), 100, 100, 100), e -> true).isEmpty())) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = SaucerModEntities.SAUCER.get().spawn(_level, BlockPos.containing(x, y + 20, z), MobSpawnType.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
					}
				}
			}
		}
	}
}
