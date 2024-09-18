 package GUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChucNangTieuDe extends JPanel implements MouseListener{
	private JLabel  lblClose; 
	private  JFrame f;
	public ChucNangTieuDe(JFrame f)
    {
        init(f);

    }
	
	public void init( JFrame f) {
		//font màu background
		Color my_color_top = new Color(78, 78, 234);
		// tạo font chữ
		Font font1=new Font("Arical", Font.BOLD, 25);
        lblClose=new JLabel("X",JLabel.CENTER);
        lblClose.setFont(font1);
        lblClose.setBackground(my_color_top);

        lblClose.setPreferredSize(new Dimension(40, 40));
        
        lblClose.setBackground(Color.blue);
        
        lblClose.setOpaque(true);
        
        lblClose.addMouseListener(this);
        
        this.setLayout(new FlowLayout(0, 1, 1));

        this.add(lblClose);
        this.f=f;
        
	}
	
	

	

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==lblClose) {
			f.dispose();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
				if(e.getSource()==lblClose) {
					lblClose.setBackground(Color.green);
					lblClose.setOpaque(true);
				}

		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Color my_color_top = new Color(78, 78, 234);
		 if(e.getSource()==lblClose) {
			lblClose.setBackground(my_color_top);
			lblClose.setOpaque(true);
		}

		
	}

}
