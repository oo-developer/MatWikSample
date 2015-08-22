package net.oodeveloper.sample.matwik;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public abstract class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;

	protected Label lblHeader;
	
	public BasePage() {
		
		lblHeader = new Label("LBL_HELLO", "Hello World!");
		lblHeader.setOutputMarkupId(true);
		add(lblHeader);
		
		AjaxLink<Void> btnBack = new AjaxLink<Void>("BTN_BACK") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				
				target.appendJavaScript("Materialize.toast('Title changed', 4000)");
				target.add(lblHeader);
				lblHeader.setDefaultModelObject("Hello Materialized!");
			}
		};
		add(btnBack);
		
		AjaxLink<Void> btnNavHome = new AjaxLink<Void>("BTN_NAV_HOME") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				
				setResponsePage(HomePage.class);
			}
		};
		add(btnNavHome);
		
		AjaxLink<Void> btnBavForm = new AjaxLink<Void>("BTN_NAV_FORM") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				
				setResponsePage(FormPage.class);
			}
		};
		add(btnBavForm);
		
	}
}
