Feature: Car Acceleration and Brake

  Background:
    Given the car engine is running

  Scenario: Accelerate the Car
    Given a driver accelerates the car with a speed of 20 mph
    When the car speed is checked
    Then the car speed should be 20 mph

  Scenario: Brake the Car
    Given a driver brakes the car
    When the car speed is checked
    Then the car speed should be 0