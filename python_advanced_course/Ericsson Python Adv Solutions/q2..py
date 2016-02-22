'''
Implement a simulation of the national lottery.
'''

import random

numbers = set()

while len(numbers) != 6:
    numbers.add( random.randint(1, 50) )

print sorted(numbers)

