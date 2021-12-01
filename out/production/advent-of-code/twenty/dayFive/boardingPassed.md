### Part One

```mathematica
FindLocation[arr_, min_, max_, lChar_, rChar_]:=Module[{left = min, right = max, a = arr}, While[Length[a] > 1, If[First[a] == lChar, right = Floor[(right + left) / 2], left = Ceiling[(right + left) /2]]; a = Drop[a,1]];If[First[a] == lChar, left, right]]
FindRow[s_]:=FindLocation[DeleteCases[StringSplit[StringTake[s, {1,7}],""],""], 0, 127, "F", "B"]
FindSeat[s_]:=FindLocation[DeleteCases[StringSplit[StringTake[s, {8,10}],""],""], 0, 7, "L", "R"]
FindSeatId[s_]:=FindRow[s] * 8 + FindSeat[s]
boardingPasses = ReadList[OpenRead["~/Desktop/boardingPasses.txt"], String]
Max[Map[FindSeatId, boardingPasses]]
```
#### Output
```
Wolfram Language 12.1.1 Engine for Mac OS X x86 (64-bit)
Copyright 1988-2020 Wolfram Research, Inc.

In[1]:= FindLocation[arr_, min_, max_, lChar_, rChar_]:=Module[{left = min, right = max, a = arr}, While[Length[a] > 1, If[First[a] == lChar, right = Floor[(right + left) / 2], left = Ceiling[(right + left) /2]]; a = Drop[a,1]];If[First[a] == lChar, left, right]]                                                         

In[2]:= FindRow[s_]:=FindLocation[DeleteCases[StringSplit[StringTake[s, {1,7}],""],""], 0, 127, "F", "B"]                                                       

In[3]:= FindSeat[s_]:=FindLocation[DeleteCases[StringSplit[StringTake[s, {8,10}],""],""], 0, 7, "L", "R"]                                                       

In[4]:= FindSeatId[s_]:=FindRow[s] * 8 + FindSeat[s]                            

In[5]:= boardingPasses = ReadList[OpenRead["~/Desktop/boardingPasses.txt"], String]                                                                             

Out[5]= {FBBBBBBLRR, FFFBFFFLLR, FFBBBBBRRL, BFFBFFBRLL, BFBFBBFLLR,  
 ....
>    FFBFBFFRRR, BFFBBBBRLL}

In[6]:= Max[Map[FindSeatId, boardingPasses]]                                    

Out[6]= ANSWER
```
### Part Two

```mathematica
Sort[Map[FindSeatId, boardingPasses]]
FindMySeat[arr_]:=Module[{r = arr[[Length[arr]]]}, Print[r];Complement[Range[arr[[1]],r], arr][[1]]]
FindMySeat[Map[FindSeatId, boardingPasses]]
```
#### Output
```
In[9]:= Sort[Map[FindSeatId, boardingPasses]]                                   
                                                                                
Out[9]= {49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 
 .....
>    793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806}

In[10]:= FindMySeat[arr_]:=Module[{r = arr[[Length[arr]]]},Complement[Range[arr[
[1]],r], arr][[1]]]                                                             

In[11]:= FindMySeat[Map[FindSeatId, boardingPasses]]                            

Out[11]=
```