package com.hyq.learning.sigton;

/**
 * @author dibulidohu
 * @classname Sigton
 * @date 2019/4/919:46
 * @description
 */
public class Sigton1 {

    private Sigton2 sigton2;

    private static Sigton1 sigton1 = null;

    private Sigton1(Sigton2 sigton2) {
        this.sigton2 = sigton2;
    }

    public static Sigton1 getInstance(){
        if (null == sigton1) {
            synchronized (Sigton1.class) {
                Sigton2 instance = Sigton2.getInstance();
                sigton1 = new Sigton1(instance);
            }
        }
        return sigton1;
    }
}
