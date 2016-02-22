'''
Have 2 lists of numbers, the lists being the same length.
Indicate if all the numbers in the first list are less than their corresponding number, in the second  list.
'''
from itertools import izip

nos1 = [1, 7, 11, 42]
nos2 = [10, 20, 30, 50]

if all(  no1 < no2 for no1, no2 in izip(nos1, nos2)  ):
    print 'all less'
