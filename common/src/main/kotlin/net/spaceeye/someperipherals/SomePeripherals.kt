package net.spaceeye.someperipherals

import dan200.computercraft.api.peripheral.IPeripheral
import dan200.computercraft.api.peripheral.PeripheralLookup
import dev.architectury.platform.Platform
import dev.architectury.registry.menu.MenuRegistry
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.spaceeye.someperipherals.config.ConfigDelegateRegister
import net.spaceeye.someperipherals.integrations.cc.getPeripheralCommon
import net.spaceeye.someperipherals.stuff.digitizer.DigitizerScreen
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

fun LOG(s: String) = SomePeripherals.logger.warn(s)

object SomePeripherals {
    const val MOD_ID = "some_peripherals"
    val logger: Logger = LogManager.getLogger(MOD_ID)!!

    var has_vs: Boolean = false
    var has_arc = false

    @JvmStatic
    fun init() {
        if (Platform.isModLoaded("valkyrienskies")) { has_vs = true}
        if (Platform.isModLoaded("acceleratedraycasting")) { has_arc = true}

        ConfigDelegateRegister.initConfig()

        SomePeripheralsBlocks.register()
        SomePeripheralsBlockEntities.register()
        SomePeripheralsItems.register()
        SomePeripheralsMenu.register()

        // There is a discrepancy in the fabric and forge versions of CC: Tweaked for registering peripherals in this verison
        if (Platform.isModLoaded("computercraft")) {
            // reference CC: Bridged and CC: Drones for example if this doesn't work
            PeripheralLookup.get().registerFallback{ level : Level, blockPos : BlockPos, state : BlockState, be : BlockEntity?, direction : Direction ->
                val peripheral: IPeripheral? = getPeripheralCommon(level, blockPos, direction)
                if (peripheral != null)
                    return@registerFallback peripheral
                return@registerFallback null
            }
        }
    }

    @JvmStatic
    fun initClient() {
        MenuRegistry.registerScreenFactory(SomePeripheralsMenu.DIGITIZER_MENU.get()) {it1, it2, it3 -> DigitizerScreen(it1, it2, it3) }
    }
}