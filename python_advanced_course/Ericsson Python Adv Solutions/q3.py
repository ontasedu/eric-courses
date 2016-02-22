"""Implement the Linux command wc.
		- with 1 hard-wired filename e.g. q3.py
		- with a list of files on the command line
		- that takes options """

'with 1 hard-wired filename e.g. q3.py'

num_chars = 0
num_words = 0
num_lines = 0

with open('q3.py', 'rt') as in_file:
    for line in in_file:
        num_chars += len( line )
        num_words += len( line.split() )
        num_lines += 1

print num_chars
print num_words
print num_lines


############################################
'with a list of files on the command line'

from collections import defaultdict
import fileinput

wc_dict = defaultdict( lambda : defaultdict(int) )

for line in fileinput.input():
    num_chars = len( line )
    num_words = len( line.split() )
    num_lines += 1

    filename = fileinput.filename()
    wc_dict[filename]['chars'] += num_chars
    wc_dict[filename]['words'] += num_words
    wc_dict[filename]['lines'] += 1

print wc_dict

############################################
##
##''' that takes options '''
##
##from collections import defaultdict
##import fileinput
##from getopt import getopt
##import sys
##
##opts, filenames = getopt(sys.argv[1 : ], 'lwc')
##
##wc_dict = defaultdict( lambda : defaultdict(int) )
##
##for line in fileinput.input(filenames):
##    num_chars = len( line )
##    num_words = len( line.split() )
##    num_lines += 1
##
##    filename = fileinput.filename()
##    wc_dict[filename]['chars'] += num_chars
##    wc_dict[filename]['words'] += num_words
##    wc_dict[filename]['lines'] += 1
##
##options = [option for option, value in opts]
##if '-c' not in options:
##    print wc_dict[filename]['chars']  
##if '-w' not in options:
##    print wc_dict[filename]['words']
##if '-l' not in options:
##    print wc_dict[filename]['lines'] 

