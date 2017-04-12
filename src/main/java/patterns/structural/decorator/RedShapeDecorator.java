package patterns.structural.decorator;

/**
 * Created by Administrator on 2017/3/9.
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    public void draw(){
        System.out.println("first step is to draw red background");
        shape.draw();
    }

}
