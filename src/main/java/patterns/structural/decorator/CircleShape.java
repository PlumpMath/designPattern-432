package patterns.structural.decorator;

/**
 * 被修饰具体子类
 */
public class CircleShape implements Shape {
    public void draw() {
        System.out.println("drawCircle");
    }
}
