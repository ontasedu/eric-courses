from account_q1_q6 import Account, OverDrawnError

import unittest 

class AccountTest(unittest.TestCase):

    def setUp(self):
        self.account = Account('joe')

    def test_account_open(self):
        self.assertEqual(self.account.get_balance(), 0)        

    def test_deposit(self):
        self.account.deposit(90)
        self.assertEqual(self.account.get_balance(), 90)

    def test_overdrawn(self):
        self.failUnlessRaises(OverDrawnError,  self.account.withdraw, 100)

    def test_get_transaction_by_method(self):
        self.account.deposit(100)
        self.assertEqual(self.account.get_all_transactions()[0], 100)

    def test_get_transaction_by_operator(self):
        self.account.deposit(200)
        self.assertEqual(self.account[0], 200)

    def test_get_number_of_accounts(self):
        Account('sue')
        self.assertEqual(Account.get_num_acs(), 2)

    def tearDown(self):
        del self.account
        Account.num_acs = 0

if __name__ == '__main__':
    unittest.main()
    
    
