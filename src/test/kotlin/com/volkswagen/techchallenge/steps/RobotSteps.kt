package com.volkswagen.techchallenge.steps

import com.volkswagen.techchallenge.BaseIntegrationTest
import com.volkswagen.techchallenge.application.command.createrobot.CreateRobotCommand
import com.volkswagen.techchallenge.application.command.createrobot.CreateRobotCommandHandler
import com.volkswagen.techchallenge.application.command.moverobot.MoveRobotCommand
import com.volkswagen.techchallenge.application.command.moverobot.MoveRobotCommandHandler
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionQuery
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionQueryHandler
import com.volkswagen.techchallenge.domain.value.`object`.Heading
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.spring.CucumberContextConfiguration
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import java.util.*


@CucumberContextConfiguration
class RobotSteps : BaseIntegrationTest() {

    @Autowired
    lateinit var createRobotCommandHandler: CreateRobotCommandHandler

    @Autowired
    lateinit var moveRobotCommandHandler: MoveRobotCommandHandler

    @Autowired
    lateinit var getRobotPositionQueryHandler: GetRobotPositionQueryHandler

    @Given("a robot with UUID {string} is at position {int}, {int} and heading {string} in workspace with UUID {string}")
    fun `a robot with UUID is at position and heading in workspace with UUID`(
        robotLogicalId: String,
        positionX: Int,
        positionY: Int,
        heading: String,
        workspaceLogicalId: String) {

        createRobotCommandHandler.handle(
            CreateRobotCommand(
                UUID.fromString(robotLogicalId),
                UUID.fromString(workspaceLogicalId),
                positionX,
                positionY,
                Heading.from(heading)
            )
        )
    }

    @When("the robot with UUID {string} does move sequence {string}")
    fun `the robot does move sequence`(uuid: String, moveSequence: String) {
        moveRobotCommandHandler.handle(
            MoveRobotCommand(
                UUID.fromString(uuid),
                moveSequence
            )
        )
    }

    @Then("the robot with UUID {string} is at position {int}, {int} and heading {string}")
    fun `the robot with UUID is at position and heading`(
        robotLogicalId: String,
        positionX: Int,
        positionY: Int,
        heading: String
    ) {
        val robotPosition = getRobotPositionQueryHandler.handle(
            GetRobotPositionQuery(
                UUID.fromString(robotLogicalId)
            )
        )

        Assertions.assertThat(robotPosition.positionX).isEqualTo(positionX)
        Assertions.assertThat(robotPosition.positionY).isEqualTo(positionY)
        Assertions.assertThat(robotPosition.heading).isEqualTo(Heading.from(heading))
    }
}