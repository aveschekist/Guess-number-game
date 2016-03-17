package org.nesteruk.game;

import org.nesteruk.engine.Engine;

public interface GameFactory {
    Game createGame(int max);
}