package me.mrpingu.kotlinbiomesxl.item

import me.mrpingu.kotlinbiomesxl.ModRegistry
import me.mrpingu.kotlinbiomesxl.block.RedRockBlock
import net.fabricmc.fabric.api.client.itemgroup.*
import net.minecraft.block.Block
import net.minecraft.item.*
import net.minecraft.item.Item.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry.*

object ModItems: ModRegistry<Item>(ITEM) {
	
	val modItemGroup: ItemGroup = FabricItemGroupBuilder.build(Identifier("kotlinbiomesxl", "all")) { ItemStack(CattailItem) }
	val modItemSettings: Settings = Settings().group(modItemGroup)
	
	fun registerAll() {
		register(
			"cattail" to CattailItem,
		)
		
		registerBlockItems(
			"red_rock" to RedRockBlock
		)
	}
	
	private fun registerBlockItem(name: String, block: Block): Item = register(ITEM, Identifier("kotlinbiomesxl", name), BlockItem(block, modItemSettings))
	private fun registerBlockItems(vararg blocks: Pair<String, Block>) = blocks.forEach { (name, block) -> registerBlockItem(name, block) }
}
