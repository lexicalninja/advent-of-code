def score(elf, me)
  elfNormalized = elf.ord
  meNormalized = me.ord - 23
  (me.ord - 87) + case meNormalized - elfNormalized
           when 0
             3
           when 1, -2
             6
           else
             0
           end
end

file = File.open("dayTwo/input.txt")
points = 0
file.readlines.each { |s|
  points += score(*s.chomp.split(" "))
}
puts points

