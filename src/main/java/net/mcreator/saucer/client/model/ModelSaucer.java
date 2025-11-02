package net.mcreator.saucer.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelSaucer<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("saucer", "model_saucer"), "main");
	public final ModelPart saucer;
	public final ModelPart base;
	public final ModelPart door;

	public ModelSaucer(ModelPart root) {
		this.saucer = root.getChild("saucer");
		this.base = this.saucer.getChild("base");
		this.door = this.base.getChild("door");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition saucer = partdefinition.addOrReplaceChild("saucer", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition base = saucer.addOrReplaceChild("base",
				CubeListBuilder.create().texOffs(0, 0).addBox(-154.0F, -8.0F, -154.0F, 308.0F, 17.0F, 308.0F, new CubeDeformation(0.0F)).texOffs(0, 325).addBox(-112.5F, -36.0F, -112.5F, 225.0F, 49.0F, 225.0F, new CubeDeformation(0.0F))
						.texOffs(0, 599).addBox(-80.0F, 13.0F, -80.0F, 160.0F, 27.0F, 160.0F, new CubeDeformation(0.0F)).texOffs(640, 599).addBox(-70.0F, -52.0F, -70.0F, 140.0F, 16.0F, 140.0F, new CubeDeformation(0.0F)).texOffs(519, 855)
						.addBox(-69.5F, -84.0F, -69.5F, 139.0F, 48.0F, 139.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -47.0F, 0.0F));
		PartDefinition door = base.addOrReplaceChild("door", CubeListBuilder.create().texOffs(0, 786).addBox(-24.0F, 0.1F, -24.0F, 48.0F, 0.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 40.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 2048, 2048);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		saucer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
