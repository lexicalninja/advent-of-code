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
    time = int(lines[0][5:].strip().replace(' ', ''))
    dist = int(lines[1][9:].strip().replace(' ', ''))
    print(numSuccesses(time, dist))

