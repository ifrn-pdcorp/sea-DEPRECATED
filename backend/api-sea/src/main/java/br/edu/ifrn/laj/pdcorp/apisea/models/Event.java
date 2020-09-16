package br.edu.ifrn.laj.pdcorp.apisea.models;

import java.util.Calendar;
import java.util.List;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	@Lob
	private String summary;

	private String thumbPath;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<Activity> activities;

	@NotNull
	private LocalDateTime subscriptionStart;

	@NotNull
	private LocalDateTime subscriptionEnd;

	@ManyToOne
	@NotNull
	private User owner;

	private boolean active;
	
	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}
	
	public Event () {}

	public Event(Long id, @NotBlank String name, @NotBlank String summary, String thumbPath, List<Activity> activities,
			@NotNull LocalDateTime subscriptionStart, @NotNull LocalDateTime subscriptionEnd) {
		this();
		this.id = id;
		this.name = name;
		this.summary = summary;
		this.thumbPath = thumbPath;
		this.subscriptionStart = subscriptionStart;
		this.subscriptionEnd = subscriptionEnd;
		this.activities = activities;
	}

	public Event(Long id, @NotBlank String name, @NotBlank String summary, String thumbPath, List<Activity> activities,
			@NotNull LocalDateTime subscriptionStart, @NotNull LocalDateTime subscriptionEnd, @NotNull User owner) {
		this(id, name, summary, thumbPath, activities, subscriptionStart, subscriptionEnd);
		this.owner = owner;
	}
	
	public boolean checkExistence(Activity activity) {
		return this.getActivities()
				.stream()
				.anyMatch(act -> act.getId().equals(activity.getId()));
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

	public LocalDateTime getSubscriptionStart() {
		return subscriptionStart;
	}

	public void setSubscriptionStart(LocalDateTime subscriptionStart) {
		this.subscriptionStart = subscriptionStart;
	}

	public LocalDateTime getSubscriptionEnd() {
		return subscriptionEnd;
	}

	public void setSubscriptionEnd(LocalDateTime subscriptionEnd) {
		this.subscriptionEnd = subscriptionEnd;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public List<Activity> getActivities() {
		return activities;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", summary=" + summary + ", thumbPath=" + thumbPath
				+ ", subscriptionStart=" + subscriptionStart + ", subscriptionEnd=" + subscriptionEnd + ", active="
				+ active + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
