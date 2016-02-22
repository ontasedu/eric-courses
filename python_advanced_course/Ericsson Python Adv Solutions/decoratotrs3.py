'''
Write a parameterised decorator for functions that take a variable number of parameters.
The decorators parameters to be the expected type for each parameter.
e.g. @checktypes(int, int)
'''

from itertools import izip

def checktypes(*types):
    def idec(func):
        def ifunc(*var_args):
            if len(types) != len(var_args):
                raise Exception('incoreect number of arguments')
            if not all( isinstance(actual, expected) for  actual, expected in izip(var_args, types) ):
                raise TypeError('incorrect argument type')
            return func(*var_args)
        return ifunc
    return idec

@checktypes(int, int)
def add(x, y):
    return x + y

assert add(10, 20) == 30

try:
    assert add(10, 20.0) == 30.0
except TypeError:
    print 'bad add test  1 passes'

try:
    assert add(10, 20, 30)
except Exception:
    print 'bad add test 2 passes'
    
print 'all tests pass'
            
    

