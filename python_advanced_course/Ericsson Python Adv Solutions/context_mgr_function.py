'''
Write a context manager that persists (use either pickle/JSON) an object(specified by the user) to a file (chosen by the context manager).
Have 2 solutions :-
	- using a class
	- using a function
'''
from contextlib import contextmanager
import pickle

@contextmanager
def auto_save(obj):
    save_file = open('auto_save2', 'wb')
    yield 'auto_save2'
    with save_file:
        pickle.dump(obj, save_file)

codes = { }

with auto_save(codes) as save_file:
    codes['ire'] = 353
    codes['uk'] = 44
    codes['usa'] = 1

del codes

with open(save_file, 'rb') as in_file:
    codes = pickle.load(in_file)

print codes

    

