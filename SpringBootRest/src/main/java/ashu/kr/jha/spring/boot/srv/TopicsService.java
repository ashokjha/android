/**
 * 
 */
package ashu.kr.jha.spring.boot.srv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import ashu.kr.jha.spring.boot.mdl.Topic;

/**
 * @author USER
 *
 */
@Service
public class TopicsService {

	private List<Topic> topics = new ArrayList<>(
			Arrays.asList(new Topic("Spring", "Spring Framework", "Spring Framework for everything"),
					new Topic("Springcore", "Spring Core ", "Sprting Core for production ready conmfigured"),
					new Topic("java", "java8", "java for server side"),
					new Topic("js", "javascript", "java Sscript for clienFt")));

	/**
	 * @return list of Topics
	 */
	public List<Topic> getAllTopics() {
		return topics;
	}

	/**
	 * @return list of Topics
	 */
	public Topic getTopic(String id) {
		// Uses Lambda Expression
		try {
			return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		} catch (NoSuchElementException nseExp) {
			Topic topic = new Topic();
			topic.setId(id);
			topic.setDescription("doesn't exist");
			return topic;
		}

	}

	/**
	 * Add Topic
	 * 
	 * @param topic
	 * @return
	 */
	public String addTopic(Topic topic) {
		String msg;
		try {
			topics.stream().filter(t -> t.getId().equals(topic.getId())).findFirst().get();
			msg = " already exist";

		} catch (NoSuchElementException nseExp) {
			topics.add(topic);
			msg = " added successfully.";
		}
		return topic + msg;
	}

	/**
	 * update Topic
	 * 
	 * @param topic
	 */
	public String updateTopic(Topic topic, String id) {
		try {
			topics.set(topics.indexOf(topics.stream().filter(t -> t.getId().equals(id)).findFirst().get()), topic);
		} catch (NoSuchElementException nseExp) {
			return topic + " doesn't exist.";
		}
		return topic + " updated successfully.";
	}

	/**
	 * @param id
	 * @return
	 */
	public String removeTopic(String id) {
		return id + (topics.removeIf(elm -> elm.getId().equals(id)) ? " removed successfully" : " doesn't exist");
	}
}