package com.example.test.controller.homework.week09.seven;


import com.example.test.controller.homework.week09.seven.service.Bank1Service;

@SpringBootApplication
@Slf4j
public class BankTransferClientApplication {

    @DubboReference(version = "1.0.0") //, url = "dubbo://127.0.0.1:12345")
    private Bank1Service bank1Service;

    public static void main(String[] args) {
        SpringApplication.run(BankTransferClientApplication.class, args);
    }


    @Bean
    public ApplicationRunner runner() {
        return args -> {
            int customerid = 10000;
            int amount = 500;
            String tid = UUID.randomUUID().toString();
            bank1Service.transfer(tid,customerid,amount);
            log.info("customerid {} transfer amount {} ...",customerid,amount);
        };
    }
}
