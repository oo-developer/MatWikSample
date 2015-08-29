package net.oodeveloper.sample.matwik;

import java.util.Iterator;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;

import net.oodeveloper.sample.matwik.model.Person;
import net.oodeveloper.sample.matwik.model.PersonProvider;
import net.oodeveloper.sample.matwik.resource.images.ImageResource;

public class CollectionPage extends BasePage {
	private static final long serialVersionUID = 1L;
	
	private PersonProvider personProvider = new PersonProvider();
	
	public CollectionPage() {
		
		RefreshingView<Person> list = new RefreshingView<Person>("list") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected Iterator<IModel<Person>> getItemModels() {
				return personProvider.getModelIterator();
			}

			@Override
			protected void populateItem(Item<Person> item) {
				
				Person person = (Person)item.getDefaultModel().getObject();
				
				item.add(new Label("headLine", person.getFirstName() + " " + person.getLastName()));
				item.add(new Label("email", person.getEmail()));
				item.add(new Label("birthday", "Birthday: " + person.getBirthday()));
				item.add(new Image("image", new PackageResourceReference(ImageResource.class, person.getImage())));
				
				for (int ii = 0; ii < 5; ii++) {
					
					Label grade = new Label("grade" + ii, "grade");
					item.add(grade);
					grade.setVisible(ii < person.getRating());
				}
			}
		};
		
		add(list);
	}
}
