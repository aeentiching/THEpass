package PlanesWar;

public class BOSS1 
{
	private int x;
	private int y;
	private int width;
	private int height;
	private String path;//Í¼Æ¬Â·¾¶
	private int life;
	public BOSS1()
	{
		x=-150;
		y=-150;
		width=150;
		height=150;
		path="/boss_1.png";
		life=300;
	}
	boolean isLeft=true;//ÅÐ¶ÏBOOSÍùÄÄ×ß
	boolean istop=true;//ÅÐ¶ÏBOOSÍùÄÄ×ß
	public void move()   //bossÒÆ¶¯·½·¨
	{
		if(getY()>Planeframe.height-65)
			istop=true;
		else if(getY()<0)
			istop=false;
		if(istop==true)
			setY(getY()-10);
		if(istop==false)
			setY(getY()+5);
		
		if(getX()<20)
			isLeft=false;
		else if(getX()>Planeframe.width-55)
			isLeft=true;
		if(isLeft==true)
			setX(getX()-10);
		if(isLeft==false)
		setX(getX()+5);
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
	public int getLife() 
	{
		return life;
	}
	public void setLife(int life) 
	{
		this.life = life;
	}
	
}
