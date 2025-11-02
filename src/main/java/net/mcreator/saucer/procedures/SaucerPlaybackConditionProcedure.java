package net.mcreator.saucer.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.saucer.entity.SaucerEntity;
import net.mcreator.saucer.SaucerMod;

import java.util.List;
import java.util.Comparator;

public class SaucerPlaybackConditionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double particleRadius = 0;
		double particleAmount = 0;
		double DifX = 0;
		double root = 0;
		double DifY = 0;
		double DifZ = 0;
		Entity LocalEntity = null;
		entity.getPersistentData().putDouble("tagName", (entity.getPersistentData().getDouble("tagName") + 1));
		int horizontalRadiusSphere = (int) 7 - 1;
		int verticalRadiusSphere = (int) 20 - 1;
		int yIterationsSphere = verticalRadiusSphere;
		for (int yi = -yIterationsSphere; yi <= yIterationsSphere; yi++) {
			for (int xi = -horizontalRadiusSphere; xi <= horizontalRadiusSphere; xi++) {
				for (int zi = -horizontalRadiusSphere; zi <= horizontalRadiusSphere; zi++) {
					double distanceSq = (xi * xi) / (double) (horizontalRadiusSphere * horizontalRadiusSphere) + (yi * yi) / (double) (verticalRadiusSphere * verticalRadiusSphere)
							+ (zi * zi) / (double) (horizontalRadiusSphere * horizontalRadiusSphere);
					if (distanceSq <= 1.0) {
						{
							final Vec3 _center = new Vec3(x + xi, y + yi, z + zi);
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
							for (Entity entityiterator : _entfound) {
								if (!(entityiterator instanceof SaucerEntity)) {
									if (entity.getPersistentData().getDouble("tagName") > 200) {
										LocalEntity = (Entity) world.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(new Vec3(x + xi, y + yi, z + zi), 20, 20, 20), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x + xi, y + yi, z + zi)).findFirst().orElse(null);
									}
									if (((Entity) world.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(new Vec3(x + xi, y + yi, z + zi), 7, 7, 7), e -> true).stream().sorted(new Object() {
										Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
											return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
										}
									}.compareDistOf(x + xi, y + yi, z + zi)).findFirst().orElse(null)) == null) {
										LocalEntity = (Entity) world.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(new Vec3(x + xi, y + yi, z + zi), 30, 30, 30), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x + xi, y + yi, z + zi)).findFirst().orElse(null);
									}
									if (entity.getPersistentData().getDouble("tagName") > 250) {
										entity.getPersistentData().putDouble("tagName", 0);
										for (int index0 = 0; index0 < 10; index0++) {
											if (entity instanceof Mob _entity)
												_entity.getNavigation().moveTo((LocalEntity.getX()), (LocalEntity.getY() + 20), (LocalEntity.getZ()), 1);
										}
										entity.getPersistentData().putBoolean("Stop", true);
										entity.setDeltaMovement(new Vec3(0, 0, 0));
										entity.getPersistentData().putBoolean("Particle", true);
										entity.getPersistentData().putBoolean("Hatch1", true);
										SaucerMod.queueServerWork(10, () -> {
											entity.getPersistentData().putBoolean("Hatch1", false);
											entity.getPersistentData().putBoolean("Hatch2", true);
										});
										SaucerEntityTickSuckProcedure.execute(world, x, y, z, entity);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
