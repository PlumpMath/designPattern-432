package patterns.structural.decorator;

/**
 * Created by Administrator on 2017/3/9.
 */
public class BlueShapeDecorator extends ShapeDecorator {
    public BlueShapeDecorator(Shape shape) {
        super(shape);
    }

    public void draw(){
        System.out.println("first step is to draw blue background");
        shape.draw();
    }
}
