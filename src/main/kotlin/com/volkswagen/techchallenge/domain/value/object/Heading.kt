package com.volkswagen.techchallenge.domain.value.`object`

enum class Heading(val value: String) {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    companion object {
        fun from(value: String): Heading {
            return when(value) {
                "N" -> NORTH
                "S" -> SOUTH
                "W" -> WEST
                "E" -> EAST
                else -> throw IllegalArgumentException()
            }
        }
    }
}