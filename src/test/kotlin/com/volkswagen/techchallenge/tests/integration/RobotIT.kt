package com.volkswagen.techchallenge.tests.integration

import com.volkswagen.techchallenge.application.command.createrobot.CreateRobotCommand
import com.volkswagen.techchallenge.application.command.createworkspace.CreateWorkspaceCommand
import com.volkswagen.techchallenge.application.command.moverobot.MoveRobotCommand
import com.volkswagen.techchallenge.application.command.createrobot.CreateRobotCommandHandler
import com.volkswagen.techchallenge.application.command.createworkspace.CreateWorkspaceCommandHandler
import com.volkswagen.techchallenge.application.command.moverobot.MoveRobotCommandHandler
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionQuery
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionQueryHandler
import com.volkswagen.techchallenge.domain.value.`object`.Heading
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.UUID

@SpringBootTest
@ExtendWith(SpringExtension::class)
//@WebMvcTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@ComponentScan("com.volkswagen.techchallenge.*")
class RobotIT {

	@Autowired
	lateinit var createWorkspaceCommandHandler: CreateWorkspaceCommandHandler

	@Autowired
	lateinit var createRobotCommandHandler: CreateRobotCommandHandler

	@Autowired
	lateinit var moveRobotCommandHandler: MoveRobotCommandHandler

	@Autowired
	lateinit var getRobotPositionQueryHandler: GetRobotPositionQueryHandler

	@Test
	fun `happy path`() {

		val workspaceLogicalId = UUID.randomUUID()
		createWorkspaceCommandHandler.handle(
			CreateWorkspaceCommand(
				workspaceLogicalId,
				5,
				5
			)
		)

		val robotLogicalId = UUID.randomUUID()
		createRobotCommandHandler.handle(
			CreateRobotCommand(
				robotLogicalId,
				workspaceLogicalId,
				1,
				2,
				Heading.NORTH
			)
		)

		moveRobotCommandHandler.handle(
			MoveRobotCommand(
				robotLogicalId,
				"LMLMLMLMM"
			)
		)

		val robotPosition = getRobotPositionQueryHandler.handle(
			GetRobotPositionQuery(
				robotLogicalId
			)
		)
	}

}
