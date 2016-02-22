'''
Get the user to input a list of numbers.
	Then output a menu for the user to select either the sum/average/max/min.
	Then execute the selected function :-
	- using nested if’s
	- using a dict
'''
from __future__ import division

def average(numbers):
    return sum(numbers) / len(numbers)

'get user  to input 4 numbers'
numbers = [ ]
for _ in xrange(4):
    number = int(raw_input('pls input number '))
    numbers.append(number)

action = raw_input('type sum/max/min/average ')

result = 0
if action == 'sum':
    result = sum(numbers)
elif action == 'average':
    result = average(numbers)
elif action == 'max':
    result = max(numbers)
elif action == 'min':
    result == min(numbers)

print result
    













    

