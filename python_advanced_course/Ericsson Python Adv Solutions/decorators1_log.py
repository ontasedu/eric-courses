'''
Write a decorator that decorates functions, that take 2 numeric parameters
and return 1 numeric value.
Have the decorator append a time stamp to a file, when the function gets called.
Also record the 2 parameters and the return value.
'''

def log(func):
    from time import ctime
    log_file = open('log_file_for_' + func.__name__ , 'at')
    def ifunc(x, y):
        log_file.write( ctime()  + '\n')
        log_file.write( 'parameters ' + str(x) + ' ' + str(y) + '\n')
        rv = func(x, y)
        log_file.write( 'return value ' + str(rv) + '\n')
        log_file.flush()
        return rv
    return ifunc

@log
def good(p1, p2):
    return p1 + p2

assert good(10, 20) == 30
assert good(1, 41) == 42
print 'good tests passes'

@log
def bad(p1, p2):
    return 'text'

try:
    bad(10, 20)
except TypeError:
    pass
print 'bad test passes'

print 'all tests pass'

# see also the logging module
                 
