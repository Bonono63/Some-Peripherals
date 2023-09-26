package net.spaceeye.someperipherals

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.BoolArgumentType
import com.mojang.brigadier.arguments.FloatArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import net.minecraft.commands.CommandSourceStack

object SomePeripheralsCommands {
    private fun lt(name: String) = LiteralArgumentBuilder.literal<CommandSourceStack>(name)
    private fun <T> arg(name: String, type: ArgumentType<T>) = RequiredArgumentBuilder.argument<CommandSourceStack, T>(name, type)

    private fun optionDebugLogging(it: CommandContext<CommandSourceStack>):Int {
        val state = BoolArgumentType.getBool(it, "enable")
        SomePeripherals.slogger.is_enabled = state
        return 0
    }

    private fun optionSetDebugOffset(it: CommandContext<CommandSourceStack>): Int {
        val offset: Float = FloatArgumentType.getFloat(it, "offset")
        val what_offset: String = StringArgumentType.getString(it, "xyz")

        when(what_offset.lowercase()) {
            "x" -> {SomePeripheralsConfig.SERVER.COMMON.RAYCASTER_SETTINGS.debug_x_displacement = offset.toDouble()}
            "y" -> {SomePeripheralsConfig.SERVER.COMMON.RAYCASTER_SETTINGS.debug_y_displacement = offset.toDouble()}
            "z" -> {SomePeripheralsConfig.SERVER.COMMON.RAYCASTER_SETTINGS.debug_z_displacement = offset.toDouble()}
            else -> {
                return 1
            }
        }
        return 0
    }

    fun registerServerCommands(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            lt("some_peripherals").then(
                lt("debug-logging")
                    .then(arg("enable", BoolArgumentType.bool()).executes{ optionDebugLogging(it) })
                ).then(
                lt("debug-offset")
                    .then(arg("xyz", StringArgumentType.string())
                        .then(arg("offset", FloatArgumentType.floatArg()).executes{ optionSetDebugOffset(it) }))
                )
        )
    }
}