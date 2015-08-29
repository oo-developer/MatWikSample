package net.oodeveloper.sample.matwik;

import net.oodeveloper.sample.matwik.model.Person;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class FormPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private Form<Person> form;
	private TextField<String> txtFirstName = new TextField<String>("firstName");
	private TextField<String> txtLastName = new TextField<String>("lastName");
	private EmailTextField txtEmail = new EmailTextField("email");
	private PasswordTextField txtPassword = new PasswordTextField("password", Model.of(""));
	private DateTextField txtBirthday = new DateTextField("birthday", "dd MMM, yyyy");
	private CheckBox chkNewsLetter = new CheckBox("wantsToReceiveNewsletter");
	
	
	public FormPage() {
		
		final Person person = new Person();
		person.setFirstName("Dave");
		person.setLastName("Bowman");
		
		form = new Form<Person>("form", new CompoundPropertyModel<Person>(person));
		add(form);

		form.add(txtFirstName);
		form.add(txtLastName);
		form.add(txtEmail);
		form.add(txtPassword);
		form.add(txtBirthday);
		form.add(chkNewsLetter);
		
		
		AjaxSubmitLink btnSave = new AjaxSubmitLink("btnSave") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				String message = String.format("%s %s OK", 
						person.getFirstName(),
						person.getLastName());
				target.appendJavaScript(String.format("Materialize.toast('%s', 4000)", message));
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				
				target.appendJavaScript("Materialize.toast('Error in input or password empty!', 4000)");
			}
		};
		form.add(btnSave);
	}
}
