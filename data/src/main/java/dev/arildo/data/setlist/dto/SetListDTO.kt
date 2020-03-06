package dev.arildo.data.setlist.dto

data class SetListDTO(
    var date: Long? = 0L,
    var songs: List<String> = emptyList()
)