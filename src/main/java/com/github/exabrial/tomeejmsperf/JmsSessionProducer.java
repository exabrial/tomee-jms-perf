package com.github.exabrial.tomeejmsperf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import javax.transaction.TransactionScoped;

@ApplicationScoped
public class JmsSessionProducer {
	@Resource
	private ConnectionFactory connectionFactory;
	private Connection connection;

	@PostConstruct
	void postConstruct() {
		try {
			connection = connectionFactory.createConnection();
			connection.start();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	@PreDestroy
	void preDestroy() {
		try {
			connection.close();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	@Produces
	@Default
	@TransactionScoped
	public Session createTranactionalSession() {
		try {
			return new SSession(connection.createSession(true, Session.AUTO_ACKNOWLEDGE));
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	public void disposeSession(@Disposes @Any Session session) {
		try {
			session.close();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	private static class SSession implements Session, Serializable {
		private static final long serialVersionUID = 1L;
		private transient Session session;

		public SSession(Session session) {
			this.session = session;
		}

		@Override
		public BytesMessage createBytesMessage() throws JMSException {
			return session.createBytesMessage();
		}

		@Override
		public MapMessage createMapMessage() throws JMSException {
			return session.createMapMessage();
		}

		@Override
		public Message createMessage() throws JMSException {
			return session.createMessage();
		}

		@Override
		public ObjectMessage createObjectMessage() throws JMSException {
			return session.createObjectMessage();
		}

		@Override
		public ObjectMessage createObjectMessage(Serializable object) throws JMSException {
			return session.createObjectMessage(object);
		}

		@Override
		public StreamMessage createStreamMessage() throws JMSException {
			return session.createStreamMessage();
		}

		@Override
		public TextMessage createTextMessage() throws JMSException {
			return session.createTextMessage();
		}

		@Override
		public TextMessage createTextMessage(String text) throws JMSException {
			return session.createTextMessage(text);
		}

		@Override
		public boolean getTransacted() throws JMSException {
			return session.getTransacted();
		}

		@Override
		public int getAcknowledgeMode() throws JMSException {
			return session.getAcknowledgeMode();
		}

		@Override
		public void commit() throws JMSException {
			session.commit();
		}

		@Override
		public void rollback() throws JMSException {
			session.rollback();
		}

		@Override
		public void close() throws JMSException {
			session.close();
		}

		@Override
		public void recover() throws JMSException {
			session.recover();
		}

		@Override
		public MessageListener getMessageListener() throws JMSException {
			return session.getMessageListener();
		}

		@Override
		public void setMessageListener(MessageListener listener) throws JMSException {
			session.setMessageListener(listener);
		}

		@Override
		public void run() {
			session.run();
		}

		@Override
		public MessageProducer createProducer(Destination destination) throws JMSException {
			return session.createProducer(destination);
		}

		@Override
		public MessageConsumer createConsumer(Destination destination) throws JMSException {
			return session.createConsumer(destination);
		}

		@Override
		public MessageConsumer createConsumer(Destination destination, String messageSelector) throws JMSException {
			return session.createConsumer(destination, messageSelector);
		}

		@Override
		public MessageConsumer createConsumer(Destination destination, String messageSelector, boolean noLocal) throws JMSException {
			return session.createConsumer(destination, messageSelector, noLocal);
		}

		@Override
		public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName) throws JMSException {
			return session.createSharedConsumer(topic, sharedSubscriptionName);
		}

		@Override
		public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String messageSelector)
				throws JMSException {
			return session.createSharedConsumer(topic, sharedSubscriptionName, messageSelector);
		}

		@Override
		public Queue createQueue(String queueName) throws JMSException {
			return session.createQueue(queueName);
		}

		@Override
		public Topic createTopic(String topicName) throws JMSException {
			return session.createTopic(topicName);
		}

		@Override
		public TopicSubscriber createDurableSubscriber(Topic topic, String name) throws JMSException {
			return session.createDurableSubscriber(topic, name);
		}

		@Override
		public TopicSubscriber createDurableSubscriber(Topic topic, String name, String messageSelector, boolean noLocal)
				throws JMSException {
			return session.createDurableSubscriber(topic, name, messageSelector, noLocal);
		}

		@Override
		public MessageConsumer createDurableConsumer(Topic topic, String name) throws JMSException {
			return session.createDurableConsumer(topic, name);
		}

		@Override
		public MessageConsumer createDurableConsumer(Topic topic, String name, String messageSelector, boolean noLocal)
				throws JMSException {
			return session.createDurableConsumer(topic, name, messageSelector, noLocal);
		}

		@Override
		public MessageConsumer createSharedDurableConsumer(Topic topic, String name) throws JMSException {
			return session.createSharedDurableConsumer(topic, name);
		}

		@Override
		public MessageConsumer createSharedDurableConsumer(Topic topic, String name, String messageSelector) throws JMSException {
			return session.createSharedDurableConsumer(topic, name, messageSelector);
		}

		@Override
		public QueueBrowser createBrowser(Queue queue) throws JMSException {
			return session.createBrowser(queue);
		}

		@Override
		public QueueBrowser createBrowser(Queue queue, String messageSelector) throws JMSException {
			return session.createBrowser(queue, messageSelector);
		}

		@Override
		public TemporaryQueue createTemporaryQueue() throws JMSException {
			return session.createTemporaryQueue();
		}

		@Override
		public TemporaryTopic createTemporaryTopic() throws JMSException {
			return session.createTemporaryTopic();
		}

		@Override
		public void unsubscribe(String name) throws JMSException {
			session.unsubscribe(name);
		}
	}
}
