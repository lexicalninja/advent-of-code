file = File.open("dayOne/input.txt")
file_data = file.read
elves = file_data.split(/\n{2,}/).map { |s| s.split("\n").map { |v|v.to_i } }.map { |a|a.reduce{|sum, num| sum+num} }
max = elves.max
top3 = elves.sort.last(3).sum
puts max, top3

#  .map {|x| x.lines.map{|z| z.chomp.to_i}.sum}.max(3).sum