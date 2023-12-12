def polynomialInterpolation(arr):
    if all(a == 0 for a in arr): return 0
    else: return arr[-1] + polynomialInterpolation([(j-i) for i, j in zip(arr[:-1], arr[1:])])

with open('input.txt', 'r') as file:
    lines = [[int(s) for s in l.split()] for l in file.readlines()]
print(sum([polynomialInterpolation(a) for a in lines]))





def connectsLeft(node):



class Node:
    def __init__(self,x,y,p,l = None,r = None,t = None,b = None):
        self.x = x
        self.y = y
        self.p = p
        self.l = initLeft(l)
        self.r = r
        self.t = t
        self.b = b

    def initLeft(node):
        return node if self.p in "J-7" and (node.l != None and node.l.p in "L-F") else None
