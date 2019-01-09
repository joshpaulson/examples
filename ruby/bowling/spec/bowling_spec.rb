require './src/bowling'

describe 'bowling' do
  before(:each) do
    @game = Game.new()
  end


  it "should return zero as the score for a perfectly bad game" do
    
    20.times {
      @game.roll(0)
    }
    expect(@game.score()).to eq 0
  end

  it "should return 20 as the score when one point is scored in each frame" do 
    20.times {
      @game.roll(1)
    }
    expect(@game.score()).to eq 20
  end
  
  it "should return 16 as the score for a spare, followed by a three pin frame with no other pins in any frame" do
    
    @game.roll(5)
    @game.roll(5)
    @game.roll(3)
    
    17.times{
      @game.roll(0)
    }
    expect(@game.score()).to eq 16
  end

  it "should return 24 as the score for a strike followed by 3 then 4, then no other pins in any frame" do
    @game.roll(10)
    @game.roll(3)
    @game.roll(4)

    16.times{
        @game.roll(0)
    }
    expect(@game.score()).to eq 24
  
  end
  
  it "should return 300 for a perfect game" do
    
    12.times{
      @game.roll(10)
    }

    expect(@game.score()).to eq 300
  end

  it "should return a score of 38 for a strike followed by a 9-1 spare followed by 4 pins" do
    @game.roll(10)
    @game.roll(9)
    @game.roll(1)
    @game.roll(4)
    @game.roll(0)

    14.times{
        @game.roll(0)
    }
    expect(@game.score()).to eq 38
  end

  it "should return a score of 87 for three strikes then 9 pins" do
    3.times{
        @game.roll(10)
    }
    @game.roll(9)
    @game.roll(0)
    16.times{
        @game.roll(0)
    }

    expect(@game.score()).to eq 87
  end


  it "should return a score of 237 for eight strikes then 9 pins" do
    8.times{
        @game.roll(10)
    }
    @game.roll(9)
    @game.roll(0)

    @game.roll(0)
    @game.roll(0)

    expect(@game.score()).to eq 237
  end
   
  it "should return a score of 267 for nine strikes then 9 pins" do
    9.times{
      @game.roll(10)
    }
    @game.roll(9)
    @game.roll(0)

    expect(@game.score()).to eq 267
  end

  it "should return a score of 288 for an almost perfect game with 9 pins in final" do
    10.times{
        @game.roll(10)
    }
    @game.roll(9)
    @game.roll(0)

    expect(@game.score()).to eq 288
  end

  # it "should return a score of 44 for a spare followed by a strike then 3 and 4" do

  #   @game.roll(9)
  #   @game.roll(1)

  #   @game.roll(10)

  #   @game.roll(3)
  #   @game.roll(4)

  #   expect(@game.score()).to eq 44
  # end

  it "should return a score of 61 for two strikes followed by a 5-5 spare" do

    gameRolls = [ 10, 10, 5,5, 3,0,  0,0,  0,0, 0,0, 0,0, 0,0, 0,0]
    
    gameRolls.each do |it|
      @game.roll( it )
    end

    expect(@game.score()).to eq 61
  end


end


