package me.mrpingu.kotlinbiomesxl.client.render.entity.model

import net.fabricmc.api.*
import net.minecraft.client.model.ModelPart
import net.minecraft.client.render.entity.model.CompositeEntityModel
import net.minecraft.entity.mob.EndermiteEntity
import net.minecraft.util.math.MathHelper
import kotlin.math.PI
import kotlin.math.abs

@Environment(EnvType.CLIENT)
object EndermiteEntityModel: CompositeEntityModel<EndermiteEntity>() {
	
	private val cuboids = arrayOf(intArrayOf(4, 3, 2), intArrayOf(6, 4, 5), intArrayOf(3, 3, 1), intArrayOf(1, 2, 1))
	private val textures = arrayOf(intArrayOf(0, 0), intArrayOf(0, 5), intArrayOf(0, 14), intArrayOf(0, 18))
	
	private val modelParts = mutableListOf<ModelPart>()
	
	init {
		var pivotZ = -3.5f
		
		cuboids.forEachIndexed { index, (width, height, length) ->
			val (u, v) = textures[index]
			
			modelParts += ModelPart(this, u, v).apply {
				addCuboid(width * -0.5f, 0.0f, length * -0.5f, width.toFloat(), height.toFloat(), length.toFloat())
				setPivot(0.0f, 24f - height, pivotZ)
			}
			
			if (index < cuboids.lastIndex) {
				val nextLength = cuboids[index + 1][2]
				pivotZ += (length + nextLength) * 0.5f
			}
		}
	}
	
	override fun getParts() = modelParts
	
	override fun setAngles(entity: EndermiteEntity, limbsDistance: Float, limbsPitch: Float, partialCycle: Float, yaw: Float, pitch: Float) {
		val pi = PI.toFloat()
		
		modelParts.forEachIndexed { index, modelPart ->
			modelPart.apply {
				this.yaw = MathHelper.cos(partialCycle * 0.9f + index * 0.15f * pi) * pi * 0.01f * (1 + abs(index - 2))
				pivotX = MathHelper.sin(partialCycle * 0.9f + index * 0.15f * pi) * pi * 0.1f * abs(index - 2)
			}
		}
	}
}
