package com.volkswagen.techchallenge.tests.integration

import com.volkswagen.techchallenge.application.command.InitRobotCommand
import com.volkswagen.techchallenge.application.command.InitWorkspaceCommand
import com.volkswagen.techchallenge.application.command.MoveRobotCommand
import com.volkswagen.techchallenge.application.command.handler.InitRobotCommandHandler
import com.volkswagen.techchallenge.application.command.handler.InitWorkspaceCommandHandler
import com.volkswagen.techchallenge.application.command.handler.MoveRobotCommandHandler
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionQuery
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionQueryHandler
import com.volkswagen.techchallenge.domain.entity.Vector
import com.volkswagen.techchallenge.domain.value.`object`.Direction
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
	lateinit var initWorkspaceCommandHandler: InitWorkspaceCommandHandler

	@Autowired
	lateinit var initRobotCommandHandler: InitRobotCommandHandler

	@Autowired
	lateinit var moveRobotCommandHandler: MoveRobotCommandHandler

	@Autowired
	lateinit var getRobotPositionQueryHandler: GetRobotPositionQueryHandler



	@Test
	fun contextLoads() {

		val workspaceLogicalId = UUID.randomUUID()
		initWorkspaceCommandHandler.handle(
			InitWorkspaceCommand(
				workspaceLogicalId,
				Vector(5,5)
			)
		)

		val robotLogicalId = UUID.randomUUID()
		initRobotCommandHandler.handle(
			InitRobotCommand(
				robotLogicalId,
				workspaceLogicalId,
				Vector(1, 2),
				Direction.NORTH
			)
		)

		moveRobotCommandHandler.handle(
			MoveRobotCommand(
				robotLogicalId,
				"LMLMLMLMM"
			)
		)

		getRobotPositionQueryHandler.handle(
			GetRobotPositionQuery(
				robotLogicalId
			)
		)
	}

}
