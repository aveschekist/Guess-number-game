package org.nesteruk.rating;


import org.nesteruk.game.Player;

import java.io.*;
import java.util.LinkedList;

public class RatingImpl implements Rating {
    private LinkedList<Player> table;
    private String filePaht = "rating.dat";

    @Override
    public void insert(Player player) {
        if(table.size() != 0) {

            int indexSteps = findPositionBySteps(player.getSteps());

            if (indexSteps > table.size() - 1 || (indexSteps == table.size() - 1 && table.getLast().getSteps() <  player.getSteps()))
                table.addLast(player);
            else {
                table.add(indexSteps, player);
            }

        }else
               table.addFirst(player);


    }

    private int findPositionBySteps(int steps)
    {
        int startIndex = 0;
        int endIndex = table.size() - 1;
        int midIndex;
        int curValue;

        while(startIndex < endIndex ) {
            midIndex = ((endIndex - startIndex) / 2) + startIndex;
            curValue = table.get(midIndex).getSteps();

            if(steps > curValue)
                startIndex = midIndex + 1;
            else
                endIndex = midIndex;
        }


        return endIndex;
    }


    @Override
    public LinkedList getTable() {
        return table;
    }


    private void load(LinkedList table) {
        if(table != null)
            this.table = table;
        else
            this.table = new LinkedList<>();
    }

    @Override
    public void write() {
        try(FileOutputStream fos = new FileOutputStream(filePaht)){
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(table);
        } catch (Exception ex){
            System.err.println(String.format("Can't write in file %s", filePaht));
        }
    }

    @Override
    public void read() {
        LinkedList<Player> tmpTable = null;

        try(FileInputStream fis = new FileInputStream(filePaht)){
            ObjectInputStream ois = new ObjectInputStream(fis);
            tmpTable = (LinkedList<Player>) ois.readObject();

        }catch (Exception ex){
        } finally {
            load(tmpTable);
        }
    }
}
