package org.nesteruk.game;

import org.nesteruk.engine.Engine;

import java.util.Scanner;

public class GameConsole implements Game {

    private Engine engine;
    private int steps;

    public GameConsole(Engine engine)
    {
        setEngine(engine);
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public int getSteps() {
        return steps;
    }

    @Override
    public void start() {
        engine.generateNumber();

        boolean isFind = false;
        Scanner scanner = new Scanner(System.in);
        int  number;
        int result;
        steps = 0;

        System.out.println(String.format("Start new round game. Try guess number [0..%d]", engine.getMax()));
        do {
            System.out.print("Please, enter number: ");

            try {
                number = scanner.nextInt();

                result = engine.checkResult(number);
                steps++;
                if( result == 0){
                    isFind = true;
                    System.out.println("HIT!");
                } else if(result > 0){
                    System.out.println("Miss. The number is smaller");
                } else{
                    System.out.println("Miss. The number is bigger");
                }
            }catch (java.util.InputMismatchException ex){
                System.err.println("Wrong symbols. Print only number!");
                scanner.nextLine();
            }

        }while (!isFind);
    }
}
