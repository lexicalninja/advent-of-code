import cmath
def numSuccesses(time, dist):
    a = -1
    b = time
    c = -dist
    d = (b**2) - (4*a*c)
    sol1 = int(abs((-b+cmath.sqrt(d))/(2*a)))
    sol2 = int(abs((-b-cmath.sqrt(d))/(2*a)))
    return abs(sol1 - sol2)

with open('input.txt', 'r') as file:
    lines = file.readlines()
    times = [int(i) for i in lines[0][5:].strip().split()]
    dists = [int(i) for i in lines[1][9:].strip().split()]

successes = 1
for r, t in enumerate(times):
    d = dists[r]
    successes *= numSuccesses(t, d)
print( successes)