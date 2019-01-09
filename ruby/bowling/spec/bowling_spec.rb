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

end


