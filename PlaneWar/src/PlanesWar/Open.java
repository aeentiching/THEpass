package PlanesWar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Open extends JFrame
{
	JButton startButton;//开始按钮
	public Open()
	{
		startButton=new JButton("开始游戏");
		//设置窗体样子
				setSize(280,100);
				setTitle("是否开始游戏？");
				setResizable(false);
				startButton.setBounds(200, 210, 200, 100);
				setLocationRelativeTo(null);
				add(startButton);
				setVisible(true);
				startButton.addActionListener(new ActionListener() 
				{ 
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent e) 
				{
					PlanePanel planePanel=new PlanePanel();
					Planeframe planeframe=new Planeframe(planePanel);
					PlaneThread planeThread=new PlaneThread(planePanel);
					planeThread.start();
					TimeThread timeThread=new TimeThread(planePanel);
					timeThread.start();
					PassFrameThread passFrameThread=new PassFrameThread(planePanel);
					passFrameThread.start();
					planeThread.setPriority(10);
				}
				});
	}
			
}
