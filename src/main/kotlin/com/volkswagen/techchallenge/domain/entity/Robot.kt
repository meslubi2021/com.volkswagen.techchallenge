package com.volkswagen.techchallenge.domain.entity

import com.volkswagen.techchallenge.domain.value.`object`.Direction
import java.util.*

class Robot(
    var id: Long?,
    var logicalId: UUID,
    var position: Vector,
    var direction: Direction
) {
    fun move() {
        when(direction) {
            Direction.NORTH -> position.y + 1
            Direction.EAST -> position.x + 1
            Direction.SOUTH -> position.y - 1
            Direction.WEST -> position.x - 1
        }
    }

    fun left() {
        direction = when(direction) {
            Direction.NORTH -> Direction.WEST
            Direction.EAST -> Direction.NORTH
            Direction.SOUTH -> Direction.EAST
            Direction.WEST -> Direction.SOUTH
        }
    }

    fun right() {
        direction = when(direction) {
            Direction.NORTH -> Direction.EAST
            Direction.EAST -> Direction.SOUTH
            Direction.SOUTH -> Direction.WEST
            Direction.WEST -> Direction.NORTH
        }
    }
}