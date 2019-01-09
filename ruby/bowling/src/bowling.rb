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
      @first_ball_in_frame = false
    else
      @current_frame[1] = number
      @frames << @current_frame
      @current_frame = []
      @first_ball_in_frame = true
    end
  end

  def extra_score_for_strikes_and_spares()

    score_of_next_frame = 0
    extra_score = 0
    
    for index in (@frames.length - 1).downto(0) do
      print " >#{index}" 
      
      current_frame = @frames[index]
      if( current_frame)
        current_frame_score = current_frame[0]+ current_frame[1]
        
        if ( current_frame_score == 10)
          extra_score +=  score_of_next_frame
          print " extra: #{ extra_score } "
        end
        score_of_next_frame = current_frame_score
      end  
    end
    print "total_extra:#{ extra_score }"
    
    extra_score
  end  

  def score()

    print " frames: #{@frames} length: #{ @frames.length } "

    for frame in @frames do
      @current_score += frame[0]
      @current_score += frame[1]
    end  

    @current_score += extra_score_for_strikes_and_spares()
    print " score:#{ @current_score }"
    
    @current_score
  end  
  
  
end


