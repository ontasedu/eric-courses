'''
Write a decorator that decorates functions, that takes 2 numeric parameters
and returns 1 numeric value.
'''

def check(func):
    def ifunc(x, y):
        if not( isinstance(x, (int, float)) and isinstance(y, (int, float)) ):
            raise TypeError('both parameters must be numeric')
        rv = func(x, y)
        if  not( isinstance(rv, (int, float) ) ):
            raise TypeError('return value must be numeric')
        return rv
    return ifunc

@check
def good(p1, p2):
    return p1 + p2

assert good(10, 20) == 30
print 'good test passes'

@check
def bad(p1, p2):
    return 'text'

try:
    bad(10, 20)
except TypeError:
    pass
print 'bad test passes'

print 'all tests pass'
                 
