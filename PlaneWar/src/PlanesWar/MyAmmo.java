package PlanesWar;
//�һ��ӵ���
public class MyAmmo 
{
	private int x;
	private int y;
	private int width;
	private int height;
	private String path;//ͼƬ·��
	private boolean iseffect;//��ǰ�ӵ��Ƿ���Ч
	public MyAmmo(int x,int y)
	{
		this.x=x;
		this.y=y;
		width=50;
		height=50;
		path="/myb_1.png";
		iseffect=true;
	}
	public void move(){//�һ��ӵ��ƶ�
		setY(y-10);
		if(y<0)
		{
			iseffect=false;//�ӵ���Ч
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