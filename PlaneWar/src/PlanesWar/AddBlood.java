package PlanesWar;

import java.util.Random;

public class AddBlood 
{
	private int x;
	private int y;
	private int width;
	private int height;
	private String path;//Í¼Æ¬Â·¾¶
	public static boolean hi=false;
	public AddBlood()
	{
		Random random=new Random();
		x=random.nextInt(Planeframe.width-15);//0-479
		y=random.nextInt(Planeframe.height-20)-600;//0-579;
		width=30;
		height=30;
		path="/adblood.png";
	}
	public void Move()
	{
		setY(y+3);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) 
	{
		if (x<0||x>500) 
		{
			Random random=new Random();
			this.x = random.nextInt(Planeframe.width-20);  //0-479
		}
		else 
		{
		this.x = x;
		}
	}
	public int getY() {
		return y;
	}
	public void setY(int y) 
	{
		if (y>600&&hi==false) 
		{
			Random random=new Random();
			this.y=random.nextInt(Planeframe.height)-600;   //0-479
		}
		else if(hi==true)
		{
			this.y = y;
		}
		else 
		{
			this.y = y;
		}
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
