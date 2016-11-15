package com.hongcheng;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MyClient {
	public static void main(String[] args) {
		// 1.创建ioservice
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		// 2.注册过滤器
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));

		// 3注册iohandler,到ioserivce
		connector.setHandler(new MyClinetIoHandler("你好！\r\n大家好!"));
		// 4.绑定套接字,建立连接
		connector.connect(new InetSocketAddress("localhost", 1000));
	}
}
