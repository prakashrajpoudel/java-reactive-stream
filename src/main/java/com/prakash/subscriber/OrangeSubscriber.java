package com.prakash.subscriber;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prakash.model.Orange;

public class OrangeSubscriber<Orange> implements Subscriber<Orange> {

	private Subscription subscription;
	public List<Orange> consumedElements = new LinkedList<>();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private static final Logger log = LoggerFactory.getLogger(OrangeSubscriber.class);

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
		log.info("Subscribed");
	}

	@Override
	public void onNext(Orange item) {
		log.info("Got item {}", item);
		subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() {
		log.info("Processed, Done");
	}

}
