'''
Create a list of 1000000 random numbers using a list.
	Using a for loop, calculate the total.
	Do the same using the array module.
	Print the space allocated for the list and the array.
	Then time each for loop using the time module.
'''

import array
import random
import sys
import time

random_numbers = [ random.randint(1, 100) for _ in xrange(1000000) ]
start = time.clock()
total = 0
for number in random_numbers:
    total += number
end = time.clock()

print 'list'
print 'time', end - start
print 'size', sys.getsizeof(random_numbers)

#####################################################

random_numbers = array.array( 'i', random_numbers )
start = time.clock()
total = 0
for number in random_numbers:
    total += number
end = time.clock()

print 'array'
print 'time', end - start
print 'size', sys.getsizeof(random_numbers)




