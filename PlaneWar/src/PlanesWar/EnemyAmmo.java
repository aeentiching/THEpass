package PlanesWar;

public class EnemyAmmo
{
	private int x;
	private int y;
	private int width;
	private int height;
	private String path;//ͼƬ·��
	private boolean iseffect;//��ǰ�ӵ��Ƿ���Ч
	public EnemyAmmo(int x,int y)
	{
		this.x=x;
		this.y=y;
		width=40;
		height=40;
		path="/efire_1.png";
		iseffect=true;
	}
	public void move()  //�����ӵ��ƶ�
	{
		setY(y+6);
		if(y>600||y<-100)
			iseffect=false;//������Ч
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
	public boolean isIseffect()
	{
		return iseffect;
	}
	public void setIseffect(boolean iseffect)
	{
		this.iseffect = iseffect;
	}
}
