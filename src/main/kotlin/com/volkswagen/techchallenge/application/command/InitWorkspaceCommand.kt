package com.volkswagen.techchallenge.application.command

import com.volkswagen.common.cqrs.command.Command
import com.volkswagen.techchallenge.domain.entity.Vector
import com.volkswagen.techchallenge.domain.entity.Workspace
import com.volkswagen.techchallenge.domain.value.`object`.Direction
import java.util.UUID

class InitWorkspaceCommand(
    val workspaceLogicalId: UUID,
    val upperRightCorner: Vector
) : Command