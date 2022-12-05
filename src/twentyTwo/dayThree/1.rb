require 'set'

file = File.open("dayThree/input.txt")
acc = 0
file.readlines.each { |s|
  a = s.chomp.unpack("a#{s.chomp.length/2}a*")
  b = Set[*a[0].split("").map { |s|s.ord > 96 ? s.ord - 96 : s.ord - 38 }]
  c = Set[*a[1].split("").map { |s|s.ord > 96 ? s.ord - 96 : s.ord - 38  }]
  acc += b.intersection(c).to_a.sum
}
puts acc