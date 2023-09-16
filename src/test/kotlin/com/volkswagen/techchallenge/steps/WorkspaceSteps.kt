package com.volkswagen.techchallenge.steps

import com.volkswagen.techchallenge.BaseIntegrationTest
import com.volkswagen.techchallenge.application.command.createworkspace.CreateWorkspaceCommand
import com.volkswagen.techchallenge.application.command.createworkspace.CreateWorkspaceCommandHandler
import com.volkswagen.techchallenge.application.query.getworkspaceupperright.GetWorkspaceSizeQuery
import com.volkswagen.techchallenge.application.query.getworkspaceupperright.GetWorkspaceSizeQueryHandler
import com.volkswagen.techchallenge.domain.exception.DomainException
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class WorkspaceSteps : BaseIntegrationTest() {

    @Autowired
    lateinit var createWorkspaceCommandHandler: CreateWorkspaceCommandHandler

    @Autowired
    lateinit var getWorkspaceSizeQueryHandler: GetWorkspaceSizeQueryHandler

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

    @Then("the workspace with UUID {string} has size {int}, {int}")
    fun `the workspace with UUID {string} has size`(uuid: String, sizeX: Int, sizeY: Int) {
        val getWorkspaceSizeResponse = getWorkspaceSizeQueryHandler.handle(
            GetWorkspaceSizeQuery(
                UUID.fromString(uuid)
            )
        )

        Assertions.assertThat(getWorkspaceSizeResponse.sizeX).isEqualTo(sizeX)
        Assertions.assertThat(getWorkspaceSizeResponse.sizeY).isEqualTo(sizeY)
    }

    @When("a workspace with UUID {string} and upper right corner at position {int}, {int} throws exception")
    fun `a workspace with upper right corner at position throws exception`(uuid: String, upperRightCornerX: Int, upperRightCornerY: Int,
                                                          dataTable: DataTable
    ) {
        val options = dataTable.asMap(String::class.java, String::class.java)
        val exception = assertThrows<DomainException> {
            createWorkspaceCommandHandler.handle(
                CreateWorkspaceCommand(
                    UUID.fromString(uuid),
                    upperRightCornerX,
                    upperRightCornerY
                )
            )
        }

        Assertions.assertThat(exception.message).isEqualTo(getMandatoryParameter("exceptionMessage", options))
        Assertions.assertThat(exception.code).isEqualTo(getMandatoryParameter("exceptionCode", options))
    }

}