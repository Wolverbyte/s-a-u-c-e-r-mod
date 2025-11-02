
package net.mcreator.saucer.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.saucer.entity.SaucerEntity;
import net.mcreator.saucer.client.model.animations.SaucerAnimation;
import net.mcreator.saucer.client.model.ModelSaucer;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class SaucerRenderer extends MobRenderer<SaucerEntity, ModelSaucer<SaucerEntity>> {
	public SaucerRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelSaucer.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<SaucerEntity, ModelSaucer<SaucerEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("saucer:textures/entities/saucerlightlayer.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, SaucerEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
				this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(SaucerEntity entity) {
		return new ResourceLocation("saucer:textures/entities/saucer.png");
	}

	private static final class AnimatedModel extends ModelSaucer<SaucerEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<SaucerEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(SaucerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animateWalk(SaucerAnimation.idle, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState1, SaucerAnimation.door_open, ageInTicks, 1f);
				this.animate(entity.animationState2, SaucerAnimation.door_pause, ageInTicks, 1f);
				this.animate(entity.animationState3, SaucerAnimation.door_close, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(SaucerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}
