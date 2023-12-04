import re

acc = 0
with open('input.txt', 'r') as file:
    lines = file.readlines()
    for line in lines:
        blues = max([int(x[0:x.index(" ")]) for x in re.findall('\d+ blue', line)])
        reds = max([int(x[0:x.index(" ")]) for x in re.findall('\d+ red', line)])
        greens = max([int(x[0:x.index(" ")]) for x in re.findall('\d+ green', line)])
        acc += reds * greens * blues
print(acc)

