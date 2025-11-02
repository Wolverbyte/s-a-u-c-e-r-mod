package net.mcreator.saucer.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.saucer.init.SaucerModParticleTypes;
import net.mcreator.saucer.entity.SaucerEntity;
import net.mcreator.saucer.SaucerMod;

import java.util.List;
import java.util.Comparator;

public class SaucerEntityTickSuckProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double particleRadius = 0;
		double DifX = 0;
		double root = 0;
		double DifY = 0;
		double DifZ = 0;
		double particleAmount = 0;
		if (entity.getPersistentData().getBoolean("Stop")) {
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
										entityiterator.setNoGravity(true);
										DifX = entity.getX() - entityiterator.getX();
										DifY = entity.getY() - entityiterator.getY();
										DifZ = entity.getZ() - entityiterator.getZ();
										root = Math.sqrt(Math.pow(DifX, 2) + Math.pow(DifY, 2) + Math.pow(DifZ, 2));
										entityiterator.setDeltaMovement(new Vec3(Math.pow(DifX / root, 1), Math.pow(DifY / root, 1), Math.pow(DifZ / root, 1)));
										if (entity.getPersistentData().getBoolean("Particle")) {
											particleAmount = 200;
											particleRadius = 2;
											for (int index0 = 0; index0 < (int) particleAmount; index0++) {
												world.addParticle((SimpleParticleType) (SaucerModParticleTypes.ALIEN_BEAM.get()), (x - 0 + Mth.nextDouble(RandomSource.create(), -0.1, 0.1) * particleRadius),
														(y - 10 + Mth.nextDouble(RandomSource.create(), 0, 5) * particleRadius), (z - 0 + Mth.nextDouble(RandomSource.create(), -0.1, 0.1) * particleRadius), 0, 0, 0);
												world.addParticle((SimpleParticleType) (SaucerModParticleTypes.ALIEN_BEAM.get()), (x - 0 + Mth.nextDouble(RandomSource.create(), -0.1, 0.1) * particleRadius),
														(y - 20 + Mth.nextDouble(RandomSource.create(), 0, 5) * particleRadius), (z - 0 + Mth.nextDouble(RandomSource.create(), -0.1, 0.1) * particleRadius), 0, 0, 0);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			SaucerMod.queueServerWork(200, () -> {
				entity.getPersistentData().putBoolean("Particle", false);
				entity.getPersistentData().putBoolean("Stop", false);
			});
		}
	}
}
