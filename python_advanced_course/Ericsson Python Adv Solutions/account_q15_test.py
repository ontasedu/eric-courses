from account_q15 import Account, OverDrawnError

import unittest

class AccountTest(unittest.TestCase):

    def setUp(self):
        self.account = Account('joe')

    def test_account_open(self):
        self.assertEqual(self.account.balance, 0)        

    def test_deposit(self):
        self.account.deposit(90)
        self.assertEqual(self.account.balance, 90)

    def test_overdrawn(self):
        self.failUnlessRaises(OverDrawnError,  self.account.withdraw, 100)

    def test_get_transaction_by_method(self):
        self.account.deposit(100)
        self.assertEqual(self.account.get_all_transactions()[0], 100)

    def test_get_transaction_by_operator(self):
        self.account.deposit(200)
        self.assertEqual(self.account[0], 200)

    def tearDown(self):
        del self.account

if __name__ == '__main__':
    unittest.main()
    
    
