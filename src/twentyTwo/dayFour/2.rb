file = File.open("dayFour/input.txt")
acc = 0
file.readlines.each { |s|
  s.chomp.split(",").each_slice(2) { |s|
    a, b = s[0].split("-").map(&:to_i), s[1].split("-").map(&:to_i)
    if a.first <= b.last && b.first <= a.last
      acc += 1
    end
  }
}
puts acc
