package patterns.structural.decorator;

/**
 * 修饰类
 */
public abstract class ShapeDecorator implements Shape{

    public Shape shape;

    public ShapeDecorator(Shape shape){
        this.shape = shape;
    }

    public void draw() {
        shape.draw();
    }
}
