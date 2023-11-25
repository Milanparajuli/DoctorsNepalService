package com.milan.doctorsNepal;

import com.milan.doctorsNepal.utils.BaseConfigurationPatchUtils;
import com.milan.doctorsNepal.utils.InitialPatch;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = "com.milan.doctorsNepal")
@EnableJpaRepositories(basePackages = "com.milan.doctorsNepal")
@EntityScan(basePackages = "com.milan.doctorsNepal")
public class DoctorsNepalApplication {

	@Autowired
	DataSource dataSource;

	@Value("${spring.datasource.url}")
	private String dbValue;

	public static void main(String[] args) {
		SpringApplication.run(DoctorsNepalApplication.class, args);
	}

	@PostConstruct
	public void initialize() throws Exception{
		String baseServerFolder = BaseConfigurationPatchUtils.currentConnectedDb(dbValue);
		InitialPatch.inital(baseServerFolder, dataSource);
	}

}
