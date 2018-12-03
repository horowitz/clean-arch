package com.example.danielhorowitz.clean

import com.google.gson.Gson
import java.io.File

fun <T> File.fromJson(type: Class<T>): T = Gson().fromJson(this.readText(), type)