package com.kata.bowling

import spock.lang.Specification;

class BowlingGameTest extends Specification{

    def Game game = new Game()

    def "Does a Gutter Game Work?"() {
        setup:
            20.times {
                game.roll(0)
            }

        expect:
            game.score() == 0
    }

    def "Do 20 single pin frames score 20?"(){
        setup:
            20.times {
                game.roll(1)
            }
        expect:
            game.score() == 20
    }

    def "Does One Spare then 3 pins then 17 gutter ball frames score 16?"() {
        setup:
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
            game.roll(10)
            game.roll(3)
            game.roll(4)

            16.times{
                game.roll(0)
            }
        expect:
            game.score() == 24
    }

    def "Does a perfect game score 300?"() {
        setup:
            12.times{
                game.roll(10)
            }

        expect:
            game.score() == 300
    }

    def "Does a strike followed by a 9-1 spare followed by 4 pins score 38"(){
        setup:
        game.roll(10)
        game.roll(9)
        game.roll(1)
        game.roll(4)
        game.roll(0)

        14.times{
            game.roll(0)
        }
        expect:
        game.score() == 38
    }

    def "Does three strikes then 9 pins then gutters score 87?"() {
        setup:
        3.times{
            game.roll(10)
        }
        game.roll(9)
        game.roll(0)
        16.times{
            game.roll(0)
        }

        expect:
        game.score() == 87
    }

    def "Does eight strikes then 9 pins then gutters score 237?"() {
        setup:
        8.times{
            game.roll(10)
        }
        game.roll(9)
        game.roll(0)

        game.roll(0)
        game.roll(0)


        expect:
        game.score() == 237
    }

    def "Does nine strikes then 9 pins then gutters score 267?"() {
        setup:
        9.times{
            game.roll(10)
        }
        game.roll(9)
        game.roll(0)

        expect:
        game.score() == 267
    }
    def "Does an almost perfect game with 9 pins in final frame score 288?"() {
        setup:
        10.times{
            game.roll(10)
        }
        game.roll(9)
        game.roll(0)

        expect:
        game.score() == 288
    }

    def "Does a spare followed by a strike then 3 and 4 then gutters score 44?"() {
        setup:
        game.roll(9)
        game.roll(1)

        game.roll(10)

        game.roll(3)
        game.roll(4)

        expect:
        game.score() == 44
    }

    def "Does two strikes followed by a 5-5 spare, then 3 then gutters score 61?"(){
        setup:
        List gameRolls = [ 10, 10, 5,5, 3,0,  0,0,  0,0, 0,0, 0,0, 0,0, 0,0]
        gameRolls.each() { game.roll( it ) };

        expect:
            game.score() == 61



    }

    def "Does the score calculate correctly for a bunch of games?"() {
        setup:
        gameRolls.each() { game.roll( it ) };

        expect:
        game.score()  == result;

        where:
        result << [ 300,10,   102,170,  200,200,  200,104,    77,162,   65,36,   90,60,  5,6]
        gameRolls << [[ 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,   10, 10],
                      [ 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0, 1,0],

                      [ 9,1, 7,1, 5,1, 4,1, 10,   7,1, 9,1, 10,   2,3,  0,0 ],
                      [ 10, 1,9, 10, 1,9, 10, 1,9, 10, 1,9, 10, 0,0],

                      [ 10, 1,9, 10, 1,9, 10, 1,9, 10, 1,9, 10, 1,9, 10],
                      [ 10, 1,9, 10, 1,9, 10, 1,9, 10, 1,9, 10, 1,9, 10, 1,9],

                      [ 10, 1,9, 10, 1,9, 10, 1,9, 10, 1,9, 10, 1,9, 10, 10, 10],
                      [ 5,1, 9,1, 10, 10, 9,1, 3,1, 0,0, 0,0, 5,1, 5,1], // 104

                      [ 9,1, 10, 10, 1,9, 3,0,  0,0, 0,0, 0,0, 0,0, 0,0], // 79
                      [ 9,1, 10, 10, 10, 5,5,  3,1, 10, 2,8, 10, 0,0], // 162


                      [ 10, 10, 9,1, 3,0,  0,0,  0,0, 0,0, 0,0, 0,0, 0,0], // 65
                      [ 10, 9,1, 3,0,  0,0,  0,0,  0,0, 0,0, 0,0, 0,0, 0,0], // 36

                      [ 0,0, 0,0, 0,0, 0,0, 0,0, 0,0,  0,0,  10, 10, 10, 10, 10], //90
                      [ 0,0, 0,0, 0,0, 0,0, 0,0, 0,0,  0,0,  10, 5,5, 10, 9, 1], //60

                      [ 4,0,1,0 ],
                      [ 4,0,2,0 ] ]
    }

}

















