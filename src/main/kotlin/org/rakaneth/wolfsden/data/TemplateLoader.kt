package org.rakaneth.wolfsden.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()

inline fun <reified T> buildTemplateRepository(fileName: String): Map<String, T> {
    val stream: String = T::class.java.classLoader.getResource(fileName).readText()
    val mapType =
        mapper.typeFactory.constructMapType(HashMap::class.java, String::class.java, T::class.java)
    return mapper.readValue(stream, mapType)
}