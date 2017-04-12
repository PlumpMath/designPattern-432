package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/11.
 */
public class NioTest {


    public static void main(String[] args) throws IOException {
        nioSlice();
    }

    public static void nioRead() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("H:\\group\\designPattern\\src\\main\\java\\nio\\desc.txt");
        FileChannel fileChannel = fileInputStream.getChannel();//获取通道
        //开启缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int i = fileChannel.read(byteBuffer);//返回的是长度
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nioWrite() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("H:\\group\\designPattern\\src\\main\\java\\nio\\desc.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();//开启通道
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//获取缓冲区
        byteBuffer.clear();
        byte message[] ="Some bytes".getBytes();
        for (int i=0; i<message.length; ++i) {
            byteBuffer.put( message[i] );
        }
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
    }

    public static void nioSlice(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);//默认为0
        for (int i = 0 ; i< byteBuffer.capacity() -2 ;i++){
            byteBuffer.put((byte) i);
        }
        //分片 创建子缓冲区  槽3-6
        byteBuffer.position(3);
        byteBuffer.limit(7);
        ByteBuffer slice = byteBuffer.slice();//子缓冲区
        //修改自缓冲区内容
        for (int i = 0;i < slice.capacity();i++){
            byte b = slice.get(i);
            b *= 11;
            slice.put(i,b);
        }
        //返回原缓冲区
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        while(byteBuffer.remaining() > 0){
            System.out.println(byteBuffer.get());
        }
        byteBuffer.asReadOnlyBuffer();
        byteBuffer.put((byte)1);
        //java.nio.BufferOverflowException
    }

    public void nioSever(){
        try {
            //第一步 创建一个selector
            //用于注册对各种IO事件(当事件发生时.由该对象通知事件发生 也就是select())
            Selector selector = Selector.open();

            //第二步 打开一个ServerSocketChannel
            //监听的每一个端口都需要有一个 ServerSocketChannel (一个端口一个ServerSocketChannel 一对一的关系)
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//我们必须对每一个要使用的套接字通道调用这个方法，否则异步 I/O 就不能工作。
            //绑定到给定的端口 8080
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(8081);
            serverSocket.bind(inetSocketAddress);

            //第三步 选择键
            //注册事件到selector上
            //这里 监听 accept 事件 在新的连接建立时所发生的事件 注意：适用于 ServerSocketChannel 的唯一事件类型
            // SelectionKey 代表这个通道在此 Selector 上的这个注册。
            // 当某个 Selector 通知您某个传入事件时，它是通过提供对应于该事件的 SelectionKey 来进行的。
            // SelectionKey 还可以用于取消通道的注册
            SelectionKey key = serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

            //第四步 内部循环
            //这个方法会阻塞，直到至少有一个已注册的事件发生 这也是nio被称为异步阻塞的原因
            int num = selector.select();
            Set selectKeys = selector.selectedKeys();
            Iterator iterator = selectKeys.iterator();
            //通过迭代 SelectionKeys 并依次处理每个 SelectionKey 来处理事件
            while(iterator.hasNext()){
                //对于每一个 SelectionKey，您必须确定发生的是什么 I/O 事件，以及这个事件影响哪些 I/O 对象
                SelectionKey selectionKey = (SelectionKey)iterator.next();
                //第五步 监听新连接
                if ((key.readyOps() & SelectionKey.OP_ACCEPT)
                        == SelectionKey.OP_ACCEPT) {

                    System.out.println("一个新的连接");
                    // Accept the new connection
                    // ...
                }
                //第六步 接收新的连接
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                //将新连接的 SocketChannel 配置为非阻塞的
                socketChannel.configureBlocking( false );
                //接受这个连接的目的是为了读取来自套接字的数据
                //将SocketChannel 注册到 Selector上
                //SocketChannel 注册用于 读取 而不是 接受 新连接
                SelectionKey newKey = socketChannel.register( selector, SelectionKey.OP_READ );
            }





        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
