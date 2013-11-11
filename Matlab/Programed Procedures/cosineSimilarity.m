function [z] = cosineSimilarity(A,B)
   
    z  = (A')*B/(norm(A)*norm(B));
    
