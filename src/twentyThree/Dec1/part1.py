
import re

with open('input.txt', 'r') as file:
    lines = file.readlines()

acc = 0
for line in lines:
    nums = re.findall('\d', line)
    print(nums)
    acc += int(nums[0]) * 10
    acc += int(nums[-1])

print(acc)