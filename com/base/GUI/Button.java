package com.base.GUI;



public class Button extends Element{
	private boolean used;
	private String text;
	private Menu menu;
	public Button(Menu menu, float x, float y, int elementId, String text)
	{
		super(x,y, elementId);
		loadTexture("button");
		used = false;
		this.text = text;
		this.menu = menu;
	}
	
	
	public void click()
	{
		if (!used)
		{
			setFrame(1);
			doAction();
			used = true;
		}
		else if(used)
		{
			setFrame(0);
			used = false;
		}
	}
	
	@Override
	protected void doAction()
	{
		menu.buttonDoAction(this);
	}
}
