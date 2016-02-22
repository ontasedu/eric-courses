'''
Write a decorator that decorates functions that take a variable number of numeric values
and returns 4 numeric values.
As before, append a time stamp each time the function is called
and the input and output parameters.
'''

from __future__ import division
from time import ctime

def log(func):
    log_file = open('log_file_' + func.__name__, 'at')
    def ifunc(*numbers):
        log_file.write( ctime()  + '\n')
        numbers_str = ( str(number) for number in numbers)
        log_file.write( 'parameters ' + ','.join(numbers_str) + '\n')
        
        return_values = func(*numbers)
        
        numbers_str = ( str(number) for number in return_values)       
        log_file.write( 'return values ' + ','.join(numbers_str) + '\n')
        log_file.flush()
        
        return return_values
    return ifunc

@log
def info(*numbers):
    return sum(numbers), sum(numbers)/len(numbers), max(numbers), min(numbers)

assert info(1, 7,  11, 42) == ( 61, 15.25, 42, 1)



print 'all tests pass'
