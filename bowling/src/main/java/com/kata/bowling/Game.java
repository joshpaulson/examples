package com.kata.bowling;

/**
 * Created by user on 2/4/16.
 */
public class Game {
    private int score = 0;
    public int score(){

        return score;
    }

    public void roll(int pins){
        score += pins;
    }
}
