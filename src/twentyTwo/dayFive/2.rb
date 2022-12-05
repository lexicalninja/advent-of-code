stacks = [["P", "F", "M", "Q", "W", "G", "R", "T"], ["H", "F", "R"], ["P", "Z", "R", "V", "G", "H", "S", "D"], ["Q", "H", "P", "B", "F", "W", "G"], ["P", "S", "M", "J", "H"], ["M", "Z", "T", "H", "S", "R", "P", "L"], ["P", "T", "H", "N", "M", "L"], ["F", "D", "Q", "R"], ["D", "S", "C", "N", "L", "P", "H"]]

file = File.open("dayFive/input.txt")
file.readlines.each { |s|
  a = s.scan(/\d+/).map( &:to_i )
  stacks[a[2] -1].push(*stacks[a[1] -1].pop(a[0]))
}
puts stacks.map(&:pop).join
