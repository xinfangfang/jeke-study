package com.example.test.controller.homework.week09.seven;

@SpringBootApplication(scanBasePackages = {"com.dhb.bank.transfer.orm.dao", "com.dhb.bank.transfer.bank1"})
@MapperScan(basePackages = {"com.dhb.bank.transfer.orm.mapper"})
public class DubboServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboServerApplication.class, args);
    }

}
