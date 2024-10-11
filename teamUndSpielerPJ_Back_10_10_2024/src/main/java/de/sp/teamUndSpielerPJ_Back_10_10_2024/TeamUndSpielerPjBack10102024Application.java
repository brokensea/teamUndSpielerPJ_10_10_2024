package de.sp.teamUndSpielerPJ_Back_10_10_2024;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class TeamUndSpielerPjBack10102024Application {

	public static void main(String[] args) {
		SpringApplication.run(TeamUndSpielerPjBack10102024Application.class, args);
	}

}
