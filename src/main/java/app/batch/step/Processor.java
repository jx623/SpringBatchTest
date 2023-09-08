package app.batch.step;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import app.batch.domain.Customer;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class Processor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer data) throws Exception {
    	log.info("Process processing customer with id: " + data.getId());
        if (data.getCountry().equals("Argentina")){
        	log.info("Customer from argentina, return for writer processing");
            return data;
        }
        return null;
    }
}
