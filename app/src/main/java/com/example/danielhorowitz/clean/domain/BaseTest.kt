package com.example.danielhorowitz.clean.domain

import java.io.File

open class BaseTest {
    fun getResource(fileName: String): File {
        val loader = ClassLoader.getSystemClassLoader()
        val resource = loader.getResource(fileName)
        return File(resource.path)
    }
}
