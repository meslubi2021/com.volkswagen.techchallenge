Feature: Robot commands and queries

  Scenario: [1]: Basic happy path test from challenge explanation
    Given a workspace with UUID "8212c8f2-a8bf-44fd-b0af-e8ba320b1c70" and upper right corner at position 5, 5
    And a robot with UUID "7dd63cbe-d3c8-4ae0-b4b6-0afc87b91a69" is at position 1, 2 and heading "N" in workspace with UUID "8212c8f2-a8bf-44fd-b0af-e8ba320b1c70"
    When the robot with UUID "7dd63cbe-d3c8-4ae0-b4b6-0afc87b91a69" moves sequence "LMLMLMLMM"
    Then the robot with UUID "7dd63cbe-d3c8-4ae0-b4b6-0afc87b91a69" is at position 1, 3 and heading "N"
    And a robot with UUID "c46b32d0-2386-4ad6-b7ed-4d5215331622" is at position 3, 3 and heading "E" in workspace with UUID "8212c8f2-a8bf-44fd-b0af-e8ba320b1c70"
    When the robot with UUID "c46b32d0-2386-4ad6-b7ed-4d5215331622" moves sequence "MMRMMRMRRM"
    Then the robot with UUID "c46b32d0-2386-4ad6-b7ed-4d5215331622" is at position 5, 1 and heading "E"

  Scenario: [2]: Collision between two robots
    Given a workspace with UUID "a2ac4b25-dcdf-4da9-927b-e2422cc0f1c4" and upper right corner at position 5, 5
    And a robot with UUID "4c323abb-8c5e-4e05-89d3-9d78f533ceb1" is at position 3, 1 and heading "W" in workspace with UUID "a2ac4b25-dcdf-4da9-927b-e2422cc0f1c4"
    When the robot with UUID "4c323abb-8c5e-4e05-89d3-9d78f533ceb1" moves sequence "RMMLMML"
    Then the robot with UUID "4c323abb-8c5e-4e05-89d3-9d78f533ceb1" is at position 1, 3 and heading "S"
    And a robot with UUID "824fce1f-683f-4569-839c-805f9715ae94" is at position 2, 5 and heading "E" in workspace with UUID "a2ac4b25-dcdf-4da9-927b-e2422cc0f1c4"
    Then the robot with UUID "824fce1f-683f-4569-839c-805f9715ae94" when moving sequence "RMRMLM" throws exception
      | exceptionCode    | ROBOT_COLLISION                                                   |
      | exceptionMessage | [RobotCollisionException] - Collision with robot at position 1, 3 |
    And the robot with UUID "824fce1f-683f-4569-839c-805f9715ae94" is at position 1, 4 and heading "S"

  Scenario: [3]: Trying to move outside of workspace by left side
    Given a workspace with UUID "71147915-cd61-4237-a893-08229ffbc2b5" and upper right corner at position 2, 2
    And a robot with UUID "360f925e-ac32-4310-be44-9654d5012e8d" is at position 0, 1 and heading "N" in workspace with UUID "71147915-cd61-4237-a893-08229ffbc2b5"
    Then the robot with UUID "360f925e-ac32-4310-be44-9654d5012e8d" when moving sequence "LM" throws exception
      | exceptionCode    | ROBOT_OUTSIDE_WORKSPACE                                          |
      | exceptionMessage | [RobotOutsideWorkspaceException] - Not possible to move to -1, 1 |
    And the robot with UUID "360f925e-ac32-4310-be44-9654d5012e8d" is at position 0, 1 and heading "W"

  Scenario: [4]: Trying to move outside of workspace by the lower side
    Given a workspace with UUID "a984b72e-9431-4e76-b284-7003d4313343" and upper right corner at position 2, 2
    And a robot with UUID "8e017785-4297-4374-a567-3cd35daf5a1a" is at position 0, 1 and heading "N" in workspace with UUID "a984b72e-9431-4e76-b284-7003d4313343"
    Then the robot with UUID "8e017785-4297-4374-a567-3cd35daf5a1a" when moving sequence "RRMM" throws exception
      | exceptionCode    | ROBOT_OUTSIDE_WORKSPACE                                          |
      | exceptionMessage | [RobotOutsideWorkspaceException] - Not possible to move to 0, -1 |
    And the robot with UUID "8e017785-4297-4374-a567-3cd35daf5a1a" is at position 0, 0 and heading "S"

  Scenario: [5]: Trying to move outside of workspace by the upper side
    Given a workspace with UUID "cc0e33d1-5a3e-49f8-ac5f-b2f2c63801f8" and upper right corner at position 2, 2
    And a robot with UUID "72a38c35-3521-400a-9b1b-f985778e5577" is at position 0, 1 and heading "W" in workspace with UUID "cc0e33d1-5a3e-49f8-ac5f-b2f2c63801f8"
    Then the robot with UUID "72a38c35-3521-400a-9b1b-f985778e5577" when moving sequence "RMM" throws exception
      | exceptionCode    | ROBOT_OUTSIDE_WORKSPACE                                         |
      | exceptionMessage | [RobotOutsideWorkspaceException] - Not possible to move to 0, 3 |
    And the robot with UUID "72a38c35-3521-400a-9b1b-f985778e5577" is at position 0, 2 and heading "N"

  Scenario: [6]: Trying to move outside of workspace by right side
    Given a workspace with UUID "f181e820-9723-448c-aa6d-eac9e74c7aff" and upper right corner at position 2, 2
    And a robot with UUID "767fabd0-050f-409a-92ff-0d450aa23ecc" is at position 0, 1 and heading "N" in workspace with UUID "f181e820-9723-448c-aa6d-eac9e74c7aff"
    Then the robot with UUID "767fabd0-050f-409a-92ff-0d450aa23ecc" when moving sequence "RMMM" throws exception
      | exceptionCode    | ROBOT_OUTSIDE_WORKSPACE                                         |
      | exceptionMessage | [RobotOutsideWorkspaceException] - Not possible to move to 3, 1 |
    And the robot with UUID "767fabd0-050f-409a-92ff-0d450aa23ecc" is at position 2, 1 and heading "E"

  Scenario: [7]: Created robot has to have positive positionX
    Given a workspace with UUID "edc371b1-6b1e-4358-ba8e-b72e4bf71068" and upper right corner at position 5, 5
    And a robot with UUID "effec6b0-6061-4cf2-8fb7-7758919eb5ac" is at position -1, 2 and heading "N" in workspace with UUID "edc371b1-6b1e-4358-ba8e-b72e4bf71068" throws exception
      | exceptionCode    | VALIDATION                                               |
      | exceptionMessage | [ValidationException] - Robot has to be inside workspace |