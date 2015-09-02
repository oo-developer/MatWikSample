package net.oodeveloper.sample.matwik.widget;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class AjaxMessageBox extends Panel {
	private static final long serialVersionUID = 1L;

	public static final String COMMAND_OPEN = "$('#modal1').openModal();";
	
	private String title;
	private String message;
	private String lnkPositiveTitle;
	private String lnkNegativeTitle;
	
	public interface OnCLickListener extends Serializable {
		
		void onClick(AjaxRequestTarget target);
	}
	
	public enum Button{
		
		POSITIVE,
		NEGATIVE
	}
	
	private OnCLickListener positiveListener;
	private OnCLickListener negativeListener;
	
	private AjaxLink<Void> lnkPositive;
	private AjaxLink<Void> lnkNegative;
	
	public AjaxMessageBox(String id) {
		super(id);
	
		CompoundPropertyModel<AjaxMessageBox> model = new CompoundPropertyModel<AjaxMessageBox>(this);
		
		Label lblTitle = new Label("lblTitle", model.bind("title"));
		add(lblTitle);
		
		Label lblMessage = new Label("lblMessage", model.bind("message"));
		add(lblMessage);
		
		lnkPositive = new AjaxLink<Void>("lnkPositive") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				
				positiveListener.onClick(target);
			}
		};
		lnkPositive.add(new Label("lblPositive", model.bind("lnkPositiveTitle")));
		lnkPositive.setVisible(false);
		lnkPositive.setOutputMarkupId(true);
		add(lnkPositive);
		
		lnkNegative = new AjaxLink<Void>("lnkNegative") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				
				negativeListener.onClick(target);
			}
		};
		lnkNegative.add(new Label("lblNegative", model.bind("lnkNegativeTitle")));
		lnkNegative.setVisible(false);
		lnkNegative.setOutputMarkupId(true);
		add(lnkNegative);
	}
	
	public void setButton(Button button, String title, OnCLickListener listener) {
		
		switch (button) {
		
		case POSITIVE:
			
			lnkPositive.setVisible(true);
			lnkPositiveTitle = title;
			positiveListener = listener;
			
			break;
			
		case NEGATIVE:
			
			lnkNegative.setVisible(true);
			lnkNegativeTitle = title;
			negativeListener = listener;
			
			break;
		}
	}
	
	public void show(AjaxRequestTarget target) {
		
		target.appendJavaScript(COMMAND_OPEN);
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		response.render(new JavaScriptContentHeaderItem("$(document).ready(function(){  $('.modal-trigger').leanModal(); });", "", null));
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getLnkNegativeTitle() {
		return lnkNegativeTitle;
	}
	
	public void setLnkNegativeTitle(String lnkNegativeTitle) {
		this.lnkNegativeTitle = lnkNegativeTitle;
	}
	
	public String getLnkPositiveTitle() {
		return lnkPositiveTitle;
	}
	
	public void setLnkPositiveTitle(String lnkPositiveTitle) {
		this.lnkPositiveTitle = lnkPositiveTitle;
	}
}
