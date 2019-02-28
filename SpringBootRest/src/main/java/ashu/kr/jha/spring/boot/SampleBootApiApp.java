/**
 * 
 */
package ashu.kr.jha.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author USER
 *
 */
@SpringBootApplication 
public class SampleBootApiApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SampleBootApiApp.class, args);
		System.out.println("First Spring boot application");

	}

}
