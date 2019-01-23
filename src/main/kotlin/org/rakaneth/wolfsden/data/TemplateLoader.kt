package org.rakaneth.wolfsden.data

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

inline fun <reified T : BaseTemplate> buildTemplateRepository(fileName: String): Map<String, T> {
    val ctor = Constructor(T::class.java)
    val yaml = Yaml(ctor)
    val stream = T::class.java.classLoader.getResourceAsStream(fileName)

    return yaml.load<Map<String, T>>(stream)
}