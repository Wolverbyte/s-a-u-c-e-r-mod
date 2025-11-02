package net.mcreator.saucer.procedures;

import net.minecraft.world.entity.Entity;

public class SaucerOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putBoolean("Stop", false);
	}
}
