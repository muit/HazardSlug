package com.base.GUI;



public class Button extends Element{
	private boolean used;
	
	public Button(float x, float y)
	{
		super(x,y);
		loadTexture("button");
		used = false;
	}
	
	@Override
	protected void update()
	{
		/*if (click inside && !used)
		{
			setFrame(1);
			doAction();
			used = true;
		}
		else if(used)
		{
			setFrame(0);
			used = false;
		}*/
	}
	private void doAction()
	{
		
	}
}
