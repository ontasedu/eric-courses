'''
Verify that a line of input contains a valid IP address.
'''

import re

good_ip = '127.0.0.1'
bad_ip = '666.0.0.1'

octet = r'([0-9]{1,3})' # octet is grouped, for parsing
separator = r'\.'

ip_reg_exp = r'^' + octet + separator + octet + separator + octet + separator + octet + r'$'
compiled_ip_reg_exp = re.compile(ip_reg_exp)
match_object = compiled_ip_reg_exp.search(good_ip)

if all( ( 0 <= int(group) <= 255 for group in match_object.groups()  ) ):
        print match_object.group(0), 'valid ip address'

match_object = compiled_ip_reg_exp.search(bad_ip)
if all( ( 0 <= int(group) <= 255 for group in match_object.groups()  ) ):
        print match_object.group(0), 'valid ip address'

#######################################
' solution without regular expressions '
good_ip = '127.0.0.1'
octets = good_ip.split('.')

if len(octets) != 4:
    print 'invalid ip address'
    
if all( ( 0 <= int(octet) <= 255 for octet in octets ) ):
    print good_ip, 'valid ip address'






    







        

