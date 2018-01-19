package PlanesWar;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Planeframe extends JFrame
{
	public static int width=500;
	public static int height=600;     //界面长宽
	public Planeframe(PlanePanel planepanel)
	{
		setTitle("FLY大战");
		setSize(width, height);
		setLocationRelativeTo(null);    //居中
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //关闭按钮
		add(planepanel);
		setVisible(true);     //可见
	}
}
