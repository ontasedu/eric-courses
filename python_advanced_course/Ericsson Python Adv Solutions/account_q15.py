class OverDrawnError(Exception):
    pass

class Account(object):
    num_acs = 0

    @staticmethod
    def get_num_acs():
        return Account.num_acs

    def __init__(self, name):
        self.name = name
        self._balance = 0
        self.trans = [ ]
        Account.num_acs += 1

    def deposit(self, amount):
        self._balance += amount
        self.trans.append(amount)

    def withdraw(self, amount):
        if amount > self._balance:
            raise OverDrawnError('insufficient funds')
        self._balance -= amount
        self.trans.append(-amount)

    @property
    def balance(self):
        return self._balance

    def get_all_transactions(self):
        return self.trans

    def __getitem__(self, index):
        return self.trans[index]

    def __iter__(self):
        return iter(self.trans)
    
    
