package PlanesWar;

import java.util.ArrayList;
import java.util.Random;

public class PlaneThread extends Thread  //系统主线程
{
	private static final int i = 0;
	private PlanePanel planePanel;
	TimeThread timeThread;
	
	int count=0;
	
	int a=1;
	public static int num;    //表示关卡
	
	public PlaneThread(PlanePanel planePanel)
	{
		this.planePanel=planePanel;
	}
	public void run()
	{
		num=1;   //表示关卡1
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
			EnemyPlanesMove();   //敌机进行移动
			if(++count%8==0)
			{
				addMyAmmo();  //添加我机子弹  
				count=0;
			}
			mybulletmove();//我机子弹移动
			removemyammo();//移除我机子弹
			addenemyammos();//添加敌机子弹
			enemyammomove();  //敌机子弹移动
			removeenemyammos();   //移除敌机子弹
			MyAmmos_hit_enemyplane();  //我机子弹跟敌机碰撞
			BOSS1move();   //BOSS1移动方法
			BOSS1_Hitmyammos();  //我机子弹撞击BOSS1
			isdieboss1();   //判断BOSS1是否死亡
			Enemyammo_hit_me();  //敌机子弹与我碰撞
			Isdie_me();  //判断我机是否死亡
			mineplane_hitboss1();  //我机碰撞BOSS1
			foe_hitus();   //敌机与我机碰撞
			drawboosstage();  //画BOSS出现时清场效果
			BOSS2move();   //BOSS2移动方法
			reMoveboom();  //移除爆炸
			HP();    //红心移动
			meetHP();   //我机吃掉红心
			BOSS2_hitme();    //BOSS2与我碰撞
			myammoHitBoss2();  //我机子弹击中BOSS2
			reMoveblood();   //移除红心
			diebOss2();   //判断BOSS2是否死亡
			isdiefoe();   //敌机是否死掉
		}
	}
	
	private void isdiefoe()   //敌机是否死掉
	{
		EnemyPlane[]enemyPlanes=planePanel.enemyPlanes; //取敌机集合
		for (int i = 0; i < enemyPlanes.length; i++) 
		{
		EnemyPlane enemyPlane=enemyPlanes[i];  //取当前敌机
			if (enemyPlane.getLife()==0)
			{
				planePanel.enemyPlanes[i].setY(-600);
				planePanel.isdieenemy=true;
			}
		}
	}
	@SuppressWarnings("static-access")
	private void diebOss2() //判断BOSS2是否死亡
	{
		if (planePanel.boss2.getLife1()==0)
		{
			planePanel.isdie2=true;
			planePanel.boss2.setY(-5000);
		} 
	}
	private void reMoveblood()   //移除红心
	{
		AddBlood [] addBloods=planePanel.addBloods; //取红心集合
		if (planePanel.minePlane.getLife()==0) 
		{
			for(int i=0;i<addBloods.length;i++)
			{
				addBloods[i].setY(-5000);
			}
		} 
	}
	private void myammoHitBoss2() //我机子弹击中BOSS2
	{
		ArrayList<MyAmmo> myAmmos=planePanel.myAmmos;  //取我机子弹集合
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
				planePanel.Boss2effect=true;//boss2受伤
			}
		}
	}
	private boolean myammoHitBoss2(BOSS2 boss2, MyAmmo myAmmo)   //我机子弹与BOSS2碰撞
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
	private void BOSS2_hitme()      //BOSS2与我碰撞
	{
		MinePlane minePlane =planePanel.minePlane;
		BOSS2 boss2=planePanel.boss2;
		boolean ishit=BOSS2_hitme(boss2,minePlane);
		if(ishit==true)
		{
			boss2.setLife1(boss2.getLife1()-5);
			minePlane.setLife(minePlane.getLife()-20);
			planePanel.Boss2effect=true;//boss2受伤
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
	private void meetHP()    //我机吃掉红心
	{
		MinePlane minePlane=planePanel.minePlane;   //取我机
		AddBlood [] addBloods=planePanel.addBloods; //取红心集合
			for (int i = 0; i < addBloods.length; i++) 
			{
				AddBlood[] addBlood=addBloods;  //取当前红心
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
	private boolean meetHP(AddBlood addBlood, MinePlane minePlane)   //吃掉红心
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
	private void HP()  //红心移动
	{
		AddBlood[] addBloods=planePanel.addBloods;     //取红心数组
		for(int i=0;i<addBloods.length;i++)
		{
			addBloods[i].Move();
		}
	}
	private void reMoveboom()   //移除爆炸
	{
		ArrayList<Boom> boom=planePanel.booms;   //获取爆炸集合
		for(int i=0;i<boom.size();i++)
		{
			if(boom.get(i).isEffect==false)
			{
				boom.remove(i);//移除无效的爆炸
			}
		}
	}
	public void addBOSSBOOM(BOSS1 boss1)   //画BOSS1爆炸
	{
		Random random=new Random();
		int n=random.nextInt(100)-100;
		ArrayList<Boom> bombs = planePanel.booms;
		Boom bomb = new Boom(boss1.getX()+n,boss1.getY()+n,
				boss1.getWidth()-30,boss1.getHeight()-30);
		bombs.add(bomb);
	}
	public void addSmallBomb(EnemyPlane enemyPlane)    //画敌机爆炸
	{
		//先从面板中取出爆炸集合
		ArrayList<Boom> bombs = planePanel.booms;
		Boom bomb = new Boom(enemyPlane.getX(),enemyPlane.getY(),
				             enemyPlane.getWidth(),enemyPlane.getHeight());
		//将爆炸对象添加到爆炸集合中
		bombs.add(bomb);
	}
	@SuppressWarnings("static-access")
	private void BOSS2move()   //BOSS2移动方法
	{
		if (num==2&&TimeThread.time<0&&planePanel.isdie2==false) 
		{
			planePanel.boss2.move();
		}
	}
	@SuppressWarnings("static-access")
	private void drawboosstage()   //BOSS出现时清场效果
	{
		EnemyPlane[]enemyPlanes=planePanel.enemyPlanes; //取敌机集合
		if (num==1&&TimeThread.time<=20&&TimeThread.time>8&&planePanel.isdie1==false) 
		{
			for (int i = 0; i < enemyPlanes.length; i++) 
			{
			EnemyPlane enemyPlane=enemyPlanes[i];  //取当前敌机
			enemyPlane.re=true;
			}
		}
	}
	private void foe_hitus()   ////敌机与我机碰撞
	{
		MinePlane minePlane=planePanel.minePlane;   //取我机
		EnemyPlane[]enemyPlanes=planePanel.enemyPlanes; //取敌机集合
			for (int i = 0; i < enemyPlanes.length; i++) 
			{
				EnemyPlane enemyPlane=enemyPlanes[i];  //取当前敌机
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
	
	private boolean foe_hitus(EnemyPlane enemyPlane, MinePlane minePlane)   //我机撞击了敌机
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
	private void mineplane_hitboss1() //我机碰撞BOSS1
	{
		MinePlane minePlane =planePanel.minePlane;
		BOSS1 boss1=planePanel.boss1;
		boolean ishit=mineplane_hit_boss1(boss1,minePlane);
	
		if(ishit==true)
		{
			boss1.setLife(boss1.getLife()-5);
			minePlane.setLife(minePlane.getLife()-20);
			planePanel.Boss1effect=true;//boss1受伤
		}
	}
	private boolean mineplane_hit_boss1(BOSS1 boss1,MinePlane minePlane)  //我机与BOSS1碰撞
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
	private void Isdie_me()   //判断我机是否死亡
	{
		if(planePanel.minePlane.getLife()<=0)
		{
			planePanel.isdieme=true;
		}
	}
	private void Enemyammo_hit_me()    //敌军的子弹击中我机
	{
		ArrayList<EnemyAmmo> enemyAmmos=planePanel.enemyAmmos; //取敌机子弹集合
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
	
	private boolean Enemyammo_hit_me(MinePlane minePlane,EnemyAmmo enemyAmmo) //敌机子弹与我碰撞
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
	private void isdieboss1()   //判断BOSS1是否死亡
	{
		BOSS1 boss1=planePanel.boss1;
		ArrayList<Boom>bom=planePanel.booms;
		EnemyPlane[]enemyPlanes=planePanel.enemyPlanes; //取敌机集合
		
		if(planePanel.boss1.getLife()<=0)
		{
			planePanel.isdie1=true;
			
			for(int i=0;i<8;i++)   //添加爆炸
			{
				addBOSSBOOM(boss1);
			}
			bom.get(a).isEffect=false;
			planePanel.boss1.setX(-1000);
			planePanel.boss1.setY(-1000);
			for (int i = 0; i < enemyPlanes.length; i++) 
			{
				EnemyPlane enemyPlane=enemyPlanes[i];  //取当前敌机
				planePanel.enemyPlanes[i].setY(-5555);
			}
			if (a==1)
			{
				planePanel.score+=300;
				a++;
			}
		}
		
	}
	private void BOSS1_Hitmyammos()   //我机子弹撞击BOSS1
	{
		ArrayList<MyAmmo> myAmmos=planePanel.myAmmos;  //取我机子弹集合
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
				planePanel.Boss1effect=true;//boss1受伤
			}
		}
	}
	private boolean myammo_hitBoss1(BOSS1 boss1,MyAmmo myAmmo)  //我的子弹击中BOSS
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
	private void BOSS1move()   //BOSS1移动方法
	{
		if (TimeThread.time<0&&planePanel.isdie1==false) 
		{
			planePanel.boss1.move();
		}
	}
	private void MyAmmos_hit_enemyplane() //我机子弹跟敌机碰撞
	{
		ArrayList<MyAmmo>myAmmos=planePanel.myAmmos;    //取我机子弹集合
		EnemyPlane[]enemyPlanes=planePanel.enemyPlanes; //取敌机集合
		for (int i = 0; i < myAmmos.size(); i++) 
		{
			for (int j = 0; j < enemyPlanes.length; j++) 
			{
				MyAmmo myAmmo=myAmmos.get(i);  //取当前我机子弹
				EnemyPlane enemyPlane=enemyPlanes[j];  //取当前敌机
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
	private boolean MyAmmos_hit_enemyplane(MyAmmo myAmmo,EnemyPlane enemyPlane)//具体碰撞
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
	private void removeenemyammos()   //移除敌机子弹
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
	private void enemyammomove()   //敌机子弹移动
	{
		ArrayList<EnemyAmmo>enemyAmmos=planePanel.enemyAmmos;
		for (int i = 0; i < enemyAmmos.size(); i++) 
		{
			enemyAmmos.get(i).move();
			
		}
	}
	private void mybulletmove()       //我机子弹移动
	{
		ArrayList<MyAmmo> myAmmos=planePanel.myAmmos;//获取我机子弹集合
		for(int i=0;i<myAmmos.size();i++)
		{
			myAmmos.get(i).move();
		}
	}
	private void addenemyammos()   //添加敌机子弹
	{
		EnemyPlane[] enemyplanes=planePanel.enemyPlanes;//取敌机数组
		ArrayList<EnemyAmmo> enemyAmmos=planePanel.enemyAmmos;//取敌机子弹集合
		for(int i=0;i<enemyplanes.length;i++)
		{
			if(enemyplanes[i].isfire()==true)
				enemyAmmos.add(new EnemyAmmo(enemyplanes[i].getX(), enemyplanes[i].getY()));
		}
	}
	private void removemyammo()      //移除我机子弹
	{
		ArrayList<MyAmmo> myAmmos=planePanel.myAmmos;   //获取我机子弹集合
		for(int i=0;i<myAmmos.size();i++)
		{
			if(myAmmos.get(i).isIseffect()==false)
				myAmmos.remove(i);//移除无效的我机子弹
		}
	}
	private void addMyAmmo()   //添加我机子弹
	{
		if(planePanel.minePlane.isIsfire()==true)
		{
			planePanel.myAmmos.add(new MyAmmo(planePanel.minePlane.getX(), planePanel.minePlane.getY()));
			//填加我机子弹
		}
	}
	private void EnemyPlanesMove()   //敌机移动
	{
		EnemyPlane[] enemyPlanes=planePanel.enemyPlanes;     //取敌机数组
		for(int i=0;i<enemyPlanes.length;i++)
		{
			enemyPlanes[i].Move();
		}
		planePanel.repaint();      //重新画图
	}
}
