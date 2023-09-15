Feature: Workspace commands and queries

  Scenario: [1]: Basic happy path that creates a workspace and get its size
    Given a workspace with UUID "e8b5691e-d829-4db0-8a83-1dd06613789d" and upper right corner at position 6, 7
    Then the workspace with UUID "e8b5691e-d829-4db0-8a83-1dd06613789d" has size 7, 8
