package net.oodeveloper.sample.matwik.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;

public class PersonProvider extends ListDataProvider<Person> {
	private static final long serialVersionUID = 1L;
	
	private List<Person> data = new ArrayList<>();
	
	public PersonProvider() {

		Person homer = new Person();
		homer.setFirstName("Homer");
		homer.setLastName("Simpson");
		homer.setEmail("hsimpson@springfield.net");
		homer.setBirthday(new Date());
		homer.setImage("homer-simpson.jpg");
		homer.setRating(3);
		data.add(homer);
	
		Person bart = new Person();
		bart.setFirstName("Bart");
		bart.setLastName("Simpson");
		bart.setEmail("bsimpson@springfield.net");
		bart.setBirthday(new Date());
		bart.setImage("bart-simpson.jpg");
		bart.setRating(5);
		data.add(bart);
	}
	
	@Override
	protected List<Person> getData() {
		return data;
	}
	
	public Iterator<IModel<Person>> getModelIterator() {
		
		List<IModel<Person>> modelData = new ArrayList<>();
		
		for (Person person : getData()) {
			
			modelData.add(model(person));
		}
		
		return modelData.iterator();
	}
}
