
with open('input.txt', 'r') as file:
    lines = file.readlines()

print(lines)
r = len(lines)
c = len(lines[0])
adj = [['']*r] * c
vals = [['']*r] * c
for i, line in enumerate(lines):
    print(line.strip('\n'))
    for j, c in enumerate(line.strip('\n')):
        if(c in '1234567890'):
            print(i,j, c)
            vals[i][j] = c
        if(c not in '1234567890.'):
#             adj[i-1][j-1] = adj[i-1][j] = adj[i-1][j+1] = adj[i][j-1] = adj[i][j] = adj[i][j+1] = adj[i+1][j-1] = adj[i+1][j] = adj[i+1][j+1] = 1
            adj[i][j] = 1
for r in adj:
    print(r)
for r in vals:
    print(r)