File.open("daySix/input.txt").readlines.each do |s|
  n=3
  while n < s.length do
    if s.slice(n-3, 4).chars.to_a.uniq.size == 4
      break
    else
      n += 1
    end
  end
  puts n + 1
end
