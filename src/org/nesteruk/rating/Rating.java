package org.nesteruk.rating;

import org.nesteruk.game.Player;

import java.util.LinkedList;

/**
 * Created by i_nesteruk on 17.03.2016.
 */
public interface Rating {
    void insert(Player player);
    LinkedList<Player> getTable();
    void write();
    void read();
}
