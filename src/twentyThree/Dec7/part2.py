from collections import Counter

rank = {
    'FIVE_OAK': 7,
    'FOUR_OAK': 6,
    'FULL_HOUSE': 5,
    'THREE_OAK': 4,
    'TWO_PAIR': 3,
    'PAIR': 2,
    'HIGH_CARD': 1
}

straight = 'AKQT98765432J'

def isFiveOaK(numDiffCards, cardCounts):
    return numDiffCards == 1 and max(cardCounts) == 5
def isFourOaK(numDiffCards, cardCounts):
    return numDiffCards == 2 and max(cardCounts) == 4
def isFullHouse(numDiffCards, cardCounts):
    return numDiffCards == 2 and max(cardCounts) == 3
def isThreeOaK(numDiffCards, cardCounts):
    return numDiffCards == 3 and max(cardCounts) == 3
def isTwoPair(numDiffCards, cardCounts):
    return numDiffCards == 3 and max(cardCounts) == 2
def isPair(numDiffCards, cardCounts):
    return numDiffCards == 4 and max(cardCounts) == 2

def convertJokers(hand):
    if(hand == 'JJJJJ'): hand = "AAAAA"
    counter = Counter(hand.replace("J", ''))
    return hand.replace('J', ''.join(map(lambda x: x[0] * x[1], sorted(counter.items(), key=lambda item: (-item[1], straight.index(item[0])))))[0])

def getRank(hand):
    finalHand = convertJokers(hand)
    counter = Counter(finalHand)
    l = len(counter)
    c = counter.values()
    if isFiveOaK(l,c): return "FIVE_OAK"
    elif isFourOaK(l,c): return "FOUR_OAK"
    elif isFullHouse(l,c): return "FULL_HOUSE"
    elif isThreeOaK(l,c): return "THREE_OAK"
    elif isTwoPair(l,c): return "TWO_PAIR"
    elif isPair(l,c): return "PAIR"
    else: return "HIGH_CARD"

def getRankVal(hand):
    return rank[getRank(hand)]

points = 0
hands = []
with open('input.txt', 'r') as file:
    lines = file.readlines()
    for line in lines:
        line = line.split()
        hands.append((line[0], int(line[1])))
ranks = sorted(hands, key=lambda h: (-getRankVal(h[0]), [straight.index(c) for c in h[0]]), reverse=True)

for i in range(0, len(ranks)):
    points += (i+1) * ranks[i][1]

print(points)
