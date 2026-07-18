package com.otakcanary.weather

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform