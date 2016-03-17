package org.nesteruk.game;

import java.io.Serializable;

/**
 * Created by i_nesteruk on 17.03.2016.
 */
public class Player implements Serializable {
    private String name;
    private int steps;

    public Player(String name, int steps) {
        this.name = name;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public int getSteps() {
        return steps;
    }
}
