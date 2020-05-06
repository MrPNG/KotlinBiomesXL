package me.mrpingu.kotlinbiomesxl.extension

import net.fabricmc.api.*
import net.fabricmc.api.EnvType.*
import net.minecraft.client.model.Model
import net.minecraft.client.model.ModelPart
import kotlin.math.PI

inline val Model.pi get() = PI.toFloat()

@Environment(CLIENT)
inline fun Model.modelPart(u: Int, v: Int, mirror: Boolean = false, init: ModelPart.() -> Unit) = ModelPart(this, u, v).apply { this.mirror = mirror }.apply(init)
