package PlanesWar;

public class PassFrameThread extends Thread 
{
	public static boolean isOne=true;    //�������Ƴ���ִֻ��һ��
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
			//Boos������Ϊ��2��
			if(gamePanel.isdie1==true)
			{
				/*
				 *���л��ƿ� 
				 */
				//ȡ�����˷ɻ�����
				EnemyPlane[] enemyPlanes=gamePanel.enemyPlanes;
				for(int i=0;i<enemyPlanes.length;i++)
				{
					EnemyPlane enemyPlane=enemyPlanes[i];
					enemyPlane.setY(enemyPlane.getY()-2000);
				}
				//���ҵĻһ�Ѫ����---------------
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
					//ͣ��5��
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
