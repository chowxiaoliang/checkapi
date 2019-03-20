package check;

public class TestThree {

   private static String name = "zhouliang";
   private static final int anIn = 123;

   public TestThree(){
      name = "a";
   }

   public static void main(String[] args) {
      TestThree testThree = new TestThree();
      System.out.println("name=>" + name);
   }

}
