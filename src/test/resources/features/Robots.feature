Feature: Execute loyalty level calculation cronjob

  Scenario: [1]: Basic happy path test from challenge explanation
    Given a workspace with UUID "8212c8f2-a8bf-44fd-b0af-e8ba320b1c70" and upper right corner at position 5, 5
    And a robot with UUID "7dd63cbe-d3c8-4ae0-b4b6-0afc87b91a69" is at position 1, 2 and heading "N" in workspace with UUID "8212c8f2-a8bf-44fd-b0af-e8ba320b1c70"
    When the robot with UUID "7dd63cbe-d3c8-4ae0-b4b6-0afc87b91a69" moves sequence "LMLMLMLMM"
    Then the robot with UUID "7dd63cbe-d3c8-4ae0-b4b6-0afc87b91a69" is at position 1, 3 and heading "N"
    And a robot with UUID "c46b32d0-2386-4ad6-b7ed-4d5215331622" is at position 3, 3 and heading "E" in workspace with UUID "8212c8f2-a8bf-44fd-b0af-e8ba320b1c70"
    When the robot with UUID "c46b32d0-2386-4ad6-b7ed-4d5215331622" moves sequence "MMRMMRMRRM"
    Then the robot with UUID "c46b32d0-2386-4ad6-b7ed-4d5215331622" is at position 5, 1 and heading "E"
