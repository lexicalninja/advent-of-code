(* Mathematica Source File *)
(* Created by the Wolfram Language Plugin for IntelliJ, see http://wlplugin.halirutan.de/ *)
(* :Author: pasqualelsaxton *)
(* :Date: 2020-12-04 *)
data = StringSplit[StringReplace[StringReplace[Import["~/Desktop/passports.txt", "String"], "\n\n" -> "|"], "\n" -> " "], "|"]
records=Map[GetFields, Map[GetAsList, data], 1]
req = { "byr","iyr", "eyr", "hgt", "hcl", "ecl", "pid" }
IsValid[l_]:=ContainsAll[l, req]
Count[Map[IsValid, records], True]