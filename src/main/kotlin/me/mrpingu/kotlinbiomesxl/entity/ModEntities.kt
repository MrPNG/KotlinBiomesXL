package me.mrpingu.kotlinbiomesxl.entity

import me.mrpingu.kotlinbiomesxl.ModRegistry
import me.mrpingu.kotlinbiomesxl.client.render.entity.GoodmiteEntityRenderer
import me.mrpingu.kotlinbiomesxl.client.render.entity.PenguinEntityRenderer
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder.*
import net.minecraft.entity.EntityCategory.*
import net.minecraft.entity.EntityDimensions.*
import net.minecraft.entity.EntityType
import net.minecraft.util.registry.Registry.*
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.INSTANCE as entityRendererRegistry

object ModEntities: ModRegistry<EntityType<*>>(ENTITY_TYPE) {
	
	val GOODMITE: EntityType<GoodmiteEntity> = create(CREATURE, ::GoodmiteEntity).size(fixed(1f, 1f)).build()
	val PENGUIN: EntityType<PenguinEntity> = create(CREATURE, ::PenguinEntity).size(fixed(0.4f, 1.36f)).build()
	
	fun registerAll() {
		with(entityRendererRegistry) {
			register(GOODMITE) { entityRenderDispatcher, _ -> GoodmiteEntityRenderer(entityRenderDispatcher) }
			register(PENGUIN) { entityRenderDispatcher, _ -> PenguinEntityRenderer(entityRenderDispatcher) }
		}
		
		register(
			"goodmite" to GOODMITE,
			"penguin" to PENGUIN
		)
	}
}
