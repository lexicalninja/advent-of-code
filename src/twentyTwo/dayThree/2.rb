require 'set'

file = File.open("dayThree/input.txt")
acc = 0
file.each_slice(3) { |lines|
  a = Set[*lines[0].chomp.split("").map { |s|s.ord > 96 ? s.ord - 96 : s.ord - 38 }]
  b = Set[*lines[1].chomp.split("").map { |s|s.ord > 96 ? s.ord - 96 : s.ord - 38 }]
  c = Set[*lines[2].chomp.split("").map { |s|s.ord > 96 ? s.ord - 96 : s.ord - 38 }]
  acc += a.intersection(b).intersection(c).to_a.sum
}
puts acc