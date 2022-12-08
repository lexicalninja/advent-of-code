
class Node
  attr_accessor :dirname, :parent, :children, :size, :total_size

  def initialize(dirname, parent = nil, children = [], size = 0)
    @dirname = dirname
    @parent = parent
    @children = children
    @size = size
    @total_size = 0
  end

  def ease
    @total_size = size + children&.map { |n|n.ease; n.total_size }.sum
  end

  def to_s
    "{dirName: #{dirname}, parent: #{@parent&.dirname}, size: #{size}, children: #{children.to_s}"
  end
end

nodes = Hash.new
commands = File.readlines("daySeven/input.txt")
current_node = nil
first_node = nil
commands.each do |l|
  case l
  when /^\$ cd \.\./
    current_node = current_node&.parent
  when /^\$ cd \//
      nodes[l.split(" ").last] = Node.new(dirname = l.split(" ").last)
      first_node = nodes[l.split(" ").last]
      current_node = first_node
  when /^\$ cd [a-z]+/
    name = current_node&.dirname +  l.split(" ").last + "/"
    current_node = nodes[name]
  when /^dir [a-z]+/
    name = current_node&.dirname + l.split(" ").last + "/"
    nodes[name] = Node.new(name, current_node)
    current_node&.children&.push(nodes[name])
  when /^[0-9]+/
    current_node&.size += l.split(" ").first.to_i
  else
    # "listing files #{l}"
  end
end

first_node&.ease
puts first_node.to_s
total = 0
amounts = []
nodes.each { |(k,v)| if v.total_size <= 100000; total += v.total_size; amounts.push(v.total_size); end  }
puts total

available = 70000000 - first_node.total_size
target = 30000000
smallest = Float::INFINITY
nodes.each do |_,v|
  if v.total_size + available > target && v.total_size < smallest
    smallest = v.total_size
  end
end
puts smallest