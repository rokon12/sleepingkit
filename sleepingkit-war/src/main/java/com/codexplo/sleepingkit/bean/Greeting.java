package com.codexplo.sleepingkit.bean;

import javax.faces.bean.ManagedBean;
/**
 * Created with IntelliJ IDEA.
 * Users: Bazlur Rahman Rokon
 * Date: 2/10/13
 * Time: 10:32 AM
 */

@ManagedBean
public class Greeting {
    private String greet= "Hey, Good morning!!";

    public String getGreet() {
        return greet;
    }

    public void setGreet(String greet) {
        this.greet = greet;
    }
}
