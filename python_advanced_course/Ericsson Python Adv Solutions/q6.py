'''
From a Python script execute a program
	- print the exit status of the script
	- capture standard output into your script and print it from there
	- for each of the above use the os module and also the subprocess module
'''

import os

exit_status = os.system('dir /p') # Windows dir command
print exit_status

import subprocess
import sys

print subprocess.call([sys.executable, 'hello.py' ])

