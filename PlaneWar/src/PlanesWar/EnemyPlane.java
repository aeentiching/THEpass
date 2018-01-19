package PlanesWar;

import java.util.Random;

public class EnemyPlane
{
	private int x;
	private int y;
	private int width;
	private int height;
	int life=20;
	private String path;//图片路径
	public static boolean re=false;
	public EnemyPlane()
	{
		Random random=new Random();
		x=random.nextInt(Planeframe.width-15);//0-479
		y=random.nextInt(Planeframe.height-20)-600;//0-579;
		width=55;
		height=55;
		chose_enemyplane();
	}
	public boolean isfire()  //400分之一开火
	{
		if (x>0&&x<4500&&y>0&&y<200) 
		{
		Random random=new Random();
		int num=random.nextInt(200);
		return num==0?true:false;
		}
		return false;
	}
	@SuppressWarnings("unused")
	public void Move()
	{
		Random random=new Random();
		setY(y+3);
	}
	private void chose_enemyplane()     //选择敌机方法
	{
		// TODO 自动生成的方法存根
		Random random=new Random();
		int num=random.nextInt(9);
		switch(num)
		{
			case 0:path="/enemy_1.png";break;
			case 1:path="/enemy_2.png";break;
			case 2:path="/enemy_3.png";break;
			case 3:path="/enemy_4.png";break;
			case 4:path="/enemy_5.png";break;
			case 5:path="/enemy_6.png";break;
			case 6:path="/enemy_7.png";break;
			case 7:path="/enemy_8.png";break;			
			case 8:path="/enemy_9.png";break;						
		}
	}
	public int getLife() 
	{
		return life;
	}
	public void setLife(int life) 
	{
		this.life = life;
	}
	public int getX() 
	{
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
			this.x=x;
		}
		
	}
	public int getY()
	{
		return y;
	}
	public void setY(int y)
	{
		if (y>600&&re==false) 
		{
			Random random=new Random();
			this.y=random.nextInt(Planeframe.height)-600;   //0-479
		}
		else if(re==true)
		{
			this.y = y;
		}
		else 
		{
			this.y = y;
		}
		
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
}
