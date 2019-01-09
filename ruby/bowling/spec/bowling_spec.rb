require './src/bowling'

describe 'bowling' do
  

  it "should return zero as the score for a perfectly bad game" do
    game = Game.new()
    20.times {
      game.roll(0)
    }
    expect(game.score()).to eq 0
  end

  it "should return 20 as the score when one point is scored in each frame" do 
    game = Game.new()
    20.times {
      game.roll(1)
    }
    expect(game.score()).to eq 20
  end
  
  it "should return 16 as the score for a spare, followed by a three pin frame with no other pins in any frame" do
    game = Game.new()

    game.roll(5)
    game.roll(5)
    game.roll(3)
    
    17.times{
      game.roll(0)
    }
    expect(game.score()).to eq 16
  end

  it "should return 24 as the score for a strike followed by 3 then 4, then no other pins in any frame" do
    game = Game.new()
    game.roll(10)
    game.roll(3)
    game.roll(4)

    16.times{
        game.roll(0)
    }
    expect(game.score()).to eq 24
  
  end
  
  it "should return 300 for a perfect game" do
    game = Game.new()
    
    12.times{
      game.roll(10)
    }

    expect(game.score()).to eq 300
  end


end


