package com.volkswagen.techchallenge.metrics

object MetricNames {
    private const val MS_CLEANING_ROBOTS : String = "vw.cleaning.robots"
    const val WORKSPACE_CREATED : String = "$MS_CLEANING_ROBOTS.workspace.initialized"
    const val ROBOT_CREATED : String = "$MS_CLEANING_ROBOTS.robot.initialized"
}