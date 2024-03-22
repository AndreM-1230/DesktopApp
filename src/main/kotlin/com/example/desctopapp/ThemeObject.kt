package com.example.desctopapp

import com.example.desctopapp.dataclasses.ThemeDataClass

object ThemeObject {
    var current: ThemeDataClass? = ThemeDataClass(
        "Desert",
        "Пустыня",
        "FF46211A",
        "FF693D3D",
        "FF693D3D",
        "FFBA5536",
        "FFA43820",
        "FFFFFFFF",
        "FFFFFFFF"
    )
    var list: MutableList<ThemeDataClass> = mutableListOf()
}