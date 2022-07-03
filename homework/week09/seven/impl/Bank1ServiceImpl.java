package com.example.test.controller.homework.week09.seven.impl;

@DubboService(version = "1.0.0", tag = "red", weight = 100)
public class Bank1ServiceImpl implements Bank1Service {

    @Autowired
    BankAccountService bankAccountService;

    @Override
    public Boolean transfer(String tid,int customerId, int amount) {
        this.bankAccountService.subtractAccountBalance(tid,customerId,amount);
        return true;
    }
}
