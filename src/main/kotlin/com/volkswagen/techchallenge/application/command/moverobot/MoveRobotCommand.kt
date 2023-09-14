package com.volkswagen.techchallenge.application.command.moverobot

import com.volkswagen.common.cqrs.command.Command
import java.util.*

class MoveRobotCommand(
    val robotLogicalId: UUID,
    val moveSequence: String
) : Command