package check;

public class ProxyTest {

    public static void main(String[] args) {
        MyInvocationHandle myInvocationHandle = new MyInvocationHandle(Shape.class);
        Shape shape = (Shape) myInvocationHandle.getProxyObject();
        shape.draw();
    }
}
