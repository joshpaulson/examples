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

            if (isStrike(i) ){

                extraScore += extraScoreForStrikeInFrame( i );

            } else if ( isSpare(i) ) {

                extraScore += extraScoreForSpareInFrame(i);
            }
        }
        return extraScore;
    }

    private int extraScoreForStrikeInFrame( int frame ){
        int extra = 0;

        if (firstInFrameRolls.size() > frame+1  &&
                secondInFrameRolls.size() > frame+1 ) {

            extra = (firstInFrameRolls.get(frame + 1) +
                    secondInFrameRolls.get(frame + 1));

            if (isStrike(frame + 1)) {
                if (firstInFrameRolls.size() > frame + 2) {
                    extra += firstInFrameRolls.get(frame + 2);
                }
                if (secondInFrameRolls.size() > frame + 2) {
                    extra += secondInFrameRolls.get(frame + 2);
                }
            }
            else {
                if (isSpare(frame + 1)) {
                    if (firstInFrameRolls.size() > frame + 2) {
                        extra += firstInFrameRolls.get(frame + 2);
                    }
                }
            }
        }

        if (extra >10 ){
            return 10;
        }
        return extra;
    }

    private int extraScoreForSpareInFrame( int frame ) {
        int extra = 0;

        if ( firstInFrameRolls.size() > frame + 1) {
            extra = firstInFrameRolls.get(frame + 1);
        }
        return extra;
    }

    private boolean isStrike( int frame){

        if (!( firstInFrameRolls.size() > frame) ) {
            return false;
        }

        return firstInFrameRolls.get(frame) == 10;
    }
    private boolean isSpare( int frame){

        if (!( firstInFrameRolls.size() > frame) ||
                !( secondInFrameRolls.size() > frame) ) {
            return false;
        }

        return firstInFrameRolls.get(frame) +
                secondInFrameRolls.get(frame) == 10;
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
