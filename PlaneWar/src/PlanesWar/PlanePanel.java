package PlanesWar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class PlanePanel extends JPanel implements MouseMotionListener,MouseListener
{
	String background;
	String over;
	MinePlane minePlane;  //我机
	EnemyPlane[] enemyPlanes;    //敌机数组
	ArrayList<MyAmmo> myAmmos;//我机子弹集合
	ArrayList<EnemyAmmo> enemyAmmos;//敌机子弹集合
	ArrayList<Boom>booms;  //炸集合
	AddBlood[] addBloods;   //红心合集
	int score;    //积分板
	int time;   //boss出现倒计时
	BOSS1 boss1;    
	BOSS2 boss2;
	public static boolean isdie1;      //BOSS1是否死掉
	public static boolean isdieme;      //我机是否坠机
	public static boolean isdie2;      //BOSS2是否死掉
	public static boolean isdieenemy;  //敌机是否死掉
	public PlanePanel()
	{
		over="/over.png";
		background="/background_3.jpg";
		minePlane=new MinePlane();
		enemyPlanes=new EnemyPlane[13];     //敌机数
		addMouseMotionListener(this);   //注册事件监听
		addMouseListener(this);
		myAmmos=new ArrayList<MyAmmo>();  //初始化我机子弹集合
		enemyAmmos=new ArrayList<EnemyAmmo>();//初始化敌机子弹集合
		booms=new ArrayList<Boom>();
		addBloods=new AddBlood[3];     //红心数
		score=0;      
		boss1=new BOSS1();
		boss2=new BOSS2();
		for(int i=0;i<enemyPlanes.length;i++)
		{
			enemyPlanes[i]=new EnemyPlane();    //初始化一个一个敌机
		}
		for (int i = 0; i < addBloods.length; i++) 
		{
			addBloods[i]=new AddBlood();    //初始化一个一个红心
		}
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		drawbackgroud(g);//画背景
		if (isdieme==false) 
		{
		drawminePlane(g);//画我机
		drawmyammo(g);//画我机子弹
		drawmineplanelife(g);  //画我机血条
		drawAddBlood(g);   //画加血红心
		}
		drawEnemyPlane(g);//画敌机的方法
		drawenemyammos(g);//画敌机子弹
		if (isdie1==false&&isdieme==false) 
		{
		drawBossshit(g);  //画Boss出现的提示
		drawBosslife(g);   //画Boss血条
		drawBoss1(g);    //画BOSS1
		drawBossboold(g);//boss1受伤
		}
		drawover(g);    //画游戏结束
		drawbooms(g);   //画爆炸
		
		if (isdie2==false)
		{
		drawBoss2attention(g);   //画BOSS2出现的提示
		drawBoss2life(g);   //画Boss2血条
		drawboss2(g);   //画BOSS2
		drawBoss2boold(g);     // 画boss2受伤
		}
	}
	/*/画Boos受伤害效果图/*/
	boolean Boss2effect=false;
	
	int count2=0;
	
	public void drawBoss2boold(Graphics g)  // 画boss2受伤
	{
		if(Boss2effect)
		{
			g.drawImage(new ImageIcon(BOSS2.class.getResource("/boss_3ef.png")).getImage(),boss2.getX(),boss2.getY(),boss2.getWidth(),boss2.getHeight(),this);
			
			if(count2++==30)
			{
				Boss2effect=false;
				count2=0;
			}
			
		}
	}
	private void drawAddBlood(Graphics g)   //画加血红心
	{
		for(int i=0;i<addBloods.length;i++)
		{
		    g.drawImage(new ImageIcon(AddBlood.class.getResource(addBloods[i].getPath())).getImage(), 
			addBloods[i].getX(),addBloods[i].getY(),addBloods[i].getWidth(),addBloods[i].getHeight(), this);
		}
	}
	private void drawboss2(Graphics g)    //画BOSS2
	{
		if (PlaneThread.num==2&&TimeThread.time<0&&isdie2==false) 
		{
			g.drawImage(new ImageIcon(BOSS2.class.getResource(boss2.getPath())).getImage(),
					boss2.getX(),boss2.getY(),boss2.getWidth(),boss2.getHeight(),this);
		}
	}
	private void drawBoss2life(Graphics g)   //画Boss2血条
	{
		if (PlaneThread.num==2&&TimeThread.time<0&&isdie2==false&&isdieme==false) 
		{
			g.setColor(Color.RED);
			g.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			g.drawString("BOSS", 10, 70);
			g.drawRect(30, 80, 10, 300);
			g.fillRect(30, 80, 10,boss2.life1);
		}
	}
	private void drawBoss2attention(Graphics g)    //画BOSS2出现的提示
	{
		if (PlaneThread.num==2&&TimeThread.time<=6&&TimeThread.time>0) 
		{
			g.setColor(Color.red);
			g.setFont(new Font("微软雅黑", Font.PLAIN, 32));
			g.drawString("Attention Please！！！！", Planeframe.width/2-120, Planeframe.height/2);
		}
	}
	int bombsSize=0;
	public void drawbooms(Graphics g)   
	{
		for(int i=0;i<booms.size();i++){
			//在过程中，取出每一个爆炸图片
			Boom boom = booms.get(i);
			Image img=new ImageIcon(PlanePanel.class.getResource(boom.getPath())).getImage();
			bombsSize+=1;
				g.drawImage(img, boom.getX(), boom.getY(), 
						boom.getWidth(), boom.getHeight(), this);
				new PlanePanel().repaint();
			//只要画完后，就把爆炸图片设为无效
			if(bombsSize==10)
			{
				bombsSize=0;
				boom.setEffect(false);
			}
		}
	}

	private void drawover(Graphics g)    //画游戏结束画面
	{
		if (isdieme==true) 
		{
			isdieenemy=true;boss1.setY(-5000);boss2.setY(-5000);
			g.drawImage(new ImageIcon(PlanePanel.class.getResource(over)).getImage(),
					0, 0, Planeframe.width, Planeframe.height, this);
			g.setColor(Color.white);
			g.setFont(new Font("微软雅黑", Font.PLAIN, 25));
			g.drawString("你的分数为：", Planeframe.width-350, 60);
			g.drawString(score+"", Planeframe.width-200, 60);
			
		}
	}

	/*/画Boos受伤害效果图/*/
	boolean Boss1effect=false;
	
	int count01=0;
	
	public void drawBossboold(Graphics g)  //boss1受伤
	{
		if(Boss1effect)
		{
			g.drawImage(new ImageIcon(BOSS1.class.getResource("/boss_1ef.png")).getImage(),boss1.getX(),boss1.getY(),boss1.getWidth(),boss1.getHeight(),this);
			
			if(count01++==30)
			{
				Boss1effect=false;
				count01=0;
			}
			
		}
	}
	private void drawBoss1(Graphics g)     //画BOSS1
	{
		if (TimeThread.time<0&&PlaneThread.num==1&&isdie1==false) 
		{
			g.drawImage(new ImageIcon(BOSS1.class.getResource(boss1.getPath())).getImage(),
			boss1.getX(),boss1.getY(),boss1.getWidth(),boss1.getHeight(),this);
		}
	}
	private void drawBosslife(Graphics g)  //画Boss血条
	{
		if (TimeThread.time<0&&isdie1==false&&PlaneThread.num==1) 
		{
			g.setColor(Color.RED);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		g.drawString("BOSS", 10, 70);
		g.drawRect(30, 80, 10, 300);
		g.fillRect(30, 80, 10, boss1.getLife());
		}
	}
	private void drawBossshit(Graphics g)   //画Boss出现的提示
	{
		if (TimeThread.time<=10&&TimeThread.time>0&&PlaneThread.num==1) 
		{
			g.setColor(Color.white);
			g.setFont(new Font("微软雅黑", Font.PLAIN, 32));
			g.drawString("Attention Please......", Planeframe.width/2-120, Planeframe.height/2);
			
		}
	}
	private void drawmineplanelife(Graphics g)   //画我机血条
	{
		if (minePlane.getLife()>100) 
		{
			g.setColor(Color.GREEN);
		}
		else 
		{
			g.setColor(Color.RED);
		}
		g.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		g.drawString("积分："+score, Planeframe.width-100, 30);
		g.drawString("生", Planeframe.width-55, 50);
		g.drawString("命", Planeframe.width-55, 70);
		g.drawRect(Planeframe.width-50, 80, 10, 200);
		g.fillRect(Planeframe.width-50, 80, 10, minePlane.getLife());
	}
	private void drawenemyammos(Graphics g)   //画敌机子弹
	{
		for (int i = 0; i < enemyAmmos.size(); i++)
		{
			EnemyAmmo enemyAmmo=enemyAmmos.get(i);//取当前的敌机子弹
			g.drawImage(new ImageIcon(EnemyAmmo.class.getResource(enemyAmmo.getPath())).getImage(),
					enemyAmmo.getX(),enemyAmmo.getY(),
					enemyAmmo.getWidth(),enemyAmmo.getHeight(),this);
			
		}
	}
	private void drawmyammo(Graphics g)    //画我机子弹
	{
		for (int i = 0; i < myAmmos.size(); i++) 
		{
			MyAmmo myAmmo=myAmmos.get(i);    //取当前的我机子弹
			g.drawImage(new ImageIcon(MyAmmo.class.getResource(myAmmo.getPath())).getImage(),
					myAmmo.getX(), myAmmo.getY(), myAmmo.getWidth(), myAmmo.getHeight(), this);
		}
	}
	private void drawminePlane(Graphics g)    //画我机
	{
		g.drawImage(new ImageIcon(PlanePanel.class.getResource(minePlane.getPath())).getImage(),
				minePlane.getX(),minePlane.getY(),minePlane.getWidth(),minePlane.getHeight(),this);
	}
	private void drawEnemyPlane(Graphics g)    //画敌机
	{
		for(int i=0;i<enemyPlanes.length;i++)
		{
		g.drawImage(new ImageIcon(EnemyPlane.class.getResource(enemyPlanes[i].getPath())).getImage(), 
		enemyPlanes[i].getX(),enemyPlanes[i].getY(),enemyPlanes[i].getWidth(),enemyPlanes[i].getHeight(), this);
		}
	}
	private void drawbackgroud(Graphics g)    //画背景的方法
	{
		g.drawImage(new ImageIcon(PlanePanel.class.getResource(background)).getImage(),
		0, 0, Planeframe.width, Planeframe.height, this);
	}
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO 自动生成的方法存根
		minePlane.setIsfire(true);
	}
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO 自动生成的方法存根
		minePlane.setIsfire(false);
	}
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		// TODO 自动生成的方法存根
		minePlane.setX(e.getX()-20);
		minePlane.setY(e.getY()-20);
	}
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		// TODO 自动生成的方法存根
		minePlane.setX(e.getX()-20);
		minePlane.setY(e.getY()-20);
	}
	
}
