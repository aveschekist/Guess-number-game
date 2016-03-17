package org.nesteruk.engine;

import java.util.Random;

public class Engine {

    private int guessNumber;

    private int max;

    public Engine(int max) {
        this.max = max;
    }

    public int checkResult(int number) {
        return number == guessNumber ? 0 : number > guessNumber ? +1 : -1;
    }

    public void generateNumber() {
        Random randomizer = new Random();
        guessNumber = randomizer.nextInt(max);
    }

    public int getMax() {
        return max;
    }

    public int getGuessNumber(){
        return guessNumber;
    }

}
