package PlanesWar;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Planeframe extends JFrame
{
	public static int width=500;
	public static int height=600;     //���泤��
	public Planeframe(PlanePanel planepanel)
	{
		setTitle("FLY��ս");
		setSize(width, height);
		setLocationRelativeTo(null);    //����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //�رհ�ť
		add(planepanel);
		setVisible(true);     //�ɼ�
	}
}
