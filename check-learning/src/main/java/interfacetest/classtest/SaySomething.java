package interfacetest.classtest;

import interfacetest.Say;

public class SaySomething extends Say{
    @Override
    public void say() {
        System.out.println("message one");
    }
}
