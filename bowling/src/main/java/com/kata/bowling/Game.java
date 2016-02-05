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

        totalScore += extraScoreForStrikesAndSpares();

        return totalScore.intValue();
    }

    private int extraScoreForStrikesAndSpares() {
        int extraScore = 0;

        for (int i = 0; i < secondInFrameRolls.size(); i++) {

            if (firstInFrameRolls.get(i) == 10){

                // Strike
                if (firstInFrameRolls.get(i+1) != null &&
                        secondInFrameRolls.get(i+1) != null ){

                    extraScore += ( firstInFrameRolls.get(i+1) +
                                       secondInFrameRolls.get(i+1)  ) ;
                }

            } else if (firstInFrameRolls.get(i) + secondInFrameRolls.get(i) == 10 &&
                    firstInFrameRolls.get(i + 1) != null) {

                // Spare
                extraScore += firstInFrameRolls.get(i + 1);
            }
        }
        return extraScore;
    }

    public void roll(int pins){
        rolls.add(new Integer(pins));

        if (rolls.size() % 2 == 1 ){
            firstInFrameRolls.add(new Integer(pins));

            if (pins == 10){
                secondInFrameRolls.add(new Integer(0));
                rolls.add(new Integer(0));
            }
        } else {
            secondInFrameRolls.add(new Integer(pins));
        }
    }
}
