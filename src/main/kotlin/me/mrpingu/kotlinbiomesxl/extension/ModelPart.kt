package me.mrpingu.kotlinbiomesxl.extension

import net.fabricmc.api.*
import net.fabricmc.api.EnvType.*
import net.minecraft.client.model.ModelPart

@Environment(CLIENT)
fun ModelPart.addCuboid(
	x: Int,
	y: Int,
	z: Int,
	width: Int,
	height: Int,
	length: Int,
	extra: Float = 0f,
	mirror: Boolean = this.mirror,
) = addCuboid(x.toFloat(), y.toFloat(), z.toFloat(), width.toFloat(), height.toFloat(), length.toFloat(), extra, mirror)

@Environment(CLIENT)
fun ModelPart.setPivot(x: Int, y: Int, z: Int) = setPivot(x.toFloat(), y.toFloat(), z.toFloat())
