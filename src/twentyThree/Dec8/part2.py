coords = {}
import re
import time
with open('input.txt', 'r') as file:
    lines = [l.strip() for l in file.readlines()]
instructions = lines.pop(0)
lines.pop(0)
starts = []
for line in lines:
    a = line.replace('=', '').replace('(', '').replace(')', '').replace(',', '').split()
    coords[a[0]] = {'L': a[1], 'R': a[2]}
    if a[0][-1] == 'A': starts.append(a[0])
print(starts)
locs = starts
endMap = {}
# for loc in locs:
start = 'DVA'
loc = start
current = loc
steps = 0
endMap[start] = []
proceed = True
print(start)
while proceed:
    for i in instructions:
        steps += 1
        loc = coords[loc][i]
        if loc[-1] == 'Z':
            proceed = False
            break
#     for i in range(0, len(instructions)):
#         if(not proceed): break
#         inst = instructions[i]
#         nextInst = instructions[i+1] if i <( len(instructions) -1) else instructions[0]
#         steps += 1
#         current = coords[loc][inst]
# #             print(current)
#         if current[-1] == 'Z':
#             print('z')
#             endMap[start].append(steps)
#             print(steps)
#             if (i < len(instructions) -1  and coords[current][nextInst] == start):
#                 proceed = False
#                 print(endMap)
#                 break
print(endMap)


# while len(locs) != 0:
#     for ndx, i in enumerate(instructions):
#         steps += 1
#         for j in range(0, len(locs)):
#             loc = locs[j]
#             locs[j] = coords[loc][i]
#             if locs[j][-1] == 'Z': ends.append()
# print(steps)

#     while proceed:
#         for ndx, i in enumerate(instructions):
#             steps += 1
#             current = coords[loc][i]
#             if current[-1] == 'Z':
#                 endMap[start].append(steps)
#                 print(steps)
#                 if (ndx < len(instructions) -1): print(ndx)
#                 if (ndx < len(instructions) -1  and coords[current][instructions[ndx + 1]] == start):
#                     proceed = False
#                     print(endMap)
#                     break