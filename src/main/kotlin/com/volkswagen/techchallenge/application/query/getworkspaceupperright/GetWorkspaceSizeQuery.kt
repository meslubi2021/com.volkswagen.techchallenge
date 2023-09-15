package com.volkswagen.techchallenge.application.query.getworkspaceupperright

import com.volkswagen.common.cqrs.query.Query
import java.util.UUID

data class GetWorkspaceSizeQuery(
    val workspaceLogicalId: UUID
) : Query