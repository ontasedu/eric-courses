'''
Take a Unix/Linux password file (or a mock up, if on Windows)
	- output the login shells in order of popularity
'''

from collections import defaultdict

shells = defaultdict(int)

with open('q5_password.txt', 'rt') as in_file:
    for line in in_file:
        line = line.rstrip('\n')
        shell = line.split(':')[-1]
        shells[shell] += 1

print sorted( ( (frequency, shell) for shell, frequency in shells.iteritems() ), reverse=True )
