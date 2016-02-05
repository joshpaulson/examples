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
            Game game = new Game()
            20.times {
                game.roll(0)
            }

        expect:
            game.score() == 0
    }

    def "Do 20 single pin frames score 20?"(){
        setup:
            Game game = new Game()
            20.times {
                game.roll(1)
            }
        expect:
            game.score() == 20
    }

    def "Does One Spare then 3 pins then 17 gutter ball frames score 16?"() {
        setup:
            Game game = new Game()
            game.roll(5)
            game.roll(5)
            game.roll(3)
            17.times{
                game.roll(0)
            }
        expect:
            game.score() == 16
    }


}
