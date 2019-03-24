package check;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandle implements InvocationHandler {

    private Class target;

    public MyInvocationHandle(Class target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy object method is invoked!");
        return null;
    }

    public Object getProxyObject(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{target}, this);
    }
}
