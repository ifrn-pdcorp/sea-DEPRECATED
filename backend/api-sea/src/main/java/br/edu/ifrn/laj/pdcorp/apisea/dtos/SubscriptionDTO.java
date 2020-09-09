package br.edu.ifrn.laj.pdcorp.apisea.dtos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ifrn.laj.pdcorp.apisea.models.Subscription;

public class SubscriptionDTO {

	private Long id;
	private Calendar lastChangeDate;

	private UserDTO user;

	private EventDTO event;
	
	public SubscriptionDTO() {
		super();
	}

	public SubscriptionDTO(Long id, Calendar lastChangeDate, UserDTO user, EventDTO event) {
		this();
		this.id = id;
		this.lastChangeDate = lastChangeDate;
		this.user = user;
		this.event = event;
	}
	
	public SubscriptionDTO(Subscription subscription) {
		this(subscription.getId(),
				subscription.getLastChangeDate(),
				UserDTO.convertFromModel(subscription.getUser()),
				EventDTO.convertFromModel(subscription.getEvent()));
	}
	
	public static SubscriptionDTO convertFromModel(Subscription subscription) {
		return new SubscriptionDTO(subscription);
	}
	
	public static List<SubscriptionDTO> convertFromModel(List<Subscription> subscriptions){
		List<SubscriptionDTO> result = new ArrayList<SubscriptionDTO>();
		
		for(Subscription s : subscriptions)
			result.add(SubscriptionDTO.convertFromModel(s));
		
		return result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(Calendar lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public EventDTO getEvent() {
		return event;
	}

	public void setEvent(EventDTO event) {
		this.event = event;
	}

}
