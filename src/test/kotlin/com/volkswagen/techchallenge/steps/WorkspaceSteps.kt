package com.volkswagen.techchallenge.steps

import com.volkswagen.techchallenge.BaseIntegrationTest
import com.volkswagen.techchallenge.application.command.createworkspace.CreateWorkspaceCommand
import com.volkswagen.techchallenge.application.command.createworkspace.CreateWorkspaceCommandHandler
import io.cucumber.java.en.Given
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class WorkspaceSteps : BaseIntegrationTest() {

    @Autowired
    lateinit var createWorkspaceCommandHandler: CreateWorkspaceCommandHandler

    @Given("a workspace with UUID {string} and upper right corner at position {int}, {int}")
    fun `a workspace with upper right corner at position`(uuid: String, upperRightCornerX: Int, upperRightCornerY: Int) {
        createWorkspaceCommandHandler.handle(
            CreateWorkspaceCommand(
                UUID.fromString(uuid),
                upperRightCornerX,
                upperRightCornerY
            )
        )
    }
}