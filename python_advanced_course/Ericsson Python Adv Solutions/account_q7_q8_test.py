from account_q7_q8 import Paccount, Account

import unittest 

class AccountTest(unittest.TestCase):

    def setUp(self):
        self.account = Paccount('joe', 200)

    def test_account_open(self):
        self.assertEqual(self.account.get_balance(), 200)        

    def test_deposit(self):
        self.account.deposit(90)
        self.assertEqual(self.account.get_balance(), 290)

    def test_get_transaction_by_method(self):
        self.account.deposit(100)
        self.assertEqual(self.account.get_all_transactions()[0], 200)

    def test_get_transaction_by_operator(self):
        self.account.deposit(10)
        self.assertEqual(self.account[0], 200 )

    def test_get_position(self):
        account2 = Paccount('sue', 100)
        all_accounts = [self.account, account2]
        position = 0
        for account in all_accounts:
            position += account.get_balance()
        self.assertEqual(position, 300)
        
    def tearDown(self):
        del self.account

if __name__ == '__main__':
    unittest.main()
    
    
