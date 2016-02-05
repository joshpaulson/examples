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

    def "Does One Strike Followed by a 3 then 4 frame and 16 gutter balls score 24?"() {
        setup:
            Game game = new Game()
            game.roll(10)
            game.roll(3)
            game.roll(4)

            16.times{
                game.roll(0)
            }
        expect:
            game.score() == 24
    }

//    def "Does a perfect game score 300?"() {
//        setup:
//            Game game = new Game()
//            12.times{
//                game.roll(10)
//            }
//            //game.roll(9)
//            //game.roll(0)
//
//        expect:
//            game.score() == 300
//    }

}
