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
	MinePlane minePlane;  //�һ�
	EnemyPlane[] enemyPlanes;    //�л�����
	ArrayList<MyAmmo> myAmmos;//�һ��ӵ�����
	ArrayList<EnemyAmmo> enemyAmmos;//�л��ӵ�����
	ArrayList<Boom>booms;  //ը����
	AddBlood[] addBloods;   //���ĺϼ�
	int score;    //���ְ�
	int time;   //boss���ֵ���ʱ
	BOSS1 boss1;    
	BOSS2 boss2;
	public static boolean isdie1;      //BOSS1�Ƿ�����
	public static boolean isdieme;      //�һ��Ƿ�׹��
	public static boolean isdie2;      //BOSS2�Ƿ�����
	public static boolean isdieenemy;  //�л��Ƿ�����
	public PlanePanel()
	{
		over="/over.png";
		background="/background_3.jpg";
		minePlane=new MinePlane();
		enemyPlanes=new EnemyPlane[13];     //�л���
		addMouseMotionListener(this);   //ע���¼�����
		addMouseListener(this);
		myAmmos=new ArrayList<MyAmmo>();  //��ʼ���һ��ӵ�����
		enemyAmmos=new ArrayList<EnemyAmmo>();//��ʼ���л��ӵ�����
		booms=new ArrayList<Boom>();
		addBloods=new AddBlood[3];     //������
		score=0;      
		boss1=new BOSS1();
		boss2=new BOSS2();
		for(int i=0;i<enemyPlanes.length;i++)
		{
			enemyPlanes[i]=new EnemyPlane();    //��ʼ��һ��һ���л�
		}
		for (int i = 0; i < addBloods.length; i++) 
		{
			addBloods[i]=new AddBlood();    //��ʼ��һ��һ������
		}
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		drawbackgroud(g);//������
		if (isdieme==false) 
		{
		drawminePlane(g);//���һ�
		drawmyammo(g);//���һ��ӵ�
		drawmineplanelife(g);  //���һ�Ѫ��
		drawAddBlood(g);   //����Ѫ����
		}
		drawEnemyPlane(g);//���л��ķ���
		drawenemyammos(g);//���л��ӵ�
		if (isdie1==false&&isdieme==false) 
		{
		drawBossshit(g);  //��Boss���ֵ���ʾ
		drawBosslife(g);   //��BossѪ��
		drawBoss1(g);    //��BOSS1
		drawBossboold(g);//boss1����
		}
		drawover(g);    //����Ϸ����
		drawbooms(g);   //����ը
		
		if (isdie2==false)
		{
		drawBoss2attention(g);   //��BOSS2���ֵ���ʾ
		drawBoss2life(g);   //��Boss2Ѫ��
		drawboss2(g);   //��BOSS2
		drawBoss2boold(g);     // ��boss2����
		}
	}
	/*/��Boos���˺�Ч��ͼ/*/
	boolean Boss2effect=false;
	
	int count2=0;
	
	public void drawBoss2boold(Graphics g)  // ��boss2����
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
	private void drawAddBlood(Graphics g)   //����Ѫ����
	{
		for(int i=0;i<addBloods.length;i++)
		{
		    g.drawImage(new ImageIcon(AddBlood.class.getResource(addBloods[i].getPath())).getImage(), 
			addBloods[i].getX(),addBloods[i].getY(),addBloods[i].getWidth(),addBloods[i].getHeight(), this);
		}
	}
	private void drawboss2(Graphics g)    //��BOSS2
	{
		if (PlaneThread.num==2&&TimeThread.time<0&&isdie2==false) 
		{
			g.drawImage(new ImageIcon(BOSS2.class.getResource(boss2.getPath())).getImage(),
					boss2.getX(),boss2.getY(),boss2.getWidth(),boss2.getHeight(),this);
		}
	}
	private void drawBoss2life(Graphics g)   //��Boss2Ѫ��
	{
		if (PlaneThread.num==2&&TimeThread.time<0&&isdie2==false&&isdieme==false) 
		{
			g.setColor(Color.RED);
			g.setFont(new Font("΢���ź�", Font.PLAIN, 18));
			g.drawString("BOSS", 10, 70);
			g.drawRect(30, 80, 10, 300);
			g.fillRect(30, 80, 10,boss2.life1);
		}
	}
	private void drawBoss2attention(Graphics g)    //��BOSS2���ֵ���ʾ
	{
		if (PlaneThread.num==2&&TimeThread.time<=6&&TimeThread.time>0) 
		{
			g.setColor(Color.red);
			g.setFont(new Font("΢���ź�", Font.PLAIN, 32));
			g.drawString("Attention Please��������", Planeframe.width/2-120, Planeframe.height/2);
		}
	}
	int bombsSize=0;
	public void drawbooms(Graphics g)   
	{
		for(int i=0;i<booms.size();i++){
			//�ڹ����У�ȡ��ÿһ����ըͼƬ
			Boom boom = booms.get(i);
			Image img=new ImageIcon(PlanePanel.class.getResource(boom.getPath())).getImage();
			bombsSize+=1;
				g.drawImage(img, boom.getX(), boom.getY(), 
						boom.getWidth(), boom.getHeight(), this);
				new PlanePanel().repaint();
			//ֻҪ����󣬾Ͱѱ�ըͼƬ��Ϊ��Ч
			if(bombsSize==10)
			{
				bombsSize=0;
				boom.setEffect(false);
			}
		}
	}

	private void drawover(Graphics g)    //����Ϸ��������
	{
		if (isdieme==true) 
		{
			isdieenemy=true;boss1.setY(-5000);boss2.setY(-5000);
			g.drawImage(new ImageIcon(PlanePanel.class.getResource(over)).getImage(),
					0, 0, Planeframe.width, Planeframe.height, this);
			g.setColor(Color.white);
			g.setFont(new Font("΢���ź�", Font.PLAIN, 25));
			g.drawString("��ķ���Ϊ��", Planeframe.width-350, 60);
			g.drawString(score+"", Planeframe.width-200, 60);
			
		}
	}

	/*/��Boos���˺�Ч��ͼ/*/
	boolean Boss1effect=false;
	
	int count01=0;
	
	public void drawBossboold(Graphics g)  //boss1����
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
	private void drawBoss1(Graphics g)     //��BOSS1
	{
		if (TimeThread.time<0&&PlaneThread.num==1&&isdie1==false) 
		{
			g.drawImage(new ImageIcon(BOSS1.class.getResource(boss1.getPath())).getImage(),
			boss1.getX(),boss1.getY(),boss1.getWidth(),boss1.getHeight(),this);
		}
	}
	private void drawBosslife(Graphics g)  //��BossѪ��
	{
		if (TimeThread.time<0&&isdie1==false&&PlaneThread.num==1) 
		{
			g.setColor(Color.RED);
		g.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		g.drawString("BOSS", 10, 70);
		g.drawRect(30, 80, 10, 300);
		g.fillRect(30, 80, 10, boss1.getLife());
		}
	}
	private void drawBossshit(Graphics g)   //��Boss���ֵ���ʾ
	{
		if (TimeThread.time<=10&&TimeThread.time>0&&PlaneThread.num==1) 
		{
			g.setColor(Color.white);
			g.setFont(new Font("΢���ź�", Font.PLAIN, 32));
			g.drawString("Attention Please......", Planeframe.width/2-120, Planeframe.height/2);
			
		}
	}
	private void drawmineplanelife(Graphics g)   //���һ�Ѫ��
	{
		if (minePlane.getLife()>100) 
		{
			g.setColor(Color.GREEN);
		}
		else 
		{
			g.setColor(Color.RED);
		}
		g.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		g.drawString("���֣�"+score, Planeframe.width-100, 30);
		g.drawString("��", Planeframe.width-55, 50);
		g.drawString("��", Planeframe.width-55, 70);
		g.drawRect(Planeframe.width-50, 80, 10, 200);
		g.fillRect(Planeframe.width-50, 80, 10, minePlane.getLife());
	}
	private void drawenemyammos(Graphics g)   //���л��ӵ�
	{
		for (int i = 0; i < enemyAmmos.size(); i++)
		{
			EnemyAmmo enemyAmmo=enemyAmmos.get(i);//ȡ��ǰ�ĵл��ӵ�
			g.drawImage(new ImageIcon(EnemyAmmo.class.getResource(enemyAmmo.getPath())).getImage(),
					enemyAmmo.getX(),enemyAmmo.getY(),
					enemyAmmo.getWidth(),enemyAmmo.getHeight(),this);
			
		}
	}
	private void drawmyammo(Graphics g)    //���һ��ӵ�
	{
		for (int i = 0; i < myAmmos.size(); i++) 
		{
			MyAmmo myAmmo=myAmmos.get(i);    //ȡ��ǰ���һ��ӵ�
			g.drawImage(new ImageIcon(MyAmmo.class.getResource(myAmmo.getPath())).getImage(),
					myAmmo.getX(), myAmmo.getY(), myAmmo.getWidth(), myAmmo.getHeight(), this);
		}
	}
	private void drawminePlane(Graphics g)    //���һ�
	{
		g.drawImage(new ImageIcon(PlanePanel.class.getResource(minePlane.getPath())).getImage(),
				minePlane.getX(),minePlane.getY(),minePlane.getWidth(),minePlane.getHeight(),this);
	}
	private void drawEnemyPlane(Graphics g)    //���л�
	{
		for(int i=0;i<enemyPlanes.length;i++)
		{
		g.drawImage(new ImageIcon(EnemyPlane.class.getResource(enemyPlanes[i].getPath())).getImage(), 
		enemyPlanes[i].getX(),enemyPlanes[i].getY(),enemyPlanes[i].getWidth(),enemyPlanes[i].getHeight(), this);
		}
	}
	private void drawbackgroud(Graphics g)    //�������ķ���
	{
		g.drawImage(new ImageIcon(PlanePanel.class.getResource(background)).getImage(),
		0, 0, Planeframe.width, Planeframe.height, this);
	}
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO �Զ����ɵķ������
		minePlane.setIsfire(true);
	}
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO �Զ����ɵķ������
		minePlane.setIsfire(false);
	}
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		// TODO �Զ����ɵķ������
		minePlane.setX(e.getX()-20);
		minePlane.setY(e.getY()-20);
	}
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		// TODO �Զ����ɵķ������
		minePlane.setX(e.getX()-20);
		minePlane.setY(e.getY()-20);
	}
	
}
