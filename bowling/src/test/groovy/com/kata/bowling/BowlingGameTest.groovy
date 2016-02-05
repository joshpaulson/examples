package com.kata.bowling

import spock.lang.Specification;

class BowlingGameTest extends Specification{

    def "Does Spock Work?"() {
        expect:
           name.size() == length

        where:
           name << ["joe", "john", "spock"]
           length << [3,4,5]
    }

    def "Does a Gutter Game Work?"() {
        setup:
            Game game = new Game();
            10.times {
                game.roll(0);
            }

        expect:
            game.score() == 0;
    }


}
