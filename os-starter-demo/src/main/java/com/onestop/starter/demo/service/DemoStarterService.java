package com.onestop.starter.demo.service;

/**
 * 随便定义的service
 */
public class DemoStarterService {
    private String sayWhat;
    private String toWho;

    public DemoStarterService(String sayWhat, String toWho) {
        this.sayWhat = sayWhat;
        this.toWho = toWho;
    }

    public String say() {
        return this.sayWhat + "!  " + toWho;
    }
}
