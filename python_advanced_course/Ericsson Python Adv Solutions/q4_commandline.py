'''
Implement grep
    - with the pattern and filename(s) on the command line
'''

import re
import sys

with open(sys.argv[2], 'rt') as in_file:
    for line in in_file:
        line = line.rstrip('\n')
        if re.search(sys.argv[1], line):
            print line
