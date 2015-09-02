package net.oodeveloper.sample.matwik.widget;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class Toast {

	private String text;
	
	public static Toast makeText(String text, Object ...args ) {
		
		Toast toast = new Toast();
		toast.text = String.format(text, args);
		return toast;
	}
	
	public void show(AjaxRequestTarget target, int duration) {

		String script = String.format("Materialize.toast('%s', %s)", text, duration);
		target.appendJavaScript(script);
	}
}
