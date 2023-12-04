
import re

vals = {
    "one": 1,
    "two": 2,
    "three": 3,
    "four": 4,
    "five": 5,
    "six": 6,
    "seven": 7,
    "eight": 8,
    "nine": 9
}

with open('input.txt', 'r') as file:
    lines = file.readlines()

# search = "(?=(one|two|three|four|five|six|seven|eight|nine|\\d))"
acc = 0
for line in lines:
    numWords = re.findall(r"(?=(one|two|three|four|five|six|seven|eight|nine|\d))", line)
    print( numWords)
    if not numWords[0].isnumeric():
        acc += vals[numWords[0]] * 10
    else:
        acc += int(numWords[0]) * 10
    if not numWords[-1].isnumeric():
        acc += vals[numWords[-1]]
    else:
        acc += int(numWords[-1])
print(acc)

# line = "f4twoner"
# nums = re.findall(r"(?=(one|two|three|four|five|six|seven|eight|nine|\d))", line)
# print(nums)