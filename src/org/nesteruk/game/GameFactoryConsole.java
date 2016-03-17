package org.nesteruk.game;


import org.nesteruk.engine.Engine;

public class GameFactoryConsole implements GameFactory {

    @Override
    public Game createGame(int max){
        switch (max){
            case Mode.EASY:
                return new GameConsole(new Engine(Mode.EASY_MAX));
            case Mode.MID:
                return new GameConsole(new Engine(Mode.MID_MAX));
            case Mode.HARD:
                return new GameConsole(new Engine(Mode.HARD_MAX));
            default:
                return null;

        }
    }
}
