package net.oodeveloper.sample.matwik.model;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private String email;
	private Date birthday;
	private String image;
	private int rating;
	private boolean wantsToReceiveNewsletter;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public boolean isWantsToReceiveNewsletter() {
		return wantsToReceiveNewsletter;
	}
	public void setWantsToReceiveNewsletter(boolean wantsToReceiveNewslwetter) {
		this.wantsToReceiveNewsletter = wantsToReceiveNewslwetter;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
