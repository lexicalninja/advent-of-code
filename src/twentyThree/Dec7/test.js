"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var fs = require("fs");
var cardValuesMap = new Map([
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
function processInstructions() {
    var input = fs.readFileSync("input.txt", "utf8");
    var splitInput = input.split("\r\n");
    var total = 0;
    var hands = [];
    for (var _i = 0, splitInput_1 = splitInput; _i < splitInput_1.length; _i++) {
        var line = splitInput_1[_i];
        var cardsAndBid = line.split(" ");
        hands.push({ cards: cardsAndBid[0], bid: Number.parseInt(cardsAndBid[1]) });
    }
    hands.sort(sortHands);
    console.log(hands);
    for (var i = 0; i < hands.length; i++) {
        total += (hands[i].bid * (i + 1));
    }
    console.log(total);
}
function sortHands(a, b) {
    var aMap = new Map();
    var bMap = new Map();
    for (var i = 0; i < a.cards.length; i++) {
        var aChar = a.cards.charAt(i);
        if (aMap.has(aChar)) {
            aMap.set(aChar, aMap.get(aChar) + 1);
        }
        else {
            aMap.set(aChar, 1);
        }
        var bChar = b.cards.charAt(i);
        if (bMap.has(bChar)) {
            bMap.set(bChar, bMap.get(bChar) + 1);
        }
        else {
            bMap.set(bChar, 1);
        }
    }
    var highA = getHandValue(aMap);
    var highB = getHandValue(bMap);
    if (highA === highB) {
        for (var i = 0; i < a.cards.length; i++) {
            var aVal = cardValuesMap.get(a.cards.charAt(i)) || 0;
            var bVal = cardValuesMap.get(b.cards.charAt(i)) || 0;
            if (aVal - bVal !== 0) {
                return aVal - bVal;
            }
        }
    }
    return highA - highB;
}
function getHandValue(map) {
    var highValue = 0;
    var hasPair = false;
    var hasThree = false;
    for (var _i = 0, _a = map.values(); _i < _a.length; _i++) {
        var val = _a[_i];
        if (val === 3) {
            hasThree = true;
            if (hasPair) {
                highValue = 3.5;
            }
            else {
                highValue = 3;
            }
        }
        else if (val === 2) {
            if (hasThree) {
                highValue = 3.5;
            }
            else if (hasPair) {
                highValue = 2.5;
            }
            else {
                highValue = 2;
            }
            hasPair = true;
        }
        highValue = Math.max(highValue, val);
    }
    return highValue;
}
processInstructions();
