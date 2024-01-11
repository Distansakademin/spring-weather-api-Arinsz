package com.example.spring_mysql_api.cucumber;

public class Car {

    int speed;
    boolean engineRunning;


    public Car() {
    }

    public Car(int speed, boolean engineRunning) {
        this.speed = speed;
        this.engineRunning = engineRunning;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isEngineRunning() {
        return engineRunning;
    }

    public void setEngineRunning(boolean engineRunning) {
        this.engineRunning = engineRunning;
    }

    public void brake() {
        setSpeed(0);
    }
}
