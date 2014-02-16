function [y] = weightedCovariance(x,w)
    
    weightValues = w; %weighted vector values to calculate the weighted mean
    inputVector = x; %input vectors
    alpha = 0;
    covariance = zeros(size(x,2));
   % if size(inputVector,2) == size(weightValues,2)
        alpha = calculateAlpha(w);
        meanVector = [0.6,0.3,0.1];  %weightedMean(w,x);
         
        for i = 1:size(covariance)
            for j = 1:size(covariance)
              
                temp1 = x(:,i);
                temp2 = x(:,j);
                
                for k = 1:size(inputVector)                    
                    covariance(i,j) = covariance(i,j) + weightValues(1,k)*(temp1(k) - meanVector(1,i))*(temp2(k) - meanVector(1,j));
                end
                %
                covariance(i,j) = alpha*covariance(i,j);
                %covariance(i,j)
            end
        end
        
        y = covariance;
     
   % else
    %    error('The weights values and the number of input vectors do not agree');
   % end
    
     