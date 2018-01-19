package PlanesWar;

import java.util.ArrayList;
import java.util.Random;

public class PlaneThread extends Thread  //ϵͳ���߳�
{
	private static final int i = 0;
	private PlanePanel planePanel;
	TimeThread timeThread;
	
	int count=0;
	
	int a=1;
	public static int num;    //��ʾ�ؿ�
	
	public PlaneThread(PlanePanel planePanel)
	{
		this.planePanel=planePanel;
	}
	public void run()
	{
		num=1;   //��ʾ�ؿ�1
		TimeThread.time=20;
		while(true)
		{
			try
			{
				sleep(20);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			EnemyPlanesMove();   //�л������ƶ�
			if(++count%8==0)
			{
				addMyAmmo();  //����һ��ӵ�  
				count=0;
			}
			mybulletmove();//�һ��ӵ��ƶ�
			removemyammo();//�Ƴ��һ��ӵ�
			addenemyammos();//��ӵл��ӵ�
			enemyammomove();  //�л��ӵ��ƶ�
			removeenemyammos();   //�Ƴ��л��ӵ�
			MyAmmos_hit_enemyplane();  //�һ��ӵ����л���ײ
			BOSS1move();   //BOSS1�ƶ�����
			BOSS1_Hitmyammos();  //�һ��ӵ�ײ��BOSS1
			isdieboss1();   //�ж�BOSS1�Ƿ�����
			Enemyammo_hit_me();  //�л��ӵ�������ײ
			Isdie_me();  //�ж��һ��Ƿ�����
			mineplane_hitboss1();  //�һ���ײBOSS1
			foe_hitus();   //�л����һ���ײ
			drawboosstage();  //��BOSS����ʱ�峡Ч��
			BOSS2move();   //BOSS2�ƶ�����
			reMoveboom();  //�Ƴ���ը
			HP();    //�����ƶ�
			meetHP();   //�һ��Ե�����
			BOSS2_hitme();    //BOSS2������ײ
			myammoHitBoss2();  //�һ��ӵ�����BOSS2
			reMoveblood();   //�Ƴ�����
			diebOss2();   //�ж�BOSS2�Ƿ�����
			isdiefoe();   //�л��Ƿ�����
		}
	}
	
	private void isdiefoe()   //�л��Ƿ�����
	{
		EnemyPlane[]enemyPlanes=planePanel.enemyPlanes; //ȡ�л�����
		for (int i = 0; i < enemyPlanes.length; i++) 
		{
		EnemyPlane enemyPlane=enemyPlanes[i];  //ȡ��ǰ�л�
			if (enemyPlane.getLife()==0)
			{
				planePanel.enemyPlanes[i].setY(-600);
				planePanel.isdieenemy=true;
			}
		}
	}
	@SuppressWarnings("static-access")
	private void diebOss2() //�ж�BOSS2�Ƿ�����
	{
		if (planePanel.boss2.getLife1()==0)
		{
			planePanel.isdie2=true;
			planePanel.boss2.setY(-5000);
		} 
	}
	private void reMoveblood()   //�Ƴ�����
	{
		AddBlood [] addBloods=planePanel.addBloods; //ȡ���ļ���
		if (planePanel.minePlane.getLife()==0) 
		{
			for(int i=0;i<addBloods.length;i++)
			{
				addBloods[i].setY(-5000);
			}
		} 
	}
	private void myammoHitBoss2() //�һ��ӵ�����BOSS2
	{
		ArrayList<MyAmmo> myAmmos=planePanel.myAmmos;  //ȡ�һ��ӵ�����
		BOSS2 boss2=planePanel.boss2;
		for (int i = 0; i < myAmmos.size(); i++) 
		{
			MyAmmo myAmmo=myAmmos.get(i);
			boolean ishit=myammoHitBoss2(boss2,myAmmo);
			if (ishit==true) 
			{
				boss2.setLife1(boss2.getLife1()-5);
				myAmmo.setIseffect(false);
				myAmmo.setY(-1000);
				planePanel.Boss2effect=true;//boss2����
			}
		}
	}
	private boolean myammoHitBoss2(BOSS2 boss2, MyAmmo myAmmo)   //�һ��ӵ���BOSS2��ײ
	{
		int x1=boss2.getX();
		int y1=boss2.getY();
		int w1=boss2.getWidth();
		int h1=boss2.getHeight();
		int x2=myAmmo.getX();
		int y2=myAmmo.getY();
		int w2=myAmmo.getWidth();
		int h2=myAmmo.getHeight();		
		if(x2>=x1&&x2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2>=x1&&x2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1||
				x2+w2>=x1&&x2+w2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2+w2>=x1&&x2+w2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private void BOSS2_hitme()      //BOSS2������ײ
	{
		MinePlane minePlane =planePanel.minePlane;
		BOSS2 boss2=planePanel.boss2;
		boolean ishit=BOSS2_hitme(boss2,minePlane);
		if(ishit==true)
		{
			boss2.setLife1(boss2.getLife1()-5);
			minePlane.setLife(minePlane.getLife()-20);
			planePanel.Boss2effect=true;//boss2����
		}
	}
	private boolean BOSS2_hitme(BOSS2 boss2, MinePlane minePlane) 
	{
		int x1=boss2.getX();
		int y1=boss2.getY();
		int w1=boss2.getWidth();
		int h1=boss2.getHeight();
		int x2=minePlane.getX();
		int y2=minePlane.getY();
		int w2=minePlane.getWidth();
		int h2=minePlane.getHeight();		
		if(x2>=x1&&x2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2>=x1&&x2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1||
				x2+w2>=x1&&x2+w2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2+w2>=x1&&x2+w2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	@SuppressWarnings("unused")
	private void meetHP()    //�һ��Ե�����
	{
		MinePlane minePlane=planePanel.minePlane;   //ȡ�һ�
		AddBlood [] addBloods=planePanel.addBloods; //ȡ���ļ���
			for (int i = 0; i < addBloods.length; i++) 
			{
				AddBlood[] addBlood=addBloods;  //ȡ��ǰ����
				boolean ishit=meetHP(addBloods[i],minePlane);
				if (ishit==true) 
				{
					if (minePlane.getLife()<200) 
					{
						minePlane.setLife(minePlane.getLife()+20);
					} 
					addBloods[i].setY(-2000);
				}
			}
	}
	private boolean meetHP(AddBlood addBlood, MinePlane minePlane)   //�Ե�����
	{
		int x1=addBlood.getX();
		int y1=addBlood.getY();
		int w1=addBlood.getWidth();
		int h1=addBlood.getHeight();
		int x2=minePlane.getX();
		int y2=minePlane.getY();
		int w2=minePlane.getWidth();
		int h2=minePlane.getHeight();		
		if(x2>=x1&&x2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2>=x1&&x2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1||
				x2+w2>=x1&&x2+w2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2+w2>=x1&&x2+w2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	private void HP()  //�����ƶ�
	{
		AddBlood[] addBloods=planePanel.addBloods;     //ȡ��������
		for(int i=0;i<addBloods.length;i++)
		{
			addBloods[i].Move();
		}
	}
	private void reMoveboom()   //�Ƴ���ը
	{
		ArrayList<Boom> boom=planePanel.booms;   //��ȡ��ը����
		for(int i=0;i<boom.size();i++)
		{
			if(boom.get(i).isEffect==false)
			{
				boom.remove(i);//�Ƴ���Ч�ı�ը
			}
		}
	}
	public void addBOSSBOOM(BOSS1 boss1)   //��BOSS1��ը
	{
		Random random=new Random();
		int n=random.nextInt(100)-100;
		ArrayList<Boom> bombs = planePanel.booms;
		Boom bomb = new Boom(boss1.getX()+n,boss1.getY()+n,
				boss1.getWidth()-30,boss1.getHeight()-30);
		bombs.add(bomb);
	}
	public void addSmallBomb(EnemyPlane enemyPlane)    //���л���ը
	{
		//�ȴ������ȡ����ը����
		ArrayList<Boom> bombs = planePanel.booms;
		Boom bomb = new Boom(enemyPlane.getX(),enemyPlane.getY(),
				             enemyPlane.getWidth(),enemyPlane.getHeight());
		//����ը������ӵ���ը������
		bombs.add(bomb);
	}
	@SuppressWarnings("static-access")
	private void BOSS2move()   //BOSS2�ƶ�����
	{
		if (num==2&&TimeThread.time<0&&planePanel.isdie2==false) 
		{
			planePanel.boss2.move();
		}
	}
	@SuppressWarnings("static-access")
	private void drawboosstage()   //BOSS����ʱ�峡Ч��
	{
		EnemyPlane[]enemyPlanes=planePanel.enemyPlanes; //ȡ�л�����
		if (num==1&&TimeThread.time<=20&&TimeThread.time>8&&planePanel.isdie1==false) 
		{
			for (int i = 0; i < enemyPlanes.length; i++) 
			{
			EnemyPlane enemyPlane=enemyPlanes[i];  //ȡ��ǰ�л�
			enemyPlane.re=true;
			}
		}
	}
	private void foe_hitus()   ////�л����һ���ײ
	{
		MinePlane minePlane=planePanel.minePlane;   //ȡ�һ�
		EnemyPlane[]enemyPlanes=planePanel.enemyPlanes; //ȡ�л�����
			for (int i = 0; i < enemyPlanes.length; i++) 
			{
				EnemyPlane enemyPlane=enemyPlanes[i];  //ȡ��ǰ�л�
				boolean ishit=foe_hitus(enemyPlane,minePlane);
				if (ishit==true) 
				{
					enemyPlane.setLife(-20);
					addSmallBomb(enemyPlane);
					enemyPlane.setY(-2000);
					minePlane.setLife(minePlane.getLife()-10);
					planePanel.score++;
				}
			}
}
	
	private boolean foe_hitus(EnemyPlane enemyPlane, MinePlane minePlane)   //�һ�ײ���˵л�
	{
		int x1=enemyPlane.getX();
		int y1=enemyPlane.getY();
		int w1=enemyPlane.getWidth();
		int h1=enemyPlane.getHeight();
		int x2=minePlane.getX();
		int y2=minePlane.getY();
		int w2=minePlane.getWidth();
		int h2=minePlane.getHeight();		
		if(x2>=x1&&x2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2>=x1&&x2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1||
				x2+w2>=x1&&x2+w2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2+w2>=x1&&x2+w2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	private void mineplane_hitboss1() //�һ���ײBOSS1
	{
		MinePlane minePlane =planePanel.minePlane;
		BOSS1 boss1=planePanel.boss1;
		boolean ishit=mineplane_hit_boss1(boss1,minePlane);
	
		if(ishit==true)
		{
			boss1.setLife(boss1.getLife()-5);
			minePlane.setLife(minePlane.getLife()-20);
			planePanel.Boss1effect=true;//boss1����
		}
	}
	private boolean mineplane_hit_boss1(BOSS1 boss1,MinePlane minePlane)  //�һ���BOSS1��ײ
	{
		int x1=boss1.getX();
		int y1=boss1.getY();
		int w1=boss1.getWidth();
		int h1=boss1.getHeight();
		int x2=minePlane.getX();
		int y2=minePlane.getY();
		int w2=minePlane.getWidth();
		int h2=minePlane.getHeight();		
		if(x2>=x1&&x2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2>=x1&&x2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1||
				x2+w2>=x1&&x2+w2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2+w2>=x1&&x2+w2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	@SuppressWarnings("static-access")
	private void Isdie_me()   //�ж��һ��Ƿ�����
	{
		if(planePanel.minePlane.getLife()<=0)
		{
			planePanel.isdieme=true;
		}
	}
	private void Enemyammo_hit_me()    //�о����ӵ������һ�
	{
		ArrayList<EnemyAmmo> enemyAmmos=planePanel.enemyAmmos; //ȡ�л��ӵ�����
		MinePlane minePlane=planePanel.minePlane;
		for (int i = 0; i < enemyAmmos.size(); i++) 
		{
			EnemyAmmo eneAmmo=enemyAmmos.get(i);
			boolean ishit=Enemyammo_hit_me(minePlane,eneAmmo);
			if (ishit==true) 
			{
				minePlane.setLife(minePlane.getLife()-20);
				eneAmmo.setIseffect(false);
				eneAmmo.setY(-100);
			}
		}
		
	}
	
	private boolean Enemyammo_hit_me(MinePlane minePlane,EnemyAmmo enemyAmmo) //�л��ӵ�������ײ
	{
		int x1=enemyAmmo.getX();
		int y1=enemyAmmo.getY();
		int w1=enemyAmmo.getWidth();
		int h1=enemyAmmo.getHeight();
		int x2=minePlane.getX();
		int y2=minePlane.getY();
		int w2=minePlane.getWidth();
		int h2=minePlane.getHeight();		
		if(x2>=x1&&x2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2>=x1&&x2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1||
				x2+w2>=x1&&x2+w2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2+w2>=x1&&x2+w2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	@SuppressWarnings("static-access")
	private void isdieboss1()   //�ж�BOSS1�Ƿ�����
	{
		BOSS1 boss1=planePanel.boss1;
		ArrayList<Boom>bom=planePanel.booms;
		EnemyPlane[]enemyPlanes=planePanel.enemyPlanes; //ȡ�л�����
		
		if(planePanel.boss1.getLife()<=0)
		{
			planePanel.isdie1=true;
			
			for(int i=0;i<8;i++)   //��ӱ�ը
			{
				addBOSSBOOM(boss1);
			}
			bom.get(a).isEffect=false;
			planePanel.boss1.setX(-1000);
			planePanel.boss1.setY(-1000);
			for (int i = 0; i < enemyPlanes.length; i++) 
			{
				EnemyPlane enemyPlane=enemyPlanes[i];  //ȡ��ǰ�л�
				planePanel.enemyPlanes[i].setY(-5555);
			}
			if (a==1)
			{
				planePanel.score+=300;
				a++;
			}
		}
		
	}
	private void BOSS1_Hitmyammos()   //�һ��ӵ�ײ��BOSS1
	{
		ArrayList<MyAmmo> myAmmos=planePanel.myAmmos;  //ȡ�һ��ӵ�����
		BOSS1 boss1=planePanel.boss1;
		for (int i = 0; i < myAmmos.size(); i++) 
		{
			MyAmmo myAmmo=myAmmos.get(i);
			boolean ishit=myammo_hitBoss1(boss1,myAmmo);
			if (ishit==true) 
			{
				boss1.setLife(boss1.getLife()-10);
				myAmmo.setIseffect(false);
				myAmmo.setY(-100);
				planePanel.Boss1effect=true;//boss1����
			}
		}
	}
	private boolean myammo_hitBoss1(BOSS1 boss1,MyAmmo myAmmo)  //�ҵ��ӵ�����BOSS
	{
		int x1=boss1.getX();
		int y1=boss1.getY();
		int w1=boss1.getWidth();
		int h1=boss1.getHeight();
		int x2=myAmmo.getX();
		int y2=myAmmo.getY();
		int w2=myAmmo.getWidth();
		int h2=myAmmo.getHeight();		
		if(x2>=x1&&x2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2>=x1&&x2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1||
				x2+w2>=x1&&x2+w2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2+w2>=x1&&x2+w2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	@SuppressWarnings("static-access")
	private void BOSS1move()   //BOSS1�ƶ�����
	{
		if (TimeThread.time<0&&planePanel.isdie1==false) 
		{
			planePanel.boss1.move();
		}
	}
	private void MyAmmos_hit_enemyplane() //�һ��ӵ����л���ײ
	{
		ArrayList<MyAmmo>myAmmos=planePanel.myAmmos;    //ȡ�һ��ӵ�����
		EnemyPlane[]enemyPlanes=planePanel.enemyPlanes; //ȡ�л�����
		for (int i = 0; i < myAmmos.size(); i++) 
		{
			for (int j = 0; j < enemyPlanes.length; j++) 
			{
				MyAmmo myAmmo=myAmmos.get(i);  //ȡ��ǰ�һ��ӵ�
				EnemyPlane enemyPlane=enemyPlanes[j];  //ȡ��ǰ�л�
				boolean ishit=MyAmmos_hit_enemyplane(myAmmo,enemyPlane);
				if (ishit==true) 
				{
					myAmmo.setIseffect(false);
					myAmmo.setX(-100);
					addSmallBomb(enemyPlane);
					enemyPlane.setLife(-20);
					enemyPlane.setY(-200);
					planePanel.score++;
				}
			}
		}
	}
	private boolean MyAmmos_hit_enemyplane(MyAmmo myAmmo,EnemyPlane enemyPlane)//������ײ
	{
		int x1=myAmmo.getX();
		int y1=myAmmo.getY();
		int w1=myAmmo.getWidth();
		int h1=myAmmo.getHeight();
		int x2=enemyPlane.getX();
		int y2=enemyPlane.getY();
		int w2=enemyPlane.getWidth();
		int h2=enemyPlane.getHeight();
		if (x2>=x1&&x2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2>=x1&&x2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1||
			x2+w2>=x1&&x2+w2<=x1+w1&&y2>=y1&&y2<=y1+h1||x2+w2>=x1&&x2+w2<=x1+w1&&y2+h2>=y1&&y2+h2<=y1+h1) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	private void removeenemyammos()   //�Ƴ��л��ӵ�
	{
		ArrayList<EnemyAmmo>enemyAmmos=planePanel.enemyAmmos;
		for (int i = 0; i < enemyAmmos.size(); i++) 
		{
			if (enemyAmmos.get(i).isIseffect()==false) 
			{
			enemyAmmos.remove(i);
			}
			
		}
	}
	private void enemyammomove()   //�л��ӵ��ƶ�
	{
		ArrayList<EnemyAmmo>enemyAmmos=planePanel.enemyAmmos;
		for (int i = 0; i < enemyAmmos.size(); i++) 
		{
			enemyAmmos.get(i).move();
			
		}
	}
	private void mybulletmove()       //�һ��ӵ��ƶ�
	{
		ArrayList<MyAmmo> myAmmos=planePanel.myAmmos;//��ȡ�һ��ӵ�����
		for(int i=0;i<myAmmos.size();i++)
		{
			myAmmos.get(i).move();
		}
	}
	private void addenemyammos()   //��ӵл��ӵ�
	{
		EnemyPlane[] enemyplanes=planePanel.enemyPlanes;//ȡ�л�����
		ArrayList<EnemyAmmo> enemyAmmos=planePanel.enemyAmmos;//ȡ�л��ӵ�����
		for(int i=0;i<enemyplanes.length;i++)
		{
			if(enemyplanes[i].isfire()==true)
				enemyAmmos.add(new EnemyAmmo(enemyplanes[i].getX(), enemyplanes[i].getY()));
		}
	}
	private void removemyammo()      //�Ƴ��һ��ӵ�
	{
		ArrayList<MyAmmo> myAmmos=planePanel.myAmmos;   //��ȡ�һ��ӵ�����
		for(int i=0;i<myAmmos.size();i++)
		{
			if(myAmmos.get(i).isIseffect()==false)
				myAmmos.remove(i);//�Ƴ���Ч���һ��ӵ�
		}
	}
	private void addMyAmmo()   //����һ��ӵ�
	{
		if(planePanel.minePlane.isIsfire()==true)
		{
			planePanel.myAmmos.add(new MyAmmo(planePanel.minePlane.getX(), planePanel.minePlane.getY()));
			//����һ��ӵ�
		}
	}
	private void EnemyPlanesMove()   //�л��ƶ�
	{
		EnemyPlane[] enemyPlanes=planePanel.enemyPlanes;     //ȡ�л�����
		for(int i=0;i<enemyPlanes.length;i++)
		{
			enemyPlanes[i].Move();
		}
		planePanel.repaint();      //���»�ͼ
	}
}
