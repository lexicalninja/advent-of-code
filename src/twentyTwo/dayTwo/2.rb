
file = File.open("dayTwo/input.txt")
points = 0
lookup = [[3,1,2],[1,2,3],[2,3,1]]
file.readlines.each { |s|
  a = s.chomp.split(" ")
  points += lookup[a[0].ord - 65][a[1].ord - 88]
  points += case a[1]
            when "Y"
              3
            when "Z"
              6
            else
              0
            end
}
puts points