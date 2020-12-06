### Part One
```mathematica
Total[Map[CountDistinct[
	Characters[#]]&,StringSplit[StringReplace[
		StringReplace[Import["~/Desktop/customs.txt", "String"], "\n\n" -> "|"], "\n" -> ""], "|"]
]]
```
#### Output
```
In[110]:= Total[Map[CountDistinct[ Characters[#]]&,StringSplit[StringReplace[ St
ringReplace[Import["~/Desktop/customs.txt", "String"], "\n\n" -> "|"], "\n" -> "
"], "|"] ]]                                                                     

Out[110]= ANSWER
```
### Part Two
```mathematica
FindIntersection[l_]:=Module[{acc = List[]}, For[i=1,i <= Length[l], i++,acc = If[i == 1, l[[i]], Intersection[l[[i]], acc]]]; acc]
Total[Map[Length, Map[FindIntersection, 
	Map[Characters, Map[StringSplit[#," "]&,StringSplit[StringReplace[
		StringReplace[Import["~/Desktop/customs.txt", "String"], "\n\n" -> "|"], "\n" -> " "], "|"]]
]]] ]
```
#### Output
```
In[1]:= FindIntersection[l_]:=Module[{acc = List[]}, For[i=1,i <= Length[l], i
++,acc = If[i == 1, l[[i]], Intersection[l[[i]], acc]]]; acc]                   

In[2]:= Total[Map[Length, Map[FindIntersection,                               
          Map[Characters, Map[StringSplit[#," "]&,StringSplit[StringReplace[    
          StringReplace[Import["~/Desktop/customs.txt", "String"], "\n\n" -> "|"
], "\n" -> " "], "|"]]                                                          
          ]]] ]                                                                 

Out[2]= ANSWER
```