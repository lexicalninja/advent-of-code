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
   
   In[8]:=  ANSWER```