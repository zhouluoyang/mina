package com.hongcheng;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServer {
	private static final int PORT=10010; 
	private static final Logger LOGGER=LoggerFactory.getLogger(MyServer.class);
	public static void main(String[] args) throws IOException {
		// 1.编写ioservice
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger",new LoggingFilter());
		acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset .forName("UTF-8"))));
		acceptor.setHandler(new CalculatorHandler());
		acceptor.bind(new InetSocketAddress(PORT));
		LOGGER.info("计算器服务已启动，端口是"+PORT);
	}
}
