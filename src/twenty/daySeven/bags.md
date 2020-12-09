### Part One
```mathematica
GetEdges[l_]:=Module[{start, nodes, edges}, start = First[l]; nodes = Drop[l,1]; Map[start -> #&, nodes]]
options = ReadList[OpenRead["~/Desktop/bags.txt"], String]
Map[StringTrim[StringSplit[StringReplace[#, {{Whitespace ~~ "bags" ~~ Whitespace ~~ "contain" ~~ Whitespace ~~ NumberString, "bags," ~~ Whitespace ~~ NumberString, "bag," ~~ Whitespace ~~ NumberString} -> "|", {"no other bags", "bags.", "bag."} -> ""}], "|"]]&, options]
graph = Graph[Flatten[Map[GetEdges, %]]]

paths = Map[FindPath[graph, #, "shiny gold"]&, VertexList[graph]]
Length[Select[paths, UnsameQ[#, {}] &]] 
```
#### Output
```
In[133]:= Map[StringTrim[StringSplit[StringReplace[#, {{Whitespace ~~ "bags" ~~ 
Whitespace ~~ "contain" ~~ Whitespace ~~ NumberString, "bags," ~~ Whitespace ~~ 
NumberString, "bag," ~~ Whitespace ~~ NumberString} -> "|", {"no other bags", "b
ags.", "bag."} -> ""}], "|"]]&, options]                                        

Out[133]= {{wavy green, posh black, faded green, wavy red}, 
....
 {vibrant cyan, vibrant plum}}

In[134]:= graph = Graph[Flatten[Map[GetEdges, %]]]                              
                                                                                
Out[134]= Graph[<594>, <1403>]                                                  
                                                                                
In[135]:= paths = Map[FindPath[graph, #, "shiny gold"]&, VertexList[graph]]     
                                                                                
Out[135]= {{}, {}, {}, {}, {{dotted chartreuse, light beige, bright plum,
....
In[136]:= Length[Select[paths, UnsameQ[#, {}] &]]                               

Out[136]= 169
```