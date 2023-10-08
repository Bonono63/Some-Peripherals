package net.spaceeye.someperipherals.config

data class ConfigValueGetSet(var get: () -> Any, var set: (Any) -> Unit)

abstract class ConfigBuilder {
    abstract fun pushNamespace(namespace: String)
    abstract fun popNamespace()

    abstract fun beginBuilding()
    abstract fun finishBuilding(type: String)

    abstract fun <T : Any> makeItem(name: String, defaultValue: T, description: String): ConfigValueGetSet
}

