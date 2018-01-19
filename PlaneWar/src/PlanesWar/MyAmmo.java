package PlanesWar;
//我机子弹类
public class MyAmmo 
{
	private int x;
	private int y;
	private int width;
	private int height;
	private String path;//图片路径
	private boolean iseffect;//当前子弹是否有效
	public MyAmmo(int x,int y)
	{
		this.x=x;
		this.y=y;
		width=50;
		height=50;
		path="/myb_1.png";
		iseffect=true;
	}
	public void move(){//我机子弹移动
		setY(y-10);
		if(y<0)
		{
			iseffect=false;//子弹无效
		}
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
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
	public boolean isIseffect() {
		return iseffect;
	}
	public void setIseffect(boolean iseffect) {
		this.iseffect = iseffect;
	}	
}