package br.com.xyzbank.proposal.shered.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(encoding = "UTF-8", value = "classpath:messages.properties")
public class PropertiesConfiguration {

}
