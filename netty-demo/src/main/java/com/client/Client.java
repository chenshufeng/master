package com.client;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ Auther: chenshf
 * @ Date: 18/8/24 23:09
 * @ Description:
 */
public class Client {

    public static void main(String[] args) {
        ClientBootstrap bootstrap = new ClientBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        bootstrap.setFactory(new NioClientSocketChannelFactory(boss,worker));

        bootstrap.setPipelineFactory(() -> {
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("encoder",new StringEncoder());
            pipeline.addLast("decoder",new StringDecoder());
            pipeline.addLast("hiHandler",new HiHandler());
            return pipeline;
        });

        ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8888));

        Channel channel = future.getChannel();
        System.out.println("client 启动");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入内容:");
        while (true){
            channel.write(scanner.next());
        }


    }
}
