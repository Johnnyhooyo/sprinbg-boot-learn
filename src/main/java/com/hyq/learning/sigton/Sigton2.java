package com.hyq.learning.sigton;

/**
 * @author dibulidohu
 * @classname Sigton2
 * @date 2019/4/919:49
 * @description
 */
public class Sigton2 {

    private Sigton1 sigton1;
    private static Sigton2 sigton2;

    private Sigton2(Sigton1 sigton1) {
        this.sigton1 = sigton1;
    }

    public static Sigton2 getInstance(){
        if (null == sigton2) {
            synchronized (Sigton2.class) {
                Sigton1 instance = Sigton1.getInstance();
                sigton2 = new Sigton2(instance);
            }
        }
        return sigton2;
    }
}
