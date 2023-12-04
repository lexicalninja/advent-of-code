import re

blueMax = 14
redMax = 12
greenMax = 13

acc = 0
with open('input.txt', 'r') as file:
    lines = file.readlines()
    for line in lines:
        blues = max([int(x[0:x.index(" ")]) for x in re.findall('\d+ blue', line)])
        if blues > blueMax: continue
        reds = max([int(x[0:x.index(" ")]) for x in re.findall('\d+ red', line)])
        if reds > redMax: continue
        greens = max([int(x[0:x.index(" ")]) for x in re.findall('\d+ green', line)])
        if greens > greenMax: continue
        acc += int(line[5: line.index(':')])
print(acc)