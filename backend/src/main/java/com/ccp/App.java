package main.java.com.ccp;

import com.ccp.core.RiskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
  public static void main(String[] args) { SpringApplication.run(App.class, args); }

  @Bean public RiskService riskService() { return new RiskService(); }
}
