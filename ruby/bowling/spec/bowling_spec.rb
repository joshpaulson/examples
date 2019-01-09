require './src/bowling'

describe 'bowling' do

  game = Game.new()

  it "Should return zero as the score for a perfectly bad game" do

    20.times {
      game.roll(0)
    }

    expect(game.score()).to eq 0
  end

end


