package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/4/12.
 */
public class TCPServer {

    // 缓冲区大小
    private static final int BufferSize = 1024;
    // 超时时间，单位毫秒
    private static final int TimeOut = 3000;
    // 本地监听端口
    private static final int ListenPort = 1978;

    public static void main(String[] args) throws IOException {

        //创建选择器
        Selector selector = Selector.open();
        //打开监听信道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //与本地端口绑定
        serverSocketChannel.socket().bind(new InetSocketAddress(ListenPort));
        //信道非阻塞
        serverSocketChannel.configureBlocking(false);
        //监听信道绑定选择器(非阻塞信道才能注册),并在注册过程中指出该信道可以进行Accept操作(OP_ACCEPT事件)
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //协议实现类
        TCPProtocol protocol = new TCPProtocolImpl(BufferSize);

        //循环监听
        while(true){
            //等待某信道就绪
            if (selector.select(TimeOut) == 0){
                //监听注册通道，当有住的IO操作机型是，函数返回，并将对应的SelectKey加入到set中
                System.out.println("waiting for OP_ACCEPT....");
                continue;
            }
            //取得selectedKeys 中包含了每个准备好某一IO操作的信道的SelectionKey
            //// Selected-key Iterator 代表了所有通过 select() 方法监测到可以进行 IO 操作的 channel
            Iterator<SelectionKey> keyIterable = selector.selectedKeys().iterator();
            while(keyIterable.hasNext()){
                SelectionKey key = keyIterable.next();
                try{
                    //三种事件 连接+读+写
                    if(key.isAcceptable()){
                        //有客户端连接时处理
                        protocol.handleAccept(key);
                    }
                    if (key.isReadable()){
                        //判断是否有数据发送过来
                        protocol.handleRead(key);
                    }
//                    if (key.isValid() && key.isWritable()){
//                        //判断是否有效及可以发送给客户端
//                        protocol.handleWrite(key);
//                    }
                }catch (IOException e){
                    System.out.println("event:" + key + " occur a io exception");
                   //出现异常也需要移除key
                    keyIterable.remove();
                    continue;
                }
                //处理完后 移除key
                keyIterable.remove();
            }
        }
    }

}
