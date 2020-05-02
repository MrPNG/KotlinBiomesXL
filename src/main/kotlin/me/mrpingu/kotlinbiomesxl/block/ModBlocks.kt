package me.mrpingu.kotlinbiomesxl.block

import me.mrpingu.kotlinbiomesxl.ModRegistry
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry.*

object ModBlocks: ModRegistry<Block>(BLOCK) {
	
	fun registerAll() {
		register(
			"red_rock" to RedRockBlock
		)
	}
}
