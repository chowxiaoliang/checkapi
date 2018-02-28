package interfacetest.classtest;

import interfacetest.Say;

public class SayAnotherthing extends Say {
    @Override
    public void say() {
        System.out.println("two");
    }
}
