package com.hongcheng;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClinetIoHandler extends IoHandlerAdapter {
	private final static Logger logger=LoggerFactory.getLogger(MyClinetIoHandler.class);
	private final String values;
	public MyClinetIoHandler(String values){
	this.values=values;
	}
	@Override
	public void sessionOpened(IoSession session) throws Exception {
	// TODO Auto-generated method stub
	session.write(values);
	}
}
