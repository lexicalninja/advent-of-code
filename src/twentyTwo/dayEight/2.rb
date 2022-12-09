require 'matrix'
require 'set'

arr1 = File.readlines("dayEight/input.txt").map { |a| a.chomp.split("").map(&:to_i) }
arr2 = Matrix.rows(arr1).transpose.to_a
best = 0
for i in 1..(arr1.length - 2)
  for j in 1..(arr1[i].length - 2)
    left, right, up, down = 0, 0, 0, 0
    h = arr1[i][j]
    for k in (j - 1).downto(0) do
      left += 1
      if arr1[i][k] >= h
        break
      end
    end
    for x in (i - 1).downto(0) do
      up += 1
      if arr2[j][x] >= h
        break
      end
    end
    for y in (j + 1)..(arr1[i].length - 1) do
      right += 1
      if arr1[i][y] >= h
        break
      end
    end
    for z in (i + 1)..(arr2[j].length - 1) do
      down += 1
      if arr2[j][z] >= h
        break
      end
    end
    view = left * right * up * down
    if view > best
      best = view
    end
  end
end
puts best