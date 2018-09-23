package com.prakash.subscriber;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prakash.model.Apple;


public class AppleSubscriber<Apple> implements Subscriber<Apple> {

	private Subscription subscription;
	public List<Apple> consumedElements = new LinkedList<>();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	private static final Logger log = LoggerFactory.getLogger(AppleSubscriber.class);

	
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
		log.info("Subscribed");
	}

	public void onNext(Apple item) {
		log.info("Got item {}", item);
		if (Objects.equals(item, "15")) {
			String dateString = dateFormat.format(new Date());
			try {
				log.info("Pause this item {}, time {}", item, dateString);		
				Thread.sleep(5000);
				log.info("Now ready for next item after {} time {}", item, dateString);
				System.exit(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		subscription.request(1);
	}

	public void onError(Throwable throwable) {
		throwable.printStackTrace();
		
	}

	public void onComplete() {
		log.info("Processed, Done");
		
	}

}
