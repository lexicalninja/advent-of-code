def polynomialInterpolation(arr):
    if all(a == 0 for a in arr): return 0
    else: return arr[-1] + polynomialInterpolation([(j-i) for i, j in zip(arr[:-1], arr[1:])])

with open('input.txt', 'r') as file:
    lines = [[int(s) for s in l.split()] for l in file.readlines()]
print(sum([polynomialInterpolation(a) for a in lines]))





