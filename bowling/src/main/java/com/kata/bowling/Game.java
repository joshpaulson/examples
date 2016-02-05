package com.kata.bowling;

import java.util.ArrayList;

/**
 * Created by user on 2/4/16.
 */
public class Game {
    private ArrayList<Integer> rolls = new ArrayList<Integer>();

    private ArrayList<Integer> firstInFrameRolls = new ArrayList<Integer>();
    private ArrayList<Integer> secondInFrameRolls = new ArrayList<Integer>();


    public int score(){

        Integer totalScore = rolls
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        for (int i = 0; i< secondInFrameRolls.size(); i++){
            if (firstInFrameRolls.get(i) + secondInFrameRolls.get(i) ==10 &&
                    firstInFrameRolls.get(i+1) != null) {
                totalScore += firstInFrameRolls.get(i+1);
            }
        }


        return totalScore.intValue();
    }

    public void roll(int pins){
        rolls.add(new Integer(pins));

        if (rolls.size() % 2 == 1 ){
            firstInFrameRolls.add(new Integer(pins));
        } else {
            secondInFrameRolls.add(new Integer(pins));
        }
    }
}
