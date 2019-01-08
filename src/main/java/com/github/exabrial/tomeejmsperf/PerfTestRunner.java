package com.github.exabrial.tomeejmsperf;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Singleton
public class PerfTestRunner {
	@Resource
	private ConnectionFactory factory;
	private AtomicLong counter = new AtomicLong();

	private Connection connection;

	@PostConstruct
	public void postConstruct() {
		try {
			connection = factory.createConnection();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	@Asynchronous
	public void go() {
		try {
			for (int i = 0; i < 24; i++) {
				Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
				Destination destination = session.createQueue("com.github.exabrial.tomeejmsperf");
				MessageProducer producer = session.createProducer(destination);
				TextMessage message = session.createTextMessage("yeee yee");
				producer.send(message);
				session.close();
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	@Schedule(second = "0/10", minute = "*", hour = "*")
	public void status() {
		System.out.println(counter.incrementAndGet());
	}

	public Connection getConnection() {
		return connection;
	}

	public void increment() {
		counter.incrementAndGet();
	}
}
