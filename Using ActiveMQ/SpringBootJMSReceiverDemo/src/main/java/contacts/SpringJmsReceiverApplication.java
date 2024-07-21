package contacts;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableJms
public class SpringJmsReceiverApplication implements CommandLineRunner {
	@Autowired
	JmsTemplate jmsTemplate;
	public static void main(String[] args) {
		SpringApplication.run(SpringJmsReceiverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Contact contact = new Contact("John", "Doe", "jdoe@me.com", "555-555-5555");
		ObjectMapper objectMapper = new ObjectMapper();
		String contactAsString = objectMapper.writeValueAsString(contact);

		System.out.println("JMS receiver sending message:" + contactAsString);
		jmsTemplate.convertAndSend("testQueue", contactAsString);

	}
}
