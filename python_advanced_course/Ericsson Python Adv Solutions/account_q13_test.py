from account_q7_q8 import Paccount, Account

import unittest 

class AccountTest(unittest.TestCase):
    @staticmethod
    def by_name(self, other):
        return cmp(self.name, other.name)

    def setUp(self):
        pass

    def test_sort_comparators(self):
        account1 = Account('dave')
        account1.deposit(100)
        account2 = Account('alan')
        account3 = Paccount('zak', 50)
        account4 = Paccount('sue', 60)
        
        all_accounts = [account1, account2, account3, account4]
        
        self.assertEqual( list(sorted(all_accounts)) , [ account2, account3, account4, account1] )
        self.assertEqual( list(sorted(all_accounts, cmp=AccountTest.by_name) ) ,
                                   [ account2, account1, account4, account3] )
                           
    def tearDown(self):
        pass

if __name__ == '__main__':
    unittest.main()
    
    
