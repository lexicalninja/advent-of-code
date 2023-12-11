coords = {}
with open('input.txt', 'r') as file:
    lines = [l.strip() for l in file.readlines()]
instructions = lines.pop(0)
lines.pop(0)
for line in lines:
    a = line.replace('=', '').replace('(', '').replace(')', '').replace(',', '').split()
    coords[a[0]] = {'L': a[1], 'R': a[2]}
steps = 0
loc = 'DVA'
while loc[-1] != 'Z':
    for i in instructions:
        steps += 1
        loc = coords[loc][i]
        if loc[-1] == 'Z': break
print(steps)