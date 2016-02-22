from account_q10 import Account

import unittest

class AccountTest(unittest.TestCase):

    def setUp(self):
        self.account = Account('joe')

    def test_account_open(self):
        self.assertEqual(self.account.get_balance(), 0)        

    def test_deposit(self):
        self.account.deposit(90)
        self.assertEqual(self.account.get_balance(), 90)

    def test_iteration(self):
        self.account.deposit(90)
        self.account.deposit(10)
        
        self.iter = iter(self.account)
        self.assertEqual(self.iter.next(), 90)

        self.assertEqual( list(self.account), [90, 10])
        
    def tearDown(self):
        del self.account

if __name__ == '__main__':
    unittest.main()
    
    
