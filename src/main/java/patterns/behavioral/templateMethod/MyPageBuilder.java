package patterns.behavioral.templateMethod;

/**
 * Created by Administrator on 2017/3/10.
 */
public class MyPageBuilder extends AbstractPageBuilder {

    protected void appendMeta(StringBuffer stringBuffer) {
        stringBuffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
    }

    @Override
    protected void appendHead(StringBuffer stringBuffer) {
        stringBuffer.append("<head><title>你好</title></head>");
    }

    @Override
    protected void appendBody(StringBuffer stringBuffer) {
        stringBuffer.append("<body><h1>你好,世界！</h1></body>");
    }
}
