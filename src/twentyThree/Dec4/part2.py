

with open('input.txt', 'r') as file:
    lines = file.readlines()
    copies = [1 for l in lines]
    for i, line in enumerate(lines):
        mine, win = line.split(':')[1].split('|')
        right = len(set(mine.strip().split()) & set(win.strip().split()))
        print(i+1, right, copies[i])
        for j in range(i + 1, i + right + 1):
            copies[j] += copies[i]
    print(sum(copies))


