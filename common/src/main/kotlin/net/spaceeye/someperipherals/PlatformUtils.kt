package net.spaceeye.someperipherals

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.world.inventory.Slot
import net.minecraft.world.level.block.entity.BlockEntity
import net.spaceeye.someperipherals.config.AbstractConfigBuilder
import net.spaceeye.someperipherals.stuff.utils.CommonBlockEntityInventory

object PlatformUtils {
    // IPeripheralProvider is no longer the correct way of registering CC peripherals
    /*@ExpectPlatform
    @JvmStatic
    fun getPeripheralProvider(): IPeripheralProvider = throw AssertionError()*/

    @ExpectPlatform
    @JvmStatic
    fun getConfigBuilder(): AbstractConfigBuilder = throw AssertionError()

    @ExpectPlatform
    @JvmStatic
    fun makeCommonBlockEntityInventory(size: Int): CommonBlockEntityInventory = throw AssertionError()

    @ExpectPlatform
    @JvmStatic
    fun setDigitizerStuff(be: BlockEntity, addSlot: (slot: Slot) -> Slot): Unit = throw AssertionError()
}