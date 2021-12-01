### Part One
```mathematica
ParsePasswordSet[entry_]:=DeleteCases[StringSplit[entry, {"-", " ", ":"}], ""]
AssociatePwList[list_]:=Association[{mn->ToNumber[list[[1]]], mx->ToNumber[list[[2]]], c->list[[3]], pw->list[[4]]}]
ToNumber[s_]:=Interpreter["Number"][s]
PassesRules[entry_]:=Module[{pwSet=AssociatePwList[ParsePasswordSet[entry]], letters, min, max, count}, 
	letters=LetterCounts[pwSet[pw]];
 	min=ToNumber[pwSet[mn]];
 	max=ToNumber[pwSet[mx]];
 	count=ToNumber[letters[ToString[pwSet[c]]]];
 	Boole[Between[count,{min, max}]]]
passwords = ReadList[OpenRead["~/Desktop/pwAudit.txt"], String]
Count[Map[PassesRules, passwords], 1]
```
##### Output:
```
Wolfram Language 12.1.1 Engine for Mac OS X x86 (64-bit)
Copyright 1988-2020 Wolfram Research, Inc.

In[1]:= ParsePasswordSet[entry_]:=DeleteCases[StringSplit[entry, {"-", " ", ":"}], ""]                                                                          

In[2]:= AssociatePwList[list_]:=Association[{mn->ToNumber[list[[1]]], mx->ToNumber[list[[2]]], c->list[[3]], pw->list[[4]]}]                                    

In[3]:= ToNumber[s_]:=Interpreter["Number"][s]                                  

In[4]:= PassesRules[entry_]:=Module[{pwSet=AssociatePwList[ParsePasswordSet[entry]], letters, min, max, count},                                                 
    	letters=LetterCounts[pwSet[pw]];                                        
	    min=ToNumber[pwSet[mn]];                                               
	    max=ToNumber[pwSet[mx]];                                               
	    count=ToNumber[letters[ToString[pwSet[c]]]];                           
	    Boole[Between[count,{min, max}]]]                                      

In[5]:= passwords = ReadList[OpenRead["~/Desktop/pwAudit.txt"], String]         

Out[5]= {3-4 t: dttt, 5-7 l: llmlqmblllh, 3-10 g: gggxwxggggkgglklhhgg, 
.... 
>    14-16 p: tpkppppppppppppppppp}

In[6]:= Count[Map[PassesRules, passwords], 1]                                   

Out[6]= ANSWER
```
### Part Two
```mathematica
ParsePasswordSet[entry_]:=DeleteCases[StringSplit[entry, {"-", " ", ":"}], ""]
AssociatePwList[list_]:=Association[{exists->ToNumber[list[[1]]], dne->ToNumber[list[[2]]], c->list[[3]], pw->list[[4]]}]
ToNumber[s_]:=Interpreter["Number"][s]
PassesRules[entry_]:=Module[{pwSet=AssociatePwList[ParsePasswordSet[entry]], letters, p1, p2, char}, 
	letters=pwSet[pw];
	char=pwSet[c];
 	p1=pwSet[exists];
 	p2=pwSet[dne];
 	Xor[StringTake[letters, {p1}]==char,StringTake[letters, {p2}]==char]]
passwords = ReadList[OpenRead["~/Desktop/pwAudit.txt"], String]
Count[Map[PassesRules, passwords], True]
```

##### Output
```
   Wolfram Engine activated. See https://www.wolfram.com/wolframscript/ for more information.
   Wolfram Language 12.1.1 Engine for Mac OS X x86 (64-bit)
   Copyright 1988-2020 Wolfram Research, Inc.
   
   In[1]:= ParsePasswordSet[entry_]:=DeleteCases[StringSplit[entry, {"-", " ", ":"}], ""]                                                                          
   
   In[2]:= AssociatePwList[list_]:=Association[{exists->ToNumber[list[[1]]], dne->ToNumber[list[[2]]], c->list[[3]], pw->list[[4]]}]                               
   
   In[3]:= ToNumber[s_]:=Interpreter["Number"][s]                                  
   
   In[4]:= PassesRules[entry_]:=Module[{pwSet=AssociatePwList[ParsePasswordSet[entry]], letters, p1, p2, char},                                                    
           letters=pwSet[pw];                                                      
           char=pwSet[c];                                                          
           p1=pwSet[exists];                                                      
           p2=pwSet[dne];                                                         
           Xor[StringTake[letters, {p1}]==char,StringTake[letters, {p2}]==char]]  
   
   In[5]:= passwords = ReadList[OpenRead["~/Desktop/pwAudit.txt"], String]         
   
   Out[5]= {3-4 t: dttt, 5-7 l: llmlqmblllh, 3-10 g: gggxwxggggkgglklhhgg,
   ...
   >    14-16 p: tpkppppppppppppppppp}
   
   In[6]:= Count[Map[PassesRules, passwords], True]                                
   
   Out[6]= ANSWER
```