package PlanesWar;

import java.util.Random;

public class BOSS2   
{
	private int x;
	private int y;
	private int width;
	private int height;
	private String path;//Í¼Æ¬Â·¾¶
	int life1;
	public BOSS2()
	{
		x=-150;
		y=-150;
		width=150;
		height=150;
		path="/boss_3.png";
		life1=500;
	}
	boolean isLeft=true;//ÅÐ¶ÏBOOSÍùÄÄ×ß
	boolean istop=true;//ÅÐ¶ÏBOOSÍùÄÄ×ß
	public void move()
	{
		if(getY()>Planeframe.height-65)
			istop=true;
		else if(getY()<0)
			istop=false;
		if(istop==true)
			setY(getY()-10);
		if(istop==false)
			setY(getY()+8);
		
		if(getX()<20)
			isLeft=false;
		else if(getX()>Planeframe.width-55)
			isLeft=true;
		if(isLeft==true)
			setX(getX()-10);
		if(isLeft==false)
		setX(getX()+8);
	}
	public int getX() 
	{
		return x;
	}
	public void setX(int x) 
	{
		this.x = x;
	}
	public int getY() 
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public int getWidth() 
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	public int getHeight() 
	{
		return height;
	}
	public void setHeight(int height) 
	{
		this.height = height;
	}
	public String getPath() 
	{
		return path;
	}
	public void setPath(String path) 
	{
		this.path = path;
	}
	public int getLife1() 
	{
		return life1;
	}
	public void setLife1(int life1) 
	{
		this.life1 = life1;
	}
}

