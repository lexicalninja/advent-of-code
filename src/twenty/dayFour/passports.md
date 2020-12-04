### Part One
```mathematica
data = StringSplit[StringReplace[StringReplace[Import["~/Desktop/passports.txt", "String"], "\n\n" -> "|"], "\n" -> " "], "|"]
records=Map[GetFields, Map[GetAsList, data], 1]
req = { "byr","iyr", "eyr", "hgt", "hcl", "ecl", "pid" }
IsValid[l_]:=ContainsAll[l, req]
Count[Map[IsValid, records], True] 
```

#### Output
```Wolfram Language 12.1.1 Engine for Mac OS X x86 (64-bit)
   Copyright 1988-2020 Wolfram Research, Inc.
   
   In[1]:= data = StringSplit[StringReplace[StringReplace[Import["~/Desktop/passpor
   ts.txt", "String"], "\n\n" -> "|"], "\n" -> " "], "|"]                          
   
   Out[1]= {hcl:5d90f0 cid:270 ecl:#66dc9c hgt:62cm byr:1945 pid:63201172\
   ....... 
   >     eyr:2023 iyr:2017 byr:1994}
   
   In[2]:= GetAsList[s_]:=StringSplit[s, " "]                                      
   
   In[3]:= GetFields[s_]:=StringTake[s, 3]                                         
   
   In[4]:= records=Map[GetFields, Map[GetAsList, data], 1]                         
   
   Out[4]= {{hcl, cid, ecl, hgt, byr, pid, eyr}, {ecl, byr, iyr, eyr, pid},  
   ......
   >    {hcl, pid, ecl, hgt, cid, eyr, iyr, byr}}
   
   In[5]:= req = { "byr","iyr", "eyr", "hgt", "hcl", "ecl", "pid" }                
   
   Out[5]= {byr, iyr, eyr, hgt, hcl, ecl, pid}
   
   In[6]:= IsValid[l_]:=ContainsAll[l, req]                                        
   
   In[7]:= Count[Map[IsValid, records], True]                                      
   
   Out[7]= 216
   
   In[8]:=  ANSWER
```
### Part Two
```mathematica
GetAsList[s_]:=StringSplit[s, " "]
ValidBY[a_]:=If[!KeyExistsQ[a, "byr"], False,Between[ToNumber[a["byr"]], {1920, 2002}]]
ValidIY[a_]:=If[!KeyExistsQ[a, "iyr"], False,Between[ToNumber[a["iyr"]], {2010, 2020}]]
ValidEY[a_]:=If[!KeyExistsQ[a, "eyr"], False,Between[ToNumber[a["eyr"]], {2020, 2030}]]
ValidHtCm[a_]:=If[!KeyExistsQ[a, "hgt"], False,Between[GetHtVal[a["hgt"]], {150,193}]]
ValidHtIn[a_]:=If[!KeyExistsQ[a, "hgt"], False,Between[GetHtVal[a["hgt"]], {59,76}]]
ValidHt[a_]:=If[!KeyExistsQ[a, "hgt"], False, If[GetUnits[a["hgt"]]=="cm", ValidHtCm[a], ValidHtIn[a]]]
ValidHC[a_]:=If[!KeyExistsQ[a, "hcl"], False,StringMatchQ[a["hcl"], RegularExpression["^#(?:[0-9a-fA-F]{3}){1,2}$"]]]
ValidEC[a_]:=If[!KeyExistsQ[a, "ecl"], False,MemberQ[{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"}, a["ecl"]]]
ValidPN[a_]:=If[!KeyExistsQ[a, "pid"], False,StringMatchQ[a["pid"], RegularExpression["^\\d{9}$"]]]
ToNumber[s_]:=Interpreter["Number"][s]
GetUnits[s_]:=StringTake[s, {StringLength[s]-1, StringLength[s]}]
GetHtVal[s_]:=ToNumber[StringTake[s, {1, StringLength[s]-2}]]
GetVal[s_]:=StringTake[s,{StringPosition[s, ":"][[1,1]]+1, StringLength[s]}]   
GetKey[s_]:=StringTake[s,{1, StringPosition[s, ":"][[1,1]]-1}]
ToPair[s_]:=GetKey[s]->GetVal[s]
ToAssoc[l_]:=Association[Map[ToPair, l]]
IsValid[a_]:=ValidBY[a]&&ValidIY[a]&&ValidEY[a]&&ValidHC[a]&&ValidEC[a]&&ValidPN[a]&&ValidHt[a]
data = StringSplit[StringReplace[StringReplace[Import["~/Desktop/passports.txt", "String"], "\n\n" -> "|"], "\n" -> " "], "|"]
records = GetAsList[data]
assoc = Map[ToAssoc, records]
Count[Map[IsValid, assoc], True]
```
#### Ouput
```
Copyright 1988-2020 Wolfram Research, Inc.

In[1]:= GetAsList[s_]:=StringSplit[s, " "]                                      

In[2]:= ValidBY[a_]:=If[!KeyExistsQ[a, "byr"], False,Between[ToNumber[a["byr"]],
 {1920, 2002}]]                                                                 

In[3]:= ValidIY[a_]:=If[!KeyExistsQ[a, "iyr"], False,Between[ToNumber[a["iyr"]],
 {2010, 2020}]]                                                                 

In[4]:= ValidEY[a_]:=If[!KeyExistsQ[a, "eyr"], False,Between[ToNumber[a["eyr"]],
 {2020, 2030}]]                                                                 

In[5]:= ValidHtCm[a_]:=If[!KeyExistsQ[a, "hgt"], False,Between[GetHtVal[a["hgt"]
], {150,193}]]                                                                  

In[6]:= ValidHtIn[a_]:=If[!KeyExistsQ[a, "hgt"], False,Between[GetHtVal[a["hgt"]
], {59,76}]]                                                                    

In[7]:= ValidHt[a_]:=If[!KeyExistsQ[a, "hgt"], False, If[GetUnits[a["hgt"]]=="cm
", ValidHtCm[a], ValidHtIn[a]]]                                                 

In[8]:= ValidHC[a_]:=If[!KeyExistsQ[a, "hcl"], False,StringMatchQ[a["hcl"], Regu
larExpression["^#(?:[0-9a-fA-F]{3}){1,2}$"]]]                                   

In[9]:= ValidEC[a_]:=If[!KeyExistsQ[a, "ecl"], False,MemberQ[{"amb", "blu", "brn
", "gry", "grn", "hzl", "oth"}, a["ecl"]]]                                      

In[10]:= ValidPN[a_]:=If[!KeyExistsQ[a, "pid"], False,StringMatchQ[a["pid"], Reg
ularExpression["^\\d{9}$"]]]                                                    

In[11]:= ToNumber[s_]:=Interpreter["Number"][s]                                 

In[12]:= GetUnits[s_]:=StringTake[s, {StringLength[s]-1, StringLength[s]}]      

In[13]:= GetHtVal[s_]:=ToNumber[StringTake[s, {1, StringLength[s]-2}]]          

In[14]:= GetVal[s_]:=StringTake[s,{StringPosition[s, ":"][[1,1]]+1, StringLength
[s]}]                                                                           

In[15]:= GetKey[s_]:=StringTake[s,{1, StringPosition[s, ":"][[1,1]]-1}]         

In[16]:= ToPair[s_]:=GetKey[s]->GetVal[s]                                       

In[17]:= ToAssoc[l_]:=Association[Map[ToPair, l]]                               

In[18]:= IsValid[a_]:=ValidBY[a]&&ValidIY[a]&&ValidEY[a]&&ValidHC[a]&&ValidEC[a]
&&ValidPN[a]&&ValidHt[a]                                                        

In[19]:= data = StringSplit[StringReplace[StringReplace[Import["~/Desktop/passpo
rts.txt", "String"], "\n\n" -> "|"], "\n" -> " "], "|"]                         

Out[19]= {hcl:5d90f0 cid:270 ecl:#66dc9c hgt:62cm byr:1945 pid:63201172\
..... 
>     eyr:2023 iyr:2017 byr:1994}

In[20]:= records = GetAsList[data]                                              

Out[20]= {{hcl:5d90f0, cid:270, ecl:#66dc9c, hgt:62cm, byr:1945, pid:63201172,  
.......
>     cid:340, eyr:2023, iyr:2017, byr:1994}}

In[21]:= assoc = Map[ToAssoc, records]                                          

Out[21]= {<|hcl -> 5d90f0, cid -> 270, ecl -> #66dc9c, hgt -> 62cm, 
.... 
>     eyr -> 2023, iyr -> 2017, byr -> 1994|>}

In[22]:= Count[Map[IsValid, assoc], True]                                       

Out[22]= ANSWER
```