require 'matrix'
require 'set'
# i overwrote my solution with two :(
#


# def slide(row)
#   count = 0
#   row.each_with_index do |v, i|
#     if (i != 0 && i != (row.length -1)) && (v > row.slice(0, i).max || v > row.slice(i, (row.length -1)))
#       count += 1
#     end
#   end
#   count
# end

arr1 = File.readlines("dayEight/input.txt").map { |a|a.chomp.split("").map(&:to_i) }
arr2  = Matrix.rows(arr1).transpose.to_a
best = 0
puts arr2.to_s
for i in 1..(arr1.length-2)
  for j in 1..(arr1[i].length-2)
    left, right, up, down = 0,0,0,0
    h = arr1[i][j]
    puts h
    for k in (j-1).downto(0) do
      if arr1[i][k] < h
        left += 1
      elsif arr1[i][k] >= h
        left += 1
        break
      else
        break
      end
    end
    for x in (i-1).downto(0) do
      if arr2[j][x] < h
        up += 1
      elsif arr2[j][x] >= h
        up += 1
        break
      else
        break
      end
    end
    for y in (j+1)..(arr1[i].length-1) do
      right += 1

    end
    puts "left: #{left}"
    puts "up: #{up}"
  end

end

