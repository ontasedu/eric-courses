'''
Take an input line, containing a USA-style date, and convert to a UK-style date.
'''

import re

usa_date = '12:31:1999'    # party like it's .....

month = r'([0-9][0-9])'
day = r'([0-9][0-9])'
year = r'([0-9][0-9][0-9][0-9])'


match_object = re.search('^' + month + ':' + day + ':' + year + '$', usa_date)                                           

print match_object.group(2) + ':' + match_object.group(1) + ':' + match_object.group(3)

###############################
''' without regular expressions '''

month, day, year = usa_date.split(':')
print day + ':' + month + ':' + year
