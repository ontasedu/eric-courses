'''
Write a meta-class that verifies that a class has an __init__ method  and a docstring for the class.
Raise a BadClassError if either are not present.
'''
class BadClassError(Exception):
    pass

class ClassChecker(type):
    def __new__(cls, name, bases, attrs):
        if '__init__' not in attrs or '__docstring__' not in attrs:
            raise BadClassError('class must have __init__ and documentation')
        else:
            return type.__new__(cls, name, bases, attrs)

class Good(object):
    __metaclass__ = ClassChecker
    """ a good class """
    def __init__(self):
        pass

class Bad1(object):
    __metaclass__ = ClassChecker
    """ a partially bad class"""
    pass

class Bad2(object):
    __metaclass__ = ClassChecker
    def __init__(self):
        pass

