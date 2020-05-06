package me.mrpingu.kotlinbiomesxl.client.render.entity

import me.mrpingu.kotlinbiomesxl.client.render.entity.model.PenguinEntityModel
import me.mrpingu.kotlinbiomesxl.entity.PenguinEntity
import net.fabricmc.api.*
import net.fabricmc.api.EnvType.*
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier

@Environment(CLIENT)
class PenguinEntityRenderer(entityRenderDispatcher: EntityRenderDispatcher):
	MobEntityRenderer<PenguinEntity, PenguinEntityModel>(entityRenderDispatcher, PenguinEntityModel, 0.3f) {
	
	override fun getTexture(penguinEntity: PenguinEntity) = skin
	
	override fun scale(livingEntity: PenguinEntity, matrixStack: MatrixStack, partialCycle: Float) {
		matrixStack.scale(0.75f, 0.75f, 0.75f)
	}
	
	companion object {
		
		private val skin = Identifier("kotlinbiomesxl:textures/entity/emperor_penguin.png")
	}
}
