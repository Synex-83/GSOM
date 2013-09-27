function [z] = calculateAlpha(w)
    squareTotal = 0;
    for i = 1:size(w,2),
        squareTotal = squareTotal + w(1,i)^2;
    end
    
    z = 1 / (1 - squareTotal);