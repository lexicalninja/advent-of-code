
points = 0
with open('input.txt', 'r') as file:
    lines = file.readlines()
    for line in lines:
        mine, win = line.split(':')[1].split('|')
        right = set(mine.strip().split()) & set(win.strip().split())
        pow = len(right) - 1
        if pow >= 0: points += 2**pow
    print(points)