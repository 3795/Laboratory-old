package cn.ntshare.laboratory.io.nioDemo.server;

import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/11/26
 */
public class ChatServer {

    private static final int DEFAULT_PORT = 8888;

    private static final String QUIT = "quit";

    private static final int BUFFER = 1024;

    private ServerSocketChannel server;
    private Selector selector;
    private ByteBuffer rBuffer = ByteBuffer.allocate(BUFFER);
    private ByteBuffer wBuffer = ByteBuffer.allocate(BUFFER);
    private Charset charset = Charset.forName("UTF-8");
    private int port;
}
