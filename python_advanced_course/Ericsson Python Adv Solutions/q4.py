'''
Implement grep
    - searching for : in a file e.g. q4.py
'''

import re

with open('q4.py', 'rtU') as in_file: # U for correct cross-platform newline handling
    for line in in_file:
        line = line.rstrip('\n')
        if re.search(r':', line):
            print line
