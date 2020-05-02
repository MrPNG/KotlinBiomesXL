package me.mrpingu.kotlinbiomesxl

import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.Registry.*

abstract class ModRegistry<T>(private val registry: Registry<T>) {
	
	protected fun register(name: String, element: T): T = register(registry, Identifier("kotlinbiomesxl", name), element)
	protected fun register(vararg elements: Pair<String, T>) = elements.forEach { (name, element) -> register(name, element) }
}
