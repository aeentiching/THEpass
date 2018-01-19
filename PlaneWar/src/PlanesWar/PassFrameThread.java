package PlanesWar;

public class PassFrameThread extends Thread 
{
	public static boolean isOne=true;    //用于限制程序只执行一次
	PlanePanel gamePanel;
	public PassFrameThread(PlanePanel gamePanel) 
	{
		this.gamePanel=gamePanel;
	}

	@SuppressWarnings("static-access")
	public void run()
	{
		while(true)
		{
			//Boos死了且为第2关
			if(gamePanel.isdie1==true)
			{
				/*
				 *将敌机移开 
				 */
				//取出敌人飞机集合
				EnemyPlane[] enemyPlanes=gamePanel.enemyPlanes;
				for(int i=0;i<enemyPlanes.length;i++)
				{
					EnemyPlane enemyPlane=enemyPlanes[i];
					enemyPlane.setY(enemyPlane.getY()-2000);
				}
				//将我的灰机血加满---------------
				gamePanel.minePlane.setLife(200);	
				PlaneThread.num=2;
				TimeThread.time=27;
				if(isOne==true)
				{
					try 
					{
						Thread.sleep(5000);
					} catch (InterruptedException e1) {}
					
				}
				if(isOne==true)
				{
					gamePanel.background="/passNext.jpg";
					//停留5秒
					try 
					{
						Thread.sleep(5000);
					} catch (InterruptedException e) {}
					gamePanel.background="/background_2.jpg";
					isOne=false;
				}
				break;
			}
		}
	}

}
