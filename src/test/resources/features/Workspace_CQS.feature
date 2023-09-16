Feature: Workspace commands and queries

  Scenario: [1]: Basic happy path that creates a workspace and get its size
    Given a workspace with UUID "e8b5691e-d829-4db0-8a83-1dd06613789d" and upper right corner at position 6, 7
    Then the workspace with UUID "e8b5691e-d829-4db0-8a83-1dd06613789d" has size 7, 8

  Scenario: [2]: Create a workspace with zero size in one side throws exception
    When a workspace with UUID "992be3a9-93d1-4150-8bdc-6a1690430e45" and upper right corner at position 0, 1 throws exception
      | exceptionCode    | VALIDATION                                             |
      | exceptionMessage | [ValidationException] - Workspace needs a correct size |

