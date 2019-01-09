# Code Kata to show Test Driven Development with Ruby

class Game
 
  def initialize()
    @current_score = 0
    @frames = []
    @current_frame = []
    @first_ball_in_frame = true 
    @current_frame_index = 0
  end

  def roll(number)
    
    if (@first_ball_in_frame)
      @current_frame[0] = number
    else
      @current_frame[1] = number
      @frames << @current_frame
      @current_frame = []
      @current_frame_index += 1
    end

    @first_ball_in_frame = !@first_ball_in_frame 
  end

  def extra_score_for_strikes_and_spares()

  end  

  def score()
    for frame in @frames
      @current_score += frame[0]
      @current_score += frame[1]
    end  
    @current_score
  end  
  
  
end


