package com.eeeab.eeeabsmobs.client.model.entity;

import com.eeeab.animate.client.model.EMHierarchicalModel;
import com.eeeab.animate.server.animation.Animation;
import com.eeeab.eeeabsmobs.client.model.animation.AnimationGulingSentinel;
import com.eeeab.eeeabsmobs.sever.entity.guling.EntityGulingSentinel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ModelGulingSentinel extends EMHierarchicalModel<EntityGulingSentinel> {
    private final ModelPart root;
    private final ModelPart core;
    private final ModelPart upper;
    private final ModelPart block4;
    private final ModelPart block3;
    private final ModelPart block2;
    private final ModelPart block1;
    private final ModelPart lower;
    private final ModelPart block8;
    private final ModelPart block7;
    private final ModelPart block6;
    private final ModelPart block5;

    public ModelGulingSentinel(ModelPart root) {
        this.root = root.getChild("root");
        this.core = this.root.getChild("core");
        this.upper = this.root.getChild("upper");
        this.block4 = this.upper.getChild("block4");
        this.block3 = this.upper.getChild("block3");
        this.block2 = this.upper.getChild("block2");
        this.block1 = this.upper.getChild("block1");
        this.lower = this.root.getChild("lower");
        this.block8 = this.lower.getChild("block8");
        this.block7 = this.lower.getChild("block7");
        this.block6 = this.lower.getChild("block6");
        this.block5 = this.lower.getChild("block5");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
        PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-1.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition upper = root.addOrReplaceChild("upper", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 0.0F));
        PartDefinition block4 = upper.addOrReplaceChild("block4", CubeListBuilder.create().texOffs(0, 56).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, 4.0F));
        PartDefinition block3 = upper.addOrReplaceChild("block3", CubeListBuilder.create().texOffs(0, 56).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 0.0F, 4.0F));
        PartDefinition block2 = upper.addOrReplaceChild("block2", CubeListBuilder.create().texOffs(0, 75).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 0.0F, -4.0F));
        PartDefinition block1 = upper.addOrReplaceChild("block1", CubeListBuilder.create().texOffs(0, 75).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, -4.0F));
        PartDefinition lower = root.addOrReplaceChild("lower", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));
        PartDefinition block8 = lower.addOrReplaceChild("block8", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, -4.0F));
        PartDefinition block7 = lower.addOrReplaceChild("block7", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 0.0F, -4.0F));
        PartDefinition block6 = lower.addOrReplaceChild("block6", CubeListBuilder.create().texOffs(0, 37).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 4.0F));
        PartDefinition block5 = lower.addOrReplaceChild("block5", CubeListBuilder.create().texOffs(0, 37).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 0.0F, 4.0F));
        return LayerDefinition.create(meshdefinition, 32, 128);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(EntityGulingSentinel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //LookAt
        lookAtAnimation(netHeadYaw, headPitch, 1F, this.core);
        float delta = ageInTicks - entity.tickCount;
        float frame = entity.frame + delta;
        //Idle
        Animation animation = entity.getAnimation();
        if (entity.isActive() && animation != entity.activeAnimation && animation != entity.deactivateAnimation && animation != entity.getDeathAnimation()) {
            float walkSpeed = 0.4F;
            float walkDegree = 0.6F;
            setStaticRotationPoint(root, 0F, -12F, 0F);
            setStaticRotationPoint(block1, 2F, -2F, -2F);
            setStaticRotationPoint(block6, -2F, 2F, 2F);
            setStaticRotationPoint(block2, -2F, -2F, -2F);
            setStaticRotationPoint(block5, 2F, 2F, 2F);
            setStaticRotationPoint(block3, -2F, -2F, 2F);
            setStaticRotationPoint(block7, 2F, 2F, -2F);
            setStaticRotationPoint(block4, 2F, -2F, 2F);
            setStaticRotationPoint(block8, -2F, 2F, -2F);
            this.bob(root, walkSpeed, walkDegree, false, frame, 1);
            this.bob(block1, walkSpeed * 0.8F, walkDegree * 1.25F, false, frame, 1);
            this.bob(block6, walkSpeed * 0.8F, walkDegree * 1.2F, false, frame, 1);
            this.bob(block2, walkSpeed * 0.78F, walkDegree * 1.15F, false, frame, 1);
            this.bob(block5, walkSpeed * 0.78F, walkDegree * 1.15F, false, frame, 1);
            this.bob(block3, walkSpeed * 0.76F, walkDegree * 1.1F, false, frame, 1);
            this.bob(block7, walkSpeed * 0.76F, walkDegree * 1.1F, false, frame, 1);
            this.bob(block4, walkSpeed * 0.74F, walkDegree * 1.05F, false, frame, 1);
            this.bob(block8, walkSpeed * 0.74F, walkDegree * 1.05F, false, frame, 1);
            this.upper.yRot += Mth.cos(frame * 0.01F) * 180F * Mth.PI / 180F;
            this.lower.yRot += Mth.cos(frame * 0.01F) * 180F * Mth.PI / 180F;
        }
        if (animation == entity.hurtAnimation) {
            this.root.x += (float) (Math.random() - 0.5) * 1.5F;
            this.root.y += (float) (Math.random() - 0.5) * 1.5F;
            this.root.z += (float) (Math.random() - 0.5) * 1.5F;
        }
        this.animate(entity.dieAnimation, AnimationGulingSentinel.DEACTIVATE, ageInTicks);
        this.animate(entity.activeAnimation, AnimationGulingSentinel.ACTIVE, ageInTicks);
        this.animate(entity.deactivateAnimation, AnimationGulingSentinel.DEACTIVATE, ageInTicks);
        this.animate(entity.shootLaserAnimation, AnimationGulingSentinel.SHOOT_LASER, ageInTicks);
    }
}