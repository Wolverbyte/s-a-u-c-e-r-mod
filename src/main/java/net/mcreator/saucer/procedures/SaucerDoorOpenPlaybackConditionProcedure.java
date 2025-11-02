package net.mcreator.saucer.procedures;

import net.minecraft.world.entity.Entity;

public class SaucerDoorOpenPlaybackConditionProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.getPersistentData().getBoolean("Hatch1");
	}
}
