'''
Write a context manager that persists (use either pickle/JSON) an object(specified by the user) to a file (chosen by the context manager).
Have 2 solutions :-
	- using a class
	- using a function
'''
import pickle

class AutoSave(object):
    def __init__(self, obj):
        self.obj = obj

    def __enter__(self):
        self.save_file = open('auto_save', 'wb')
        return 'auto_save'

    def __exit__(self, exc_type, exc_info, exc_trace):
        with self.save_file:
            pickle.dump(self.obj, self.save_file)

codes = { }

with AutoSave(codes) as save_file:
    codes['ire'] = 353
    codes['uk'] = 44
    codes['usa'] = 1

del codes

with open(save_file, 'rb') as in_file:
    codes = pickle.load(in_file)

print codes

    

