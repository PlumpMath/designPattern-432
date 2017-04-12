package patterns.structural.decorator;

/**
 * 被修饰具体子类
 */
public class RectangleShape implements Shape {
    public void draw() {
        System.out.println("drawRectangle");
    }


}
