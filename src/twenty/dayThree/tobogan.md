```mathematica
SplitHill[l_] := StringSplit[l, ""]
hill = Map[SplitHill, ReadList[OpenRead["~/Desktop/hill.txt"], String]]
GetX[x_,y_,l_]:=x - (Floor[(x-1)/l] * l)
TooFar[hill_,y_]:=y > Length[hill]
TreeHitPoint[hill_,y_,x_]:=If[hill[[y,x]]=="#", 1, 0]
CheckForTreesRecursive[hill_,y_,dy_,dx_]:=Module[{x},If[TooFar[hill,y], 0, x=GetX[(dx*y) - 2, y, Length[hill[[y]]]];TreeHitPoint[hill,y,x] + CheckForTreesRecursive[hill,y+dy, dy, dx]]]
CheckForTreesRecursive[hill,1,1,3] 
```
### Output
```
Wolfram Language 12.1.1 Engine for Mac OS X x86 (64-bit)
Copyright 1988-2020 Wolfram Research, Inc.

In[1]:= SplitHill[l_] := StringSplit[l, ""]                                     

In[2]:= hill = Map[SplitHill, ReadList[OpenRead["~/Desktop/hill.txt"], String]] 

Out[2]= {{., ., ., ., ., ., ., #, ., ., ., ., ., ., ., ., ., ., ., ., ., ., ., 
......
>     ., ., ., ., #, ., #}}

In[3]:= GetX[x_,y_,l_]:=x - (Floor[(x-1)/l] * l)                                

In[4]:= TooFar[hill_,y_]:=y > Length[hill]                                      

In[5]:= TreeHitPoint[hill_,y_,x_]:=If[hill[[y,x]]=="#", 1, 0]                   

In[6]:= CheckForTreesRecursive[hill_,y_,dy_,dx_]:=Module[{x},If[TooFar[hill,y], 
0, x=GetX[(dx*y) - 2, y, Length[hill[[y]]]];TreeHitPoint[hill,y,x] + CheckForTre
esRecursive[hill,y+dy, dy, dx]]]                                                

In[7]:= CheckForTreesRecursive[hill,1,1,3]                                      

Out[7]= ANSWER
```