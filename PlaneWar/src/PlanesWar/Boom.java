package PlanesWar;

public class Boom 
{
	private int x;
	private int y;
	private int width;
	private int height;
	private String path;//Í¼Æ¬Â·¾¶
	boolean isEffect;
	public boolean isEffect() {
		return isEffect;
	}
	public void setEffect(boolean isEffect) {
		this.isEffect = isEffect;
	}
	public Boom (int X,int Y,int HEIGHT,int WIDTH) 
	{
		setX(X);setY(Y);setWidth(WIDTH);setHeight(HEIGHT);
		path="/boom0.gif";
		setEffect(true);
	}
	public int getX() 
	{
		return x;
	}
	public void setX(int x) 
	{
		this.x = x;
	}
	public int getY() {
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
}
