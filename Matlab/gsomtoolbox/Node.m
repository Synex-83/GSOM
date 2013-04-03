classdef Node
    properties (SetAccess = private)
        %accumlated error of the training process
        accumulated_error = 0 
    end
    properties
        %weight vector of the node
        weights = []
        %X coordinate of the node in the 2D lattice
        X = 0
        %Y coordinate of the node in the 2D lattice
        Y = 0
        %neighbours of the node
        neighbours = []
    end
end