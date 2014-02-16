function [W] = createCovarList(A,w)

k = length(A);
N(:,:,k) = zeros(size(A(:,:,1),2));

for i = 1 : k
    N(:,:,i) = weightedCovariance(A(:,:,i),w);
end

W = N;