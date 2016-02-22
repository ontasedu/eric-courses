from account_q9 import Account

import unittest

class AccountTest(unittest.TestCase):

    def setUp(self):
        self.account = Account('joe')

    def test_account_open(self):
        self.assertEqual(self.account.get_balance(), 0)        

    def test_deposit(self):
        self.account.deposit(90)
        self.assertEqual(self.account.get_balance(), 90)

    def test_reverse_iteration(self):
        self.account.deposit(90)
        self.account.deposit(10)
        
        self.reverse_iter = reversed(self.account)
        self.assertEqual(self.reverse_iter.next(), 10)

        self.assertEqual( list(reversed(self.account)), [10, 90] )
        
    def tearDown(self):
        del self.account

if __name__ == '__main__':
    unittest.main()
    
    
