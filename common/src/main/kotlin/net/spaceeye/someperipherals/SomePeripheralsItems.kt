package net.spaceeye.someperipherals

import dev.architectury.registry.CreativeTabRegistry
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.spaceeye.someperipherals.blocks.SomePeripheralsCommonBlocks
import net.spaceeye.someperipherals.items.goggles.RangeGogglesItem
import net.spaceeye.someperipherals.items.goggles.StatusGogglesItem
import java.util.function.Supplier

object SomePeripheralsItems {
    val ITEMS = DeferredRegister.create(SomePeripherals.MOD_ID, Registries.ITEM)

    var LOGO: RegistrySupplier<Item> = ITEMS.register("someperipherals_logo") { Item(Item.Properties()) }

    val TAB: CreativeModeTab = CreativeTabRegistry.create( Component.translatable("someperipherals_tab"), ItemStack.of(LOGO.)
            /*ResourceLocation(
            SomePeripherals.MOD_ID,
            "someperipherals_tab"
            )*/
    )



    var STATUS_GOGGLES: RegistrySupplier<Item> = ITEMS.register("status_goggles") { StatusGogglesItem() }
    var RANGE_GOGGLES: RegistrySupplier<Item> = ITEMS.register("range_goggles") { RangeGogglesItem() }

    fun register() {
        SomePeripheralsBlocks.registerItems(ITEMS)
        SomePeripheralsCommonBlocks.registerItems(ITEMS)
        ITEMS.register()
    }
}