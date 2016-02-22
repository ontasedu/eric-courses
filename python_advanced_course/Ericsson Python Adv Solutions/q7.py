'''
Read one file that has a country name on each line
and another that has the international dialing code on each line.
Use the data to produce a dict from the data.
Then have the user input a country and the program outputs its code.
''' 
from itertools import izip

countries_file = open('q7_countries.txt', 'rtU') # U for correct cross platform newline handling
codes_file = open('q7_codes.txt', 'rtU')

with countries_file, codes_file:
    codes =  dict( izip( ( country.rstrip('\n') for country in  countries_file ),
                                   ( code.rstrip('\n')     for code in  codes_file ) ) )

country = raw_input('pls input a country ')
print codes[country]



