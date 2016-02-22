'''
Get the user to input a list of numbers, one number per line.
Then output a menu for the user to select either the sum/average/max/min.
Then execute the selected function :-
    - using nested if’s
    - using a dict
'''
from __future__ import division

def average(numbers):
    return sum(numbers) / len(numbers)

actions = dict(sum=sum,
               average=average,
               max=max,
               min=min)

# get user  to input 4 numbers
numbers = [ ]
for _ in xrange(4):
    number = int(raw_input('pls input number '))
    numbers.append(number)

action = raw_input('type sum/max/min/average ')

if action in actions:
    print actions[action](numbers)
else:
    print 'should type sum/max/min/average'


    
