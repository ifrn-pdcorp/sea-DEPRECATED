package br.edu.ifrn.laj.pdcorp.apisea.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.enums.TypeActivity;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiUserException;


@Entity
@Table(name = "activity")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String shortDescription;
	@Column(nullable = false)
	private String local;
	@Column(nullable = false)
	private LocalDateTime dateTime;
	@ManyToMany
	private List<Speaker> speakers;
	private boolean limited;
	@Column(name = "max_number_of_participants")
	private int maxNumberOfParticipants;
	@ManyToMany
	private List<User> participants;
	
	@Column(name = "type_activity")
	@Enumerated(EnumType.STRING)
	private TypeActivity typeActivity;
	
	public void addSpeaker(Speaker speaker) {
		this.speakers.add(speaker);
	}
	
	public void addParticipant(User user)  {
		if(verifyAvailabilityOfList()) {
			this.participants.add(user);
		}
	}
	
	private boolean verifyAvailabilityOfList() {
		if(!isLimited())
			return true;
		return (this.isLimited() && this.getParticipants().size() < this.getMaxNumberOfParticipants()) ? true : false;
	}

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public List<Speaker> getSpeakers() {
		return speakers;
	}

	public boolean isLimited() {
		return limited;
	}

	public void setLimited(boolean limited) {
		this.limited = limited;
	}

	public int getMaxNumberOfParticipants() {
		return maxNumberOfParticipants;
	}

	public void setMaxNumberOfParticipants(int maxNumberOfParticipants) {
		this.maxNumberOfParticipants = maxNumberOfParticipants;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public TypeActivity getTypeActivity() {
		return typeActivity;
	}

	public void setTypeActivity(TypeActivity typeActivity) {
		this.typeActivity = typeActivity;
	}

}
