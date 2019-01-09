# Code Kata to show Test Driven Development with Ruby

class Game
  @current_score 

  def initialize()
    @current_score = 0
  end

  def roll(number)
    @current_score = @current_score + number
  end

  def score()
    @current_score
  end  
  
  
end


