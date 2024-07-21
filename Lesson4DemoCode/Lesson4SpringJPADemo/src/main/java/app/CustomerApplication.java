package app;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import domain.Address;
import domain.CreditCard;
import domain.Customer;
import domain.Student;
import repositories.CustomerRepository;
import repositories.StudentRepository;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class CustomerApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository customerrepository;
	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// create customer
		Customer customer = new Customer(101, "John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);
		customer = new Customer(109, "John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("657483342", "Visa", "09/23");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);
		customer = new Customer(66, "James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);

		// get customers
		System.out.println(customerrepository.findById(66).get());
		System.out.println(customerrepository.findById(101).get());
		System.out.println("-----------All customers ----------------");
		System.out.println(customerrepository.findAll());
		// update customer
		customer = customerrepository.findById(101).get();
		customer.setEmail("jd@gmail.com");
		customerrepository.save(customer);
		System.out.println("-----------find by phone ----------------");
		System.out.println(customerrepository.findByPhone("0622341678"));
		System.out.println("-----------find by email ----------------");
		System.out.println(customerrepository.findCustomerWithEmail("jj123@acme.com"));
		System.out.println("-----------find customers with a certain type of creditcard ----------------");
		List<Customer> customers = customerrepository.findCustomerWithCreditCardType("Visa");
		for (Customer cust : customers) {
			System.out.println(cust);
		}

		System.out.println("-----------find by name ----------------");
		System.out.println(customerrepository.findByName("John doe"));

		// ----------------------------------------------------------------------------------------------------------------------------------

		Address address1 = new Address("123 Main St", "Springfield", "62701");
		Address address2 = new Address("456 Elm St", "Metropolis", "10001");

		Student student1 = new Student(1, "John Doe", "john.doe@example.com", "555-1234", address1);
		Student student2 = new Student(2, "Jane Smith", "jane.smith@example.com", "555-5678", address2);
		Student student3 = new Student(3, "Clark Kent", "clark.kent@example.com", "555-1010", address1);
		Student student4 = new Student(4, "Bruce Wayne", "bruce.wayne@example.com", "555-2020", address2);
		Student student5 = new Student(5, "Oliver Queen", "oliver.queen@example.com", "555-3030", address1);

		studentRepository.saveAll(Arrays.asList(student1, student2, student3, student4, student5));

		System.out.println("-----------All students ----------------");
		System.out.println(studentRepository.findAll());
		System.out.println("-----------find by name ----------------");
		System.out.println(studentRepository.findByName("Clark Kent"));

		System.out.println("-----------find by phone ----------------");
		System.out.println(studentRepository.findByPhone("555-2020"));
	}

}
