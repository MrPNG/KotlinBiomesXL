package me.mrpingu.kotlinbiomesxl.client.render.entity.model

import me.mrpingu.kotlinbiomesxl.entity.PenguinEntity
import me.mrpingu.kotlinbiomesxl.extension.*
import net.fabricmc.api.*
import net.fabricmc.api.EnvType.*
import net.minecraft.client.render.entity.model.AnimalModel
import net.minecraft.util.math.MathHelper.*

@Environment(CLIENT)
object PenguinEntityModel: AnimalModel<PenguinEntity>() {
	
	private val head = modelPart(0, 0) {
		addCuboid(-3, -7, -3, 6, 6, 6)
		setPivot(0, 2, 0)
	}
	private val beakTop = modelPart(0, 24) {
		addCuboid(-1, -4, -6, 2, 1, 3)
		setPivot(0, 2, 0)
	}
	private val beakBottom = modelPart(0, 28) {
		addCuboid(-1, -3, -5, 2, 1, 2)
		setPivot(0, 2, 0)
	}
	private val torso = modelPart(24, 0) {
		addCuboid(-5, -2, -3, 10, 20, 7)
		setPivot(0, 3, 0)
	}
	private val leftWing = modelPart(16, 12) {
		addCuboid(5, 0, -2, 1, 14, 3)
		setPivot(0, 3, 0)
	}
	private val rightWing = modelPart(16, 12, true) {
		addCuboid(-6, 0, -2, 1, 14, 3)
		setPivot(0, 3, 0)
	}
	private val tailTop = modelPart(0, 16) {
		addCuboid(-2, 16, 4, 4, 2, 1)
		setPivot(0, 3, 0)
	}
	private val tailBottom = modelPart(0, 19) {
		addCuboid(-2, 18, 2, 4, 1, 4)
		setPivot(0, 3, 0)
	}
	private val leftLeg = modelPart(0, 12) {
		addCuboid(2, 18, 0, 2, 3, 1)
		setPivot(0, 3, 0)
	}
	private val rightLeg = modelPart(0, 12, true) {
		addCuboid(-4, 18, 0, 2, 3, 1)
		setPivot(0, 3, 0)
	}
	private val leftFoot = modelPart(6, 12) {
		addCuboid(2, 20, -3, 2, 1, 3)
		setPivot(0, 3, 0)
	}
	private val rightFoot = modelPart(6, 12, true) {
		addCuboid(-4, 20, -3, 2, 1, 3)
		setPivot(0, 3, 0)
	}
	
	private val torsoParts = listOf(torso, leftWing, rightWing, tailTop, tailBottom)
	private val legParts = listOf(leftLeg, rightLeg, leftFoot, rightFoot)
	
	override fun getBodyParts() = torsoParts + legParts
	override fun getHeadParts() = listOf(head, beakTop, beakBottom)
	
	override fun setAngles(penguinEntity: PenguinEntity, limbAngle: Float, _limbDelta: Float, animationProgress: Float, _headYaw: Float, _headPitch: Float) {
		val limbDelta = _limbDelta.coerceAtMost(0.15f)
		
		val headYaw = _headYaw * pi / 180
		val headPitch = _headPitch * pi / 180
		headParts.forEach { it.yaw = headYaw; it.pitch = headPitch }
		
		val torsoYaw = -cos(limbAngle * 2) * 1.5f * limbDelta
		val torsoPitch = cos(limbAngle * 4) * 0.75f * limbDelta
		val torsoRoll = -cos(limbAngle * 2) * 0.5f * limbDelta
		torsoParts.forEach { torsoPart ->
			torsoPart.yaw = torsoYaw
			torsoPart.pitch = torsoPitch
			torsoPart.roll = torsoRoll
		}
		
		val legPitch = cos(limbAngle * 2) * 0.4f * limbDelta
		legParts.forEachIndexed { index, legPart ->
			legPart.yaw = torsoYaw
			legPart.pitch = torsoPitch + (if (index % 2 == 0) 1 else -1) * legPitch
		}
		
		val wingPitch = cos(animationProgress * 0.05f) * pi * 0.025f
		val wingRoll = (cos(animationProgress * 0.125f) * 0.5f + 0.5f) * pi * 0.0625f
		leftWing.pitch -= wingPitch
		leftWing.roll = -wingRoll
		rightWing.pitch += wingPitch
		rightWing.roll = wingRoll
	}
}
