package com.volkswagen.techchallenge.domain.value.`object`

enum class Direction(val value: String) {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    companion object {
        fun from(value: String): Direction {
            return try {
                Direction.valueOf(value.uppercase())
            } catch (e: Exception) {
                throw IllegalArgumentException()
            }
        }
    }
}