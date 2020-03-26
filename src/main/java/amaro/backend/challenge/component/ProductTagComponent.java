package amaro.backend.challenge.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductTagComponent {

	@Value(value = "${application.available.tags}")
	private String tagValues;

	private static final String TAGS_TOKEN_SEPARATOR = ",";
	
	private Map<Integer, String> tags = new HashMap<>();
	
	public Map<Integer, String> getTags() {
		if (this.tags.isEmpty()) {
			this.fillTagMap();
		}
		return this.tags;
	}
	
	public boolean containsTag(final String tag) {
		return this.tags.containsValue(tag);
	}
	
	public Optional<String> getByIndex(final int index) {
		return Optional.ofNullable(this.tags.get(index));
	}

	private void fillTagMap() {
		String[] splitted = this.tagValues.split(TAGS_TOKEN_SEPARATOR);
		AtomicInteger index = new AtomicInteger(-1);
		this.tags = Stream.of(splitted)
				.collect(Collectors.toMap(tag -> index.incrementAndGet(), tag -> String.valueOf(tag).trim()));
	}
	
}
