package PlanesWar; //ʱ���߳�

public class TimeThread extends Thread
{
	@SuppressWarnings("unused")
	private PlanePanel planePanel;
	public static int num;          //��ʾ��Ϸ����
	public static int time=30; 
	public TimeThread(PlanePanel planePanel)
	{
		this.planePanel=planePanel;
	}
	public void run ()
	{
		while(true)
		{
			try 
			{
				sleep(1000);
				time--;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}
