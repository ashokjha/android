/**
 * 
 */
package ashu.kr.jha.spring.boot.cntl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author USER
 *
 */
@RestController
public class HelloCntl {
	
	@RequestMapping("/knock")
	public String sayHi() {
		return "Hi from Spring Boot REST ";
	}

}
