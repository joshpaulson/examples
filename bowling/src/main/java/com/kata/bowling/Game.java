package com.kata.bowling;

import java.util.ArrayList;

/**
 * Created by user on 2/4/16.
 */
public class Game {
    private ArrayList<Integer> rolls = new ArrayList<Integer>();

    private ArrayList<Integer> firstInFrameRolls = new ArrayList<Integer>();
    private ArrayList<Integer> secondInFrameRolls = new ArrayList<Integer>();

    private ArrayList<Integer> calculatedFrameScores = null;

    public int score(){

        initializeEmptyCalculatedFrameScores();

        int indexOfLastFrame = firstInFrameRolls.size() - 1;

        for ( int frame = indexOfLastFrame; frame >=0; frame --){

            Integer frameScore = firstInFrameRolls.get( frame)
                                + secondInFrameRolls.get(frame)
                                + extraScoreForStrikesAndSpares(frame );

            calculatedFrameScores.add(frame, frameScore);

        }

        Integer totalScore = calculatedFrameScores
                .stream()
                .mapToInt(Integer::intValue)
                .sum();


        return totalScore.intValue();
    }

    private void initializeEmptyCalculatedFrameScores(){
        calculatedFrameScores = new ArrayList<Integer>( firstInFrameRolls.size() );
        firstInFrameRolls.forEach( (frame) -> calculatedFrameScores.add(0) );
    }

    private int extraScoreForStrikesAndSpares( int frame) {
        int extraScore = 0;

        if (isStrike(frame) && isFrameCalculated(frame +1)){
            if (isSpare( frame +1)){
                return 10;
            }
            extraScore += calculatedFrameScores.get( frame + 1 );

        } else if ( isSpare(frame) ) {
            extraScore += extraScoreForSpareInFrame(frame);
        }

        if (extraScore >20){
            return 20;
        }
        return extraScore;
    }

    private int extraScoreForSpareInFrame( int frame ) {
        int extra = 0;

        if ( frameStarted( frame + 1 ) ) {
            extra = firstInFrameRolls.get(frame + 1);
        }
        return extra;
    }

    private boolean frameCompleted( int frame){

        if ( firstInFrameRolls.size() > frame && secondInFrameRolls.size() > frame ) {
            return true;
        }
        return false;
    }
    private boolean frameStarted( int frame){
        if (firstInFrameRolls.size() > frame ) {
            return true;
        }
        return false;
    }

    private boolean isFrameCalculated( int frame){
        if (calculatedFrameScores == null ||
               ! (calculatedFrameScores.size() > frame)) {
            return false;
        }
        return true;
    }
    private boolean isStrike( int frame){

        if ( ! frameStarted( frame ) ) {
            return false;
        }

        return firstInFrameRolls.get(frame) == 10;
    }
    private boolean isSpare( int frame){

        if (! frameCompleted(frame) ) {
            return false;
        }

        if (firstInFrameRolls.get(frame) < 10 &&
                firstInFrameRolls.get(frame) +
                secondInFrameRolls.get(frame) == 10){
            return true;
        }
        return false;
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
