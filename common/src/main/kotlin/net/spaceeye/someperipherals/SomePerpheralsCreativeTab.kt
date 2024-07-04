package net.spaceeye.someperipherals

import dev.architectury.registry.CreativeTabRegistry
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

object SomePerpheralsCreativeTab {
    val TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(SomePeripherals.MOD_ID, Registries.CREATIVE_MODE_TAB)

    val TAB: RegistrySupplier<CreativeModeTab> = TABS.register(
            "tab"
    ) { ->
        CreativeTabRegistry.create(
                Component.translatable("someperipherals_tab")
        ) { ->
            BuiltInRegistries.ITEM.get(SomePeripheralsItems.LOGO.key)?.asItem()?.defaultInstance ?: ItemStack.EMPTY
        }
    }

    fun register()
    {
        TABS.register()
    }
}