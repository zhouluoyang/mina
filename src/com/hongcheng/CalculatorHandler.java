package com.hongcheng;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//适配器模式
	//1.类适配器：适配器模式应该重要的是写一个适配器，失配客户端和现有类。在当前环境下就没必要使用适配器（适配器继承现有，实现目标，自己写完目标类中未实现的方法）
	//2.对象适配器：组合现有类，实现目标类
	//3.缺省适配器：本类（平庸化设计）
//类CalculatorHandler继承IoHandlerAdapter实现IoHandler以适应需求
public class CalculatorHandler extends IoHandlerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorHandler.class);
	private ScriptEngine jsEngine = null;

	public CalculatorHandler() {
		ScriptEngineManager sfm = new ScriptEngineManager();
		jsEngine = sfm.getEngineByName("JavaScript");
		if (jsEngine == null) {
			throw new RuntimeException("找不到JavaScript引擎。");
		}
	}

	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		LOGGER.warn(cause.getMessage(), cause);
	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		String expression = message.toString();
		if ("quit".equalsIgnoreCase(expression.trim())) {
			session.close(true);
			return;
		}
		try {
			Object result = jsEngine.eval(expression);
			session.write(result.toString());
		} catch (ScriptException e) {
			LOGGER.warn(e.getMessage(), e);
			session.write("Wrongexpression,tryagain.");
		}
	}
}
