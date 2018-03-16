package jvmhook;

public class HookTestOne {

    public void close(){
        System.out.println("close system now");
        System.exit(1);
    }
    public static void main(String[] args){

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("system is closed completly now");
        }));

        new HookTestOne().close();

    }
}
