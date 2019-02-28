/**
 * 
 */
package ashu.kr.jha.spring.boot.cntl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ashu.kr.jha.spring.boot.mdl.Topic;
import ashu.kr.jha.spring.boot.srv.TopicsService;

/**
 * @author USER
 *
 */
@RestController
public class TopicsCntl {

	@Autowired
	private TopicsService topicService;

	@RequestMapping("/topics")
	public List<Topic> getListOfTopics() {
		return topicService.getAllTopics();
	}

	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable("id") String id) {
		return topicService.getTopic(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public String addTopic(@RequestBody Topic topic) {
		return topicService.addTopic(topic);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
	public String updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		return topicService.updateTopic(topic, id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
	public String removeTopic(@PathVariable String id) {
		return topicService.removeTopic(id);
	}
	// https://www.youtube.com/watch?v=z8XgBCrTVY0

}
