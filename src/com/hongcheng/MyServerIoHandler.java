package com.hongcheng;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServerIoHandler extends IoHandlerAdapter {
	private final static Logger logs = LoggerFactory.getLogger(MyServerIoHandler.class);
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String str = message.toString();
		logs.info("the message received is[" + str + "]");
		System.out.println("the message received is[" + str + "]");
		if (str.endsWith("quit")) {
			session.close(true);
			return;
		}
	}
}
