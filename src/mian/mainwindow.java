package mian;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import guanliyingxiong.addherowindow;

public class mainwindow {
	
	public mainwindow() {
		JFrame j = new JFrame();
		j.setTitle("英雄管理系统");
		j.setSize(700,500);
		j.setLayout(null);
		JButton addhero = new JButton("管理英雄");
		addhero.setBounds(10, 10, 100, 30);
		addhero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			new addherowindow();			
			}
		});
		
		j.add(addhero);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}
	
	
	

	public static void main(String[] args) {
		mainwindow m = new mainwindow();
	}

}
