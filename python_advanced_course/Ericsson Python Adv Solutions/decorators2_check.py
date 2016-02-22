'''
Write a decorator that decorates functions that take a variable number of numeric values
and returns 4 numeric values.
'''

from __future__ import division

def check(func):
    def ifunc(*numbers):
        if not all( isinstance(number, (int, float)) for number in numbers):
            raise TypeError('all parameters must be numeric')
        
        return_values = func(*numbers)
        
        if len(return_values) != 4:
            raise ArithmeticError('must return 4 values')
        if not all( isinstance(number, (int, float)) for number in return_values):
            raise TypeError('all return values must be numeric')
        
        return return_values
    return ifunc

@check
def info(*numbers):
    return sum(numbers), sum(numbers)/len(numbers), max(numbers), min(numbers)

assert info(1, 7, 11, 42) == ( 61, 15.25, 42, 1)

try:
    info(90, 'joe')
except TypeError:
    print 'bad info test passes'

@check
def bad_info(*numbers):
    return 1, 2, 3

try:
    bad_info(1, 7, 11, 42)
except ArithmeticError:
    print 'bad_info  call test passes'

print 'all tests pass'
