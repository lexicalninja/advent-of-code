Part One
Part One
```mathematica
RunProgram[l_]:=Module[{pos=1, acc=0, op=StringSplit[l[[1]], Whitespace], curr}, 
	While[l[[pos]] != "", curr = pos; If[op[[1]] == "nop", pos++; op=StringSplit[l[[pos]], Whitespace], 
		If[op[[1]]=="jmp", pos+=ToExpression[op[[2]]]; op=StringSplit[l[[pos]], Whitespace], acc+=ToExpression[op[[2]]]; pos++; op=StringSplit[l[[pos]], Whitespace]]
	]; l[[curr]]="";];acc
]
SetAttributes[RunProgram, HoldFirst];
l = ReadList[OpenRead["~/Desktop/boot.txt"], String]
RunProgram[l]
```


Part Two
```mathematica
RunProgram[l_]:=Module[{pos=1, acc=0, op=StringSplit[l[[1]], Whitespace], curr}, 
	While[l[[pos]] != "" && pos <= Length[l],curr = pos; If[op[[1]] == "nop",pos++; op=StringSplit[l[[pos]], Whitespace], 
		If[op[[1]]=="jmp",pos+=ToExpression[op[[2]]]; op=StringSplit[l[[pos]], Whitespace], acc+=ToExpression[op[[2]]]; pos++; op=StringSplit[l[[pos]], Whitespace]]
	]; l[[curr]]=""; If[pos > Length[l],Print["acc " , acc]; acc, -1]
]]

Swap[s_]:=If[StringContainsQ[s, "nop"], StringReplace[s, "nop"->"jmp"], StringReplace[s,"jmp"->"nop"]]
list = ReadList[OpenRead["~/Desktop/boot.txt"], String]
SetAttributes[RunProgram, HoldFirst]
Block[{l = list, l2 =list ,l3 =list, acc = -1, pos = 0}, While[Length[l2] > 0, pos++; p = First[l2]; l2 = Drop[l2, 1]; 
	If[StringContainsQ[p, "jmp"]||StringContainsQ[p, "nop"], l3 = l;l3[[pos]] = Swap[p]; If[RunProgram[l3] != -1, Abort[]]
	]
]]
```