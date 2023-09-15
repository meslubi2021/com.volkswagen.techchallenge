Feature: Factory API REST tests

  Scenario: [1]: Basic happy path test from challenge explanation
    When processing the following input sequence by the factory
      | 5 5        |
      | 1 2 N      |
      | LMLMLMLMM  |
      | 3 3 E      |
      | MMRMMRMRRM |
    Then response has code 200 and body is
      | 1 3 N |
      | 5 1 E |

  Scenario: [2]: Happy path with all robots going per row up and down
    When processing the following input sequence by the factory
      | 5 5          |
      | 0 0 N        |
      | MMMMMRRMMMMM |
      | 1 0 N        |
      | MMMMMRRMMMMM |
      | 2 0 N        |
      | MMMMMRRMMMMM |
      | 3 0 N        |
      | MMMMMRRMMMMM |
      | 4 0 N        |
      | MMMMMRRMMMMM |
      | 5 0 N        |
      | MMMMMRRMMMMM |
    Then response has code 200 and body is
      | 0 0 S |
      | 1 0 S |
      | 2 0 S |
      | 3 0 S |
      | 4 0 S |
      | 5 0 S |

  Scenario: [3]: Bad robot position will throw bad request exception
    When processing the following input sequence by the factory
      | 5 5    |
      | A 2 N  |
      | RMMLMM |
    Then response has code 400 and body is
      | {"message":"[BadRequestException] - Invalid request format"} |

  Scenario: [4]: Bad robot heading will throw bad request exception
    When processing the following input sequence by the factory
      | 5 5    |
      | 1 2 X  |
      | RMMLMM |
    Then response has code 400 and body is
      | {"message":"[BadRequestException] - Invalid request format"} |

  Scenario: [5]: Bad robot move sequence will throw bad request exception
    When processing the following input sequence by the factory
      | 5 5    |
      | 1 2 N  |
      | RMXLMM |
    Then response has code 400 and body is
      | {"message":"[BadRequestException] - Invalid request format"} |

  Scenario: [6]: Bad workspace will throw validation exception
    When processing the following input sequence by the factory
      | 0 5    |
      | 1 2 N  |
      | RMMLMM |
    Then response has code 400 and body is
      | {"message":"[ValidationException] - Workspace needs a correct size"} |