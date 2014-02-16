function [y] = compare(A)

k = length(A);
R = zeros(3);
c = 0;
for i = 1:k
    for j = 1:k
        if i == j
            continue
        else
            R =  A(:,:,i) == A(:,:,j);
            if R == ones(3)
                c = c + 1;
            end
        end 
    end
end

y = c;
