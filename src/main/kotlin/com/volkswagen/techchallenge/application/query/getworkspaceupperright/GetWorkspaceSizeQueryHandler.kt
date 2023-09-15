package com.volkswagen.techchallenge.application.query.getworkspaceupperright

import com.volkswagen.common.cqrs.query.QueryHandler
import com.volkswagen.techchallenge.domain.repository.WorkspaceRepository
import org.springframework.stereotype.Service

@Service
class GetWorkspaceSizeQueryHandler(
    val workspaceRepository: WorkspaceRepository
) : QueryHandler<GetWorkspaceSizeQuery, GetWorkspaceSizeResponse> {
    override fun handle(query: GetWorkspaceSizeQuery): GetWorkspaceSizeResponse {
        val workspace = workspaceRepository.findByLogicalId(query.workspaceLogicalId)
        return GetWorkspaceSizeResponse(
            sizeX = workspace.upperRightCornerX+1,
            sizeY = workspace.upperRightCornerY+1
        )
    }
}