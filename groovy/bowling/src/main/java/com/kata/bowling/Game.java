package com.kata.bowling;

import java.util.ArrayList;

public class Game {

    private ArrayList<Integer> rolls = new ArrayList<Integer>();

    private ArrayList<Integer> firstInFrameRolls = new ArrayList<Integer>();
    private ArrayList<Integer> secondInFrameRolls = new ArrayList<Integer>();

    private ArrayList<Integer> calculatedFrameScores = null;


    private static final int NUMBER_OF_NORMAL_FRAMES = 10;

    public int score(){

        initializeEmptyCalculatedFrameScores();

        for (int i=0; i <  calculatedFrameScores.size(); i++) {

            int frameScore = firstInFrameRolls.get(i) +
                             secondInFrameRolls.get(i) +
                             extraScoreForStrikesAndSpares(i);

            calculatedFrameScores.set(i, frameScore);
        }

        removeScoresBeyondTheLastFrame();

        Integer totalScore = calculatedFrameScores
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        return totalScore;
    }


    private int extraScoreForStrikesAndSpares( int frame) {

        int extraScore = 0;
        int nextFrame = frame +1;
        int afterNextFrame = frame +2;

        // Zero Strikes
        if (isSpare( frame )  ){
            extraScore = extraScoreForSpareInFrame(frame );
        }

        // One Strike
        if (isStrike( frame ) ) {

            if (frameCompletedAndNotStrikeOrSpare( nextFrame )) {

                extraScore = firstInFrameRolls.get( nextFrame ) +
                        secondInFrameRolls.get( nextFrame );
            }

            if (isStrike(frame) && isSpare( nextFrame )) {
                extraScore = 10;
            }
        }

        // Two Strikes
        if (isStrike( frame ) && isStrike( nextFrame ) ) {

            if (frameCompletedAndNotStrikeOrSpare( afterNextFrame )) {
                extraScore = 10 + firstInFrameRolls.get( afterNextFrame ) +
                        secondInFrameRolls.get( afterNextFrame );
            }

            if (isSpare( afterNextFrame )) {
                extraScore = 10 + firstInFrameRolls.get( afterNextFrame );
            }

            if (isStrike( afterNextFrame )) {
                extraScore = 20;
            }
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

    private void removeScoresBeyondTheLastFrame() {

        if (calculatedFrameScores.size() > NUMBER_OF_NORMAL_FRAMES) {

            int frame = NUMBER_OF_NORMAL_FRAMES; // 10

            while (frame < calculatedFrameScores.size() ) {
                calculatedFrameScores.set(frame, 0);
                frame ++;
            }
        }
    }

    private void initializeEmptyCalculatedFrameScores(){
        calculatedFrameScores = new ArrayList<Integer>( firstInFrameRolls.size() );
        firstInFrameRolls.forEach( (frame) -> calculatedFrameScores.add(0) );
    }

    private boolean frameCompletedAndNotStrikeOrSpare( int frame ){
        return frameCompleted( frame ) &&
                !isStrike( frame ) && !isSpare(frame);
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

    private boolean isStrike( int frame){

        if ( ! frameCompleted( frame ) ) {
            return false;
        }

        return (firstInFrameRolls.get(frame) == 10 &&
                secondInFrameRolls.get( frame) == 0);
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
