package app.batch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.batch.domain.Customer;
import app.batch.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JobInvokeContoller {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job processJob;
    
    @Autowired
    CustomerRepository repo;

    @RequestMapping("/invokejob")
    public String handle() throws Exception {
    	log.info("In controller, init data if empty");
        initData();
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);

        return "Batch job has been invoked";
    }
    
    private void initData() {
    	if(repo.count() < 1) {
    		log.info("Data empty, init...");
    		List<Customer> list = new ArrayList<>();
    		Customer c = null;
    		for(int i = 0; i < 1000; i++) {
    			c = Customer.builder()
    					.contactNo("phone_" + i)
    					.country("Argentina")
    					.dob("dob_" + i)
    					.email("email_" + i + "@gmail.com")
    					.firstName("FName_" + i)
    					.gender("M")
    					.id(i)
    					.lastName("LName_"+ i)
    					.build();
    			list.add(c);
    		}
    		
    		repo.saveAll(list);
    		log.info("Data initiated");
    	}
    }
}












