package me.mrpingu.kotlinbiomesxl.extension

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider as _WeightedBlockStateProvider

fun WeightedBlockStateProvider(vararg blockStates: Pair<BlockState, Int>) =
	_WeightedBlockStateProvider().apply { blockStates.forEach { (blockState, weight) -> addState(blockState, weight) } }
