package net.oodeveloper.sample.matwik;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;

public class FormPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private Form<Void> form;
	private TextField<String> txtFirstName;
	private TextField<String> txtLastName;
	private PasswordTextField txtPassword;
	private TextField<String> txtEMail;

	public FormPage() {
		
		form = new Form<>("FORM");
		txtFirstName = new TextField<String>("TXT_FNAME");
		txtLastName = new TextField<String>("TXT_LNAME");
		txtPassword = new PasswordTextField("TXT_PW");
		txtEMail = new TextField<String>("TXT_EMAIL");
		
		add(form);
		form.add(txtFirstName);
		form.add(txtLastName);
		form.add(txtPassword);
		form.add(txtEMail);
		
		AjaxLink<Void> btnSave = new AjaxLink<Void>("BTN_SAVE") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick(AjaxRequestTarget arg0) {
				
				
			}
		};
		form.add(btnSave);
	}
}
