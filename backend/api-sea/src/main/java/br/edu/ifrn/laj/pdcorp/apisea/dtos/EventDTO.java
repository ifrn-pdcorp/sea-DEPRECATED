package br.edu.ifrn.laj.pdcorp.apisea.dtos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.edu.ifrn.laj.pdcorp.apisea.models.Event;

public class EventDTO {

	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String summary;

	private String thumbPath;

	@NotNull
	private Calendar subscriptionStart;

	@NotNull
	private Calendar subscriptionEnd;

	public EventDTO() {
		super();
	}

	public EventDTO(@NotNull Long id, @NotBlank String name, @NotBlank String summary, String thumbPath,
			@NotNull Calendar subscriptionStart, @NotNull Calendar subscriptionEnd) {
		this();
		this.id = id;
		this.name = name;
		this.summary = summary;
		this.thumbPath = thumbPath;
		this.subscriptionStart = subscriptionStart;
		this.subscriptionEnd = subscriptionEnd;
	}

	public EventDTO(Event event) {
		this(event.getId(), event.getName(), event.getSummary(), event.getThumbPath(), event.getSubscriptionStart(),
				event.getSubscriptionEnd());
	}

	public static EventDTO convertFromModel(Event event) {
		return new EventDTO(event);
	}

	public static List<EventDTO> convertFromModel(List<Event> events) {
		List<EventDTO> result = new ArrayList<EventDTO>();

		for (Event e : events) {
			result.add(EventDTO.convertFromModel(e));
		}

		return result;
	}

	public Event convertToModel() {
		return new Event(this.getId(), this.getName(), this.getSummary(), this.getThumbPath(),
				this.getSubscriptionStart(), this.getSubscriptionEnd());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getThumbPath() {
		return thumbPath;
	}

	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}

	public Calendar getSubscriptionStart() {
		return subscriptionStart;
	}

	public void setSubscriptionStart(Calendar subscriptionStart) {
		this.subscriptionStart = subscriptionStart;
	}

	public Calendar getSubscriptionEnd() {
		return subscriptionEnd;
	}

	public void setSubscriptionEnd(Calendar subscriptionEnd) {
		this.subscriptionEnd = subscriptionEnd;
	}

}
