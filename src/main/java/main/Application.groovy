package main

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
public class Application  {
 // public Logger logger = LoggerFactory.getLogger(this.class.getName())
 // public static final Logger log = LoggerFactory.getLogger(Application.class.getName())
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

  }

}
