package com.subscriber;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.prakash.subscriber.AppleSubscriber;

public class AppleSubscriptionTest {

	@Test
	public void whenSubscribeToIt_thenShouldConsumeAll() 
	  throws InterruptedException {
	  
	    // given
	    SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
	    AppleSubscriber<String> subscriber = new AppleSubscriber<>();
	    publisher.subscribe(subscriber);
	    List<String> items = List.of("1", "x", "2", "x", "3", "x");
	 
	    // when
	    assertThat(publisher.getNumberOfSubscribers()).isEqualTo(1);
	    items.forEach(publisher::submit);
	    publisher.close();
	 
	    // then
//	     await().atMost(1000, TimeUnit.MILLISECONDS)
//	       .until(
//	         () -> assertThat(subscriber.consumedElements)
//	         .containsExactlyElementsOf(items)
//	     );
	}
}
