Feature: Assignment 2 Tests

  Scenario: A1_scenario
    Given a rigged game for A1_scenario
    When Player 1 draws a 4 stage quest
    Then Player 2 sponsors the quest
    And Sponsor sets "Foe 5, Horse" for stage 1
    And Sponsor sets "Foe 15, Sword" for stage 2
    And Sponsor sets "Foe 15, Dagger, Battle-Axe" for stage 3
    And Sponsor sets "Foe 40, Battle-Axe" for stage 4
    And Player 1 does participate in the stage
    And Player 1 trims hand and discards "Foe 5"
    And Player 3 does participate in the stage
    And Player 3 trims hand and discards "Foe 5"
    And Player 4 does participate in the stage
    And Player 4 trims hand and discards "Foe 5"
    And Player 1 sets "Dagger, Sword" for attack
    And Player 3 sets "Sword, Dagger" for attack
    And Player 4 sets "Dagger, Horse" for attack
    And Player 1 attack has a value of 15
    And Player 3 attack has a value of 15
    And Player 4 attack has a value of 15
    And the stage resolves
    And Player 1 does participate in the stage
    And Player 3 does participate in the stage
    And Player 4 does participate in the stage
    And Player 1 sets "Horse, Sword" for attack
    And Player 3 sets "Battle-Axe, Sword" for attack
    And Player 4 sets "Horse, Battle-Axe" for attack
    And the stage resolves
    And Player 1 lost the stage
    And Player 3 passed the stage
    And Player 4 passed the stage
    And Player 1 has "F5 F10 F15 F15 F30 H10 B15 B15 L20" hand
    And Player 3 does participate in the stage
    And Player 4 does participate in the stage
    And Player 3 sets "Lance, Horse, Sword" for attack
    And Player 4 sets "Battle-Axe, Sword, Lance" for attack
    And the stage resolves
    And Player 3 passed the stage
    And Player 4 passed the stage
    And Player 3 does participate in the stage
    And Player 4 does participate in the stage
    And Player 3 sets "Battle-Axe, Horse, Lance" for attack
    And Player 4 sets "Dagger, Sword, Lance, Excalibur" for attack
    And the stage resolves
    And the quest resolves
    And Player 1 lost the quest
    And Player 3 lost the quest
    And Player 4 won the quest
    And Player 1 has 0 shields
    And Player 3 has 0 shields
    And Player 4 has 4 shields
    And Player 3 has "F5 F5 F15 F30 S10" hand
    And Player 4 has "F15 F15 F40 L20" hand

  Scenario: 2winner_game_2winner_quest
    Given a rigged game
    When Player 1 draws a 4 stage quest
    Then Player 1 sponsors the quest
    And Sponsor sets "Foe 5" for stage 1
    And Sponsor sets "Foe 5, Dagger" for stage 2
    And Sponsor sets "Foe 15" for stage 3
    And Sponsor sets "Foe 15, Sword" for stage 4
    And all eligible players participate
    And Player 2 does participate in the stage
    And Player 2 trims hand and discards "Foe 5"
    And Player 3 does participate in the stage
    And Player 3 trims hand and discards "Foe 5"
    And Player 4 does participate in the stage
    And Player 4 trims hand and discards "Foe 5"
    And Player 2 sets "Sword" for attack
    And Player 3 plays to lose
    And Player 4 sets "Horse" for attack
    And the stage resolves
    And Player 2 passed the stage
    And Player 3 lost the stage
    And Player 4 passed the stage
    And Player 2 does participate in the stage
    And Player 4 does participate in the stage
    And Player 2 sets "Sword" for attack
    And Player 4 sets "Dagger, Sword" for attack
    And the stage resolves
    And Player 2 passed the stage
    And Player 4 passed the stage
    And Player 2 does participate in the stage
    And Player 4 does participate in the stage
    And Player 2 sets "Battle-Axe" for attack
    And Player 4 sets "Battle-Axe" for attack
    And the stage resolves
    And Player 2 passed the stage
    And Player 4 passed the stage
    And Player 2 does participate in the stage
    And Player 4 does participate in the stage
    And Player 2 sets "Lance, Horse" for attack
    And Player 4 sets "Lance, Sword" for attack
    And the stage resolves
    And the quest resolves
    And Player 2 won the quest
    And Player 3 lost the quest
    And Player 4 won the quest
    And Player 2 has 4 shields
    And Player 4 has 4 shields
    And Player 2 draws a 3 stage quest
    And Player 3 sponsors the quest
    And Sponsor sets "Foe 5" for stage 1
    And Sponsor sets "Foe 15" for stage 2
    And Sponsor sets "Foe 15, Dagger" for stage 3
    And Player 1 does not participate in the stage
    And Player 2 does participate in the stage
    And Player 4 does participate in the stage
    And Player 2 sets "Sword" for attack
    And Player 4 sets "Battle-Axe" for attack
    And the stage resolves
    And Player 2 passed the stage
    And Player 4 passed the stage
    And Player 2 does participate in the stage
    And Player 4 does participate in the stage
    And Player 2 sets "Battle-Axe" for attack
    And Player 4 sets "Battle-Axe" for attack
    And the stage resolves
    And Player 2 passed the stage
    And Player 4 passed the stage
    And Player 2 does participate in the stage
    And Player 4 does participate in the stage
    And Player 2 sets "Lance" for attack
    And Player 4 sets "Lance" for attack
    And the stage resolves
    And the quest resolves
    And Player 2 won the quest
    And Player 4 won the quest
    And Player 2 has 7 shields
    And Player 4 has 7 shields
    And Player 2 is a winner
    And Player 4 is a winner

  Scenario: 1winner_game_with_events
    Given a rigged game
    When Player 1 draws a 4 stage quest
    Then Player 1 sponsors the quest
    And Sponsor sets "Foe 5" for stage 1
    And Sponsor sets "Foe 5, Dagger" for stage 2
    And Sponsor sets "Foe 15" for stage 3
    And Sponsor sets "Foe 15, Sword" for stage 4
    And all eligible players participate
    And Player 2 does participate in the stage
    And Player 2 trims hand and discards "Foe 5"
    And Player 3 does participate in the stage
    And Player 3 trims hand and discards "Foe 5"
    And Player 4 does participate in the stage
    And Player 4 trims hand and discards "Foe 5"
    And Player 2 sets "Sword" for attack
    And Player 3 sets "Sword" for attack
    And Player 4 sets "Horse" for attack
    And the stage resolves
    And Player 2 passed the stage
    And Player 3 passed the stage
    And Player 4 passed the stage
    And Player 2 does participate in the stage
    And Player 3 does participate in the stage
    And Player 4 does participate in the stage
    And Player 2 sets "Sword" for attack
    And Player 3 sets "Horse, Dagger" for attack
    And Player 4 sets "Dagger, Sword" for attack
    And the stage resolves
    And Player 2 passed the stage
    And Player 3 passed the stage
    And Player 4 passed the stage
    And Player 2 does participate in the stage
    And Player 3 does participate in the stage
    And Player 4 does participate in the stage
    And Player 2 sets "Battle-Axe" for attack
    And Player 3 sets "Battle-Axe" for attack
    And Player 4 sets "Battle-Axe" for attack
    And the stage resolves
    And Player 2 passed the stage
    And Player 3 passed the stage
    And Player 4 passed the stage
    And Player 2 does participate in the stage
    And Player 3 does participate in the stage
    And Player 4 does participate in the stage
    And Player 2 sets "Lance, Horse" for attack
    And Player 3 sets "Lance, Sword" for attack
    And Player 4 sets "Lance, Sword" for attack
    And the stage resolves
    And player hands
    And the quest resolves
    And Player 2 won the quest
    And Player 3 won the quest
    And Player 4 won the quest
    And Player 2 has 4 shields
    And Player 3 has 4 shields
    And Player 4 has 4 shields
    And Player 2 draws a Plague Card
    And Player 2 has 2 shields
    And Player 3 draws a Prosperity Card
    And Player 1 has 12 cards
    And Player 4 draws a Queen's favor Card
    And Player 4 has 12 cards
    And Player 1 draws a 3 stage quest
    And Player 1 sponsors the quest
    And Sponsor sets "Foe 5" for stage 1
    And Sponsor sets "Foe 5, Sword" for stage 2
    And Sponsor sets "Foe 15, Horse" for stage 3
    And all eligible players participate
    And Player 2 does participate in the stage
    And Player 2 trims hand and discards "Foe 5"
    And Player 3 does participate in the stage
    And Player 4 does participate in the stage
    And Player 4 trims hand and discards "Foe 5"
    And Player 2 sets "Dagger" for attack
    And Player 3 sets "Sword" for attack
    And Player 4 plays to lose
    And the stage resolves
    And Player 2 passed the stage
    And Player 3 passed the stage
    And Player 4 lost the stage
    And Player 2 does participate in the stage
    And Player 3 does participate in the stage
    And Player 2 sets "Battle-Axe" for attack
    And Player 3 sets "Sword, Horse" for attack
    And the stage resolves
    And Player 2 passed the stage
    And Player 3 passed the stage
    And Player 2 does participate in the stage
    And Player 3 does participate in the stage
    And Player 2 sets "Lance, Battle-Axe" for attack
    And Player 3 sets "Battle-Axe, Lance" for attack
    And the stage resolves
    And the quest resolves
    And Player 2 won the quest
    And Player 3 won the quest
    And Player 2 has 5 shields
    And Player 3 has 7 shields
    And Player 4 has 4 shields
    And Player 3 is a winner

  Scenario: 0_winner_quest
    Given a rigged game
    When Player 1 draws a 2 stage quest
    Then Player 1 sponsors the quest
    And Sponsor sets "Foe 5, Horse" for stage 1
    And Sponsor sets "Foe 15, Sword" for stage 2
    And all eligible players participate
    And all players lose the stage
    And the stage resolves
    And the quest resolves
    And the quest has 0 winners
    And Player 2 has 0 shields
    And Player 3 has 0 shields
    And Player 4 has 0 shields
    And Player 1 has new cards
    And Player 2 has new cards
    And Player 3 has new cards
    And Player 4 has new cards