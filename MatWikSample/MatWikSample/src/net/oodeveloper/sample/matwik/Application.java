package net.oodeveloper.sample.matwik;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class Application extends WebApplication {

	@Override
	protected void init() {
		super.init();

		getDebugSettings().setAjaxDebugModeEnabled(false);
	}
	
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}
}
