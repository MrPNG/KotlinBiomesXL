package me.mrpingu.kotlinbiomesxl.client.render.entity

import me.mrpingu.kotlinbiomesxl.client.render.entity.model.EndermiteEntityModel
import net.fabricmc.api.*
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.entity.mob.EndermiteEntity
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class EndermiteEntityRenderer(entityRenderDispatcher: EntityRenderDispatcher):
	MobEntityRenderer<EndermiteEntity, EndermiteEntityModel>(entityRenderDispatcher, EndermiteEntityModel, 0.3f) {
	
	override fun getLyingAngle(endermiteEntity: EndermiteEntity) = 180f
	
	override fun getTexture(endermiteEntity: EndermiteEntity) = skin
	
	companion object {
		
		private val skin = Identifier("textures/entity/endermite.png")
	}
}
