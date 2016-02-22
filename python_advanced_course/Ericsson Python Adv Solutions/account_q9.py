class OverDrawnError(Exception):
    pass

class Account(object):
    class ReverseAccountIterator(object):
        def __init__(self, trans):
            self.trans = trans
            self.offset = len(trans)

        def next(self):
            self.offset -= 1
            if self.offset == -1:
                raise StopIteration()
            return self.trans[self.offset]

        def __iter__(self):
            return self
   
    num_acs = 0

    @staticmethod
    def get_num_acs():
        return Account.num_acs

    def __init__(self, name):
        self.name = name
        self.balance = 0
        self.trans = [ ]
        Account.num_acs += 1

    def deposit(self, amount):
        self.balance += amount
        self.trans.append(amount)

    def withdraw(self, amount):
        if amount > self.balance:
            raise OverDrawnError('insufficient funds')
        self.balance -= amount
        self.trans.append(-amount)

    def get_balance(self):
        return self.balance

    def get_all_transactions(self):
        return self.trans

    def __getitem__(self, index):
        return self.trans[index]
    
    def __reversed__(self):
        return Account.ReverseAccountIterator(self.trans)





    
    
