package com.example.desctopapp

import com.example.desctopapp.dataclasses.TitlesDataClass

object TitlesObject {
    var current: TitlesDataClass? = TitlesDataClass(
        "English",
        "Profile",
        "New Group",
        "Chats",
        "Contacts",
        "Saved Message",
        "Setting",
        "Themes",
        "Language",
        "About",
    )
    var list: MutableList<TitlesDataClass> = mutableListOf()
}