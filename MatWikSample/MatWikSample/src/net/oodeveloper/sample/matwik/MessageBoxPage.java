package net.oodeveloper.sample.matwik;

import net.oodeveloper.sample.matwik.widget.AjaxMessageBox;
import net.oodeveloper.sample.matwik.widget.Toast;
import net.oodeveloper.sample.matwik.widget.AjaxMessageBox.Button;
import net.oodeveloper.sample.matwik.widget.AjaxMessageBox.OnCLickListener;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

public class MessageBoxPage extends BasePage {
	private static final long serialVersionUID = 1L;
	
	private AjaxMessageBox messageBox;
	
	public MessageBoxPage() {
		
		messageBox = new AjaxMessageBox("messageBox");
		messageBox.setTitle("Dude Question");
		messageBox.setMessage("This is not ‘Nam. This is bowling. There are rules.");
		messageBox.setButton(Button.POSITIVE, "Walter", new OnCLickListener() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				
				Toast.makeText("Bingo El Duderino!").show(target, 4000);
			}
		});
		messageBox.setButton(Button.NEGATIVE, "His Dudeness", new OnCLickListener() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				
				Toast.makeText("Zonk!").show(target, 4000);
			}
		});
		add(messageBox);
		
		AjaxLink<Void> lnkShowMessageBox = new AjaxLink<Void>("lnkShowMessageBox") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				
				messageBox.show(target);
			}
		};
		add(lnkShowMessageBox);
	}
}
