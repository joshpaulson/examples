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
    
    if (number == 10)
      @current_frame[0] = 10
      @current_frame[1] = 0
      @frames << @current_frame
      @current_frame = []
      @first_ball_in_frame = true

    elsif (@first_ball_in_frame)
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
    first_ball_after_current_frame = 0
    first_ball_after_next_frame = 0
    extra_score = 0
    
    for index in (@frames.length - 1).downto(0) do
      puts " >#{index}" 
      
      current_frame = @frames[index]

      if( current_frame)
        current_frame_score = current_frame[0]+ current_frame[1]
        
        if ( current_frame[0] == 10) # strike
          extra_score +=  score_of_next_frame

          if (first_ball_after_current_frame == 10)
            extra_score += first_ball_after_next_frame
            puts " extra,extra: #{ first_ball_after_next_frame }"
          end
          puts " extra: #{ extra_score } "

        elsif ( current_frame_score == 10 ) # spare
          extra_score += first_ball_after_current_frame  
        end

        score_of_next_frame = current_frame_score
        first_ball_after_next_frame = first_ball_after_current_frame
        first_ball_after_current_frame = current_frame[0]
        
      end  
    end
    puts "total_extra:#{ extra_score }"
    
    extra_score
  end  

  def remove_stike_or_spare_score_for_extra_frames
    first_extra_frame = @frames[10]
    if (first_extra_frame[0] + first_extra_frame[1] == 10)
      if ( @frames[11] )
        @current_score -= @frames[11][0]
      end
    end
  end  

  def remove_normal_score_for_extra_frames
    if @frames.length > 10
      for index in (@frames.length - 1).downto(10) do
        frame = @frames[index]
        puts " remove #{frame}"

        @current_score -= frame[0]
        @current_score -= frame[1]
      end

      remove_stike_or_spare_score_for_extra_frames()
    end 
  end

  def score()

    puts " frames: #{@frames} length: #{ @frames.length } "

    for frame in @frames do
      @current_score += frame[0]
      @current_score += frame[1]
    end  

    @current_score += extra_score_for_strikes_and_spares()
    remove_normal_score_for_extra_frames()

    puts " score:#{ @current_score }"    
    @current_score
  end  
  
  
end


