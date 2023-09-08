package app.batch.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

import app.batch.domain.Customer;
import app.batch.domain.CustomerLog;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Writer implements ItemWriter<Customer> {
	
	public static int BATCH_COUNT = 0;

    //private final CustomerLogRepository repository;

   // @Autowired
   // public Writer(CustomerLogRepository repository) {
   //     this.repository = repository;
   // }

    @Override
    public void write(List<? extends Customer> customers) throws Exception {
    	log.info("Writer process customers size: " + customers.size());
        for (Customer customer : customers) {
            //repository.save(init(customer));
        	log.info("Customer id: " + customer.getId());
        }
        
        log.info("Batch completed, current batch: " + (++BATCH_COUNT) + "\n\n");
    }

    private CustomerLog init(Customer customer){
        CustomerLog log = new CustomerLog();
        log.setId(customer.getId());
        log.setFirstName(customer.getFirstName());
        log.setLastName(customer.getLastName());
        log.setDob(customer.getDob());
        log.setEmail(customer.getEmail());
        log.setGender(customer.getGender());
        log.setContactNo(customer.getContactNo());
        log.setCountry(customer.getCountry());
        return log;
    }
}
