package me.mrpingu.kotlinbiomesxl.client.render.entity

import me.mrpingu.kotlinbiomesxl.client.render.entity.model.GoodmiteEntityModel
import me.mrpingu.kotlinbiomesxl.entity.GoodmiteEntity
import net.fabricmc.api.*
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class GoodmiteEntityRenderer(entityRenderDispatcher: EntityRenderDispatcher):
	MobEntityRenderer<GoodmiteEntity, GoodmiteEntityModel>(entityRenderDispatcher, GoodmiteEntityModel, 0.3f) {
	
	override fun getLyingAngle(goodmiteEntity: GoodmiteEntity) = 180f
	
	override fun getTexture(endermiteEntity: GoodmiteEntity) = skin
	
	companion object {
		
		private val skin = Identifier("kotlinbiomesxl:textures/entity/goodmite.png")
	}
}
