package dev.arildo.data.setlist.dto

data class SetListDTO(
    var date: Long? = 0L,
    var musicians: List<String> = emptyList(),
    var songs: List<String> = emptyList()
)