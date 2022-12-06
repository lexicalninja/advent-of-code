File.open("daySix/input.txt").readlines.each do |s|
  n=13
  while n < s.length do
    if s.slice(n-13, 14).chars.to_a.uniq.size == 14
      break
    else
      n += 1
    end
  end
  puts n + 1
end