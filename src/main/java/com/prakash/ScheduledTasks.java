package com.prakash;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.prakash.model.Apple;
import com.prakash.model.Fruit;
import com.prakash.model.Orange;
import com.prakash.subscriber.AppleSubscriber;
import com.prakash.subscriber.OrangeSubscriber;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private SubmissionPublisher<Fruit> publisher;

	private void initializeSubscriber() {
		publisher = new SubmissionPublisher<>();
		getSubscriber().stream().forEach(subscriber -> publisher.subscribe(subscriber));
	}

	public List<Subscriber<Fruit>> getSubscriber() {
		List<Subscriber<Fruit>> subscriber = new ArrayList<>();
		subscriber.add(new AppleSubscriber<>());
		subscriber.add(new OrangeSubscriber<>());
		return subscriber;
	}

	@Scheduled(fixedRate = 1000)
	public void submitFruits() {
		log.info("The time is now {}", dateFormat.format(new Date()));

		// Publish several data items and then close the publisher.
		initializeSubscriber();

		Orange fruit = new Orange();
		fruit.setName("Orange");
		publisher.submit(fruit);
		Apple fruit2 = new Apple();
		fruit2.setName("Apple");
		publisher.submit(fruit2);
		publisher.close();
	}
}
