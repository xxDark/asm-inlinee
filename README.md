# asm-inline

At first I thought: *Oh, I can make an optimization transformer for Proguard*  
And then this happened.  
Example:

```Java
public class Test {

  public static void main(String[] args) {
    AsmBlock.inline(b -> {
      b.getstatic(System.class, "out", PrintStream.class)
          .ldc("Hello, World!")
          .invokevirtual(PrintStream.class, "println",
              MethodType.methodType(Void.TYPE, String.class));
      // Note that RETURN will be added by javac.
    });
  }
}
  ```