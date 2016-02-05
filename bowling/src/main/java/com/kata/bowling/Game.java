package com.kata.bowling;

import java.util.ArrayList;

/**
 * Created by user on 2/4/16.
 */
public class Game {
    private ArrayList<Integer> rolls = new ArrayList<Integer>();

    public int score(){

        Integer totalScore = rolls
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        return totalScore.intValue();
    }

    public void roll(int pins){
        rolls.add(new Integer(pins));
    }
}
