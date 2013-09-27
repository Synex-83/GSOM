function [z] = weightedMean(w,x)
    weights = w;
    inputs = x;
    d = size(x,1);
    meanV = zeros(size(weights,2),1)';
    if size(weights,2) == size(inputs,2)
        for i = 1:d,
            for j = 1:size(meanV,2)
                meanV(1,j) = meanV(1,j) + weights(1,j)*inputs(i,j);
            end
        end
        
        z = meanV;
    else
        error('The weights values and the number of input vectors do not agree');
    end 
        