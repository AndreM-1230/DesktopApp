package com.example.desctopapp

import com.example.desctopapp.dataclasses.TitlesDataClass

object TitlesObject {
    var current: TitlesDataClass? = TitlesDataClass(
        "English",
        "New Group",
        "Chats",
        "Contacts",
        "Saved Message",
        "Setting",
        "About",
    )
    var list: MutableList<TitlesDataClass> = mutableListOf()
}