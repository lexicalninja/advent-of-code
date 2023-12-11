import * as fs from 'fs';

const cardValuesMap = new Map<string, number>([
  ["2", 2],
  ["3", 3],
  ["4", 4],
  ["5", 5],
  ["6", 6],
  ["7", 7],
  ["8", 8],
  ["9", 9],
  ["T", 10],
  ["J", 11],
  ["Q", 12],
  ["K", 13],
  ["A", 14],
]);

interface Hand {
  cards: String,
  bid: number
}

function processInstructions() {
  const input = fs.readFileSync("input.txt", "utf8");
  const splitInput: string[] = input.split("\r\n");
  let total = 0;
  const hands: Hand[] = [];
  for(const line of splitInput) {
    const cardsAndBid = line.split(" ");
    hands.push({cards: cardsAndBid[0], bid: Number.parseInt(cardsAndBid[1])});
  }
  hands.sort(sortHands);
  console.log(hands);
  for(let i = 0; i < hands.length; i++) {
    total += (hands[i].bid * (i + 1))
  }
  console.log(total);
}

function sortHands(a: Hand, b: Hand): number {
  const aMap = new Map();
  const bMap = new Map();
  for(let i = 0; i < a.cards.length; i++) {
    const aChar = a.cards.charAt(i)
    if(aMap.has(aChar)) {
      aMap.set(aChar, aMap.get(aChar) + 1);
    } else {
      aMap.set(aChar, 1);
    }
    const bChar = b.cards.charAt(i);
    if(bMap.has(bChar)) {
      bMap.set(bChar, bMap.get(bChar) + 1);
    } else {
      bMap.set(bChar, 1);
    }
  }

  const highA = getHandValue(aMap);
  const highB = getHandValue(bMap);
  if(highA === highB) {
    for(let i = 0; i < a.cards.length; i++) {
      const aVal = cardValuesMap.get(a.cards.charAt(i)) || 0;
      const bVal = cardValuesMap.get(b.cards.charAt(i)) || 0;
      if(aVal - bVal !== 0) {
        return aVal - bVal;
      }
    }
  }
  return highA - highB;
}

function getHandValue(map: Map<string, number>) {
  let highValue = 0;
  let hasPair = false;
  let hasThree = false;
  for(const val of map.values()) {
    if(val === 3) {
      hasThree = true;
      if(hasPair) {
        highValue = 3.5
      } else {
        highValue = 3;
      }
    } else if(val === 2) {
      if(hasThree) {
        highValue = 3.5;
      } else if(hasPair) {
        highValue = 2.5
      } else {
        highValue = 2;
      }
      hasPair = true;
    }
    highValue = Math.max(highValue, val);
  }
  return highValue;
}

processInstructions();