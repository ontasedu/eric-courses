from account_q1_q6 import Account

class Paccount(Account):
    def __init__(self, name, amount):
        Account.__init__(self, name)
        self.deposit(amount)
