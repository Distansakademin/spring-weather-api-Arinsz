package com.example.spring_mysql_api.cucumber.cucumberStepdefinitions;
import com.example.spring_mysql_api.cucumber.Car;
import io.cucumber.java.en.*;
import io.cucumber.spring.CucumberContextConfiguration;
import static org.junit.Assert.assertEquals;


@CucumberContextConfiguration
public class CarStepDefinitions {
 String hej;
    Car car = new Car();

    @Given("the car engine is running")
    public void theCarEngineIsRunning() {
        car.setEngineRunning(true);
        System.out.println("The car engine is running!");
    }

    @Given("a driver accelerates the car with a speed of {int} mph")
    public void accelerateCar(int speed) {
        if (car.isEngineRunning()) {
            car.setSpeed(car.getSpeed() + speed);
            System.out.println("The car is accelerated to " + car.getSpeed() + " mph.");
        } else {
            System.out.println("The car engine is not running.");
        }
    }

    @When("the car speed is checked")
    public void checkCarSpeed() {
        System.out.println("The current car speed is " + car.getSpeed() + " mph.");
    }

    @Then("the car speed should be {int} mph")
    public void verifyCarSpeed(int expectedSpeed) {
        assertEquals(expectedSpeed, car.getSpeed());
    }

    @Given("a driver brakes the car")
    public void aDriverBrakesTheCar() {
        if (car.isEngineRunning() && car.getSpeed() > 0) {
            car.brake();
        }

    }

    @Then("the car speed should be 0")
    public void verifyCarSpeedIsZero() {
        assertEquals(0, car.getSpeed());
    }
}


