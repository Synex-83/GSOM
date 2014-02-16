function plotCovariance(a,rows,ele)
    nm = length(a);
    nv = 3;

    figure
    minc = min(a(:));
    maxc = max(a(:));
    for i = 1 : nm
        subplot(rows, ele, i)     % adjust such that the number of subplots is >= nm
        C = a(:, :, i);
        C(nv + 1, nv + 1) = 0;
        pcolor((0 : nv),(0 : nv), double(C))
        axis equal tight
        caxis([minc, maxc])
    end