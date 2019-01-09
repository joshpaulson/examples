require './src/bowling'

describe 'bowling' do
  game = Game.new()

  it "should return zero as the score for a perfectly bad game" do
    20.times {
      game.roll(0)
    }
    expect(game.score()).to eq 0
  end

  it "should return 20 as the score when one point is scored in each frame" do 
    20.times {
      game.roll(1)
    }
    expect(game.score()).to eq 20
  end
  
  it "should return 16 as the score for a spare, followed by a three pin frame with no other pins in any frame" do
    game.roll(5)
    game.roll(5)
    game.roll(3)
    
    17.times{
      game.roll(0)
    }
    expect(game.score()).to eq 16
  end

end


