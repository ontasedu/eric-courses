'''
Verify that a line of input contains a valid credit card number.
Allow flexibility with the separator between groups of 4 digits.
i.e. allow a dash/a space/or no separator.
'''
import re

card1 = '1234 5678 9012 3456'
card2 = '1234567812345678'
card3 = '12345678123456789'

four_digits = r'[0-9]{4}'
separator = r'[ -]?'

cc_reg_exp = (r'^' +
                       four_digits + separator +
                       four_digits + separator +
                       four_digits + separator +
                       four_digits +
                       r'$'
                      )

compiled_cc_reg_exp = re.compile(cc_reg_exp)

if compiled_cc_reg_exp.search(card1):
    print 'card1 ok'

if compiled_cc_reg_exp.search(card2):
    print 'card2 ok'

if compiled_cc_reg_exp.search(card3):
    print 'card3 ok'


    
