function [z] = createRandomArrays(n)

for i = 1:n
    A(:,:,i) = rand(3);
end

z = A;