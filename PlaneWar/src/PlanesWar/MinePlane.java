package PlanesWar;  //�ҵķɻ���

public class MinePlane 
{
	private int x;
	private int y;
	private int width;
	private int height;
	private String path;//ͼƬ·��
	private boolean isfire;//��ʾ�ɻ��Ƿ񿪻�״̬
	private int life;
	public MinePlane()
	{
		x=Planeframe.width/2;  //����x����λ��
		y=Planeframe.height-80;  //����y����λ��
		width=50;
		height=50;
		path=choseminePlane();
		isfire=false;
		life=200;
	}
	public int getLife() 
	{
		return life;
	}
	public void setLife(int life) 
	{
		this.life = life;
	}
	public String choseminePlane()  //ѡ���һ��ķ���
	{
		return "/logo.png";
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
	public boolean isIsfire()
	{
		return isfire;
	}
	public void setIsfire(boolean isfire) 
	{
		this.isfire = isfire;
	}
}
