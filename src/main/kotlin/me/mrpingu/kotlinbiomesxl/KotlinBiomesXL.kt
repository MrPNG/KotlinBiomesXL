package me.mrpingu.kotlinbiomesxl

import me.mrpingu.kotlinbiomesxl.block.ModBlocks
import me.mrpingu.kotlinbiomesxl.entity.ModEntities
import me.mrpingu.kotlinbiomesxl.item.ModItems
import me.mrpingu.kotlinbiomesxl.world.biome.ModBiomes
import net.fabricmc.api.*

object KotlinBiomesXL: ModInitializer {
	
	override fun onInitialize() {
		ModBlocks.registerAll()
		ModItems.registerAll()
		ModBiomes.registerAll()
		ModEntities.registerAll()
	}
}
