import sys
import re

with open('input.txt', 'r') as file:
    lines = file.read().split("\n\n")

seeds = [int(x) for x in lines.pop(0)[6:].split()]

maps = {}
lowest = sys.maxsize
for line in lines:
    l = line.split(':')
    name = l.pop(0)
    vals = [[int(i) for i in ll] for ll in[re.findall('\d+', i) for i in l[0].strip().split('\n')]]
    maps[name] = vals
for s in seeds:
    dest = s
    for k in maps:
        for r in maps[k]:
            if dest in range(r[1], r[1] + r[2]):
                dest = r[0] + (dest - r[1])
                break
    if dest < lowest: lowest = dest
print(lowest)