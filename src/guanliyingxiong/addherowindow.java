package guanliyingxiong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import HeroDAO.HeroDAO;


public class addherowindow {
	
	public addherowindow() {
		JFrame j = new JFrame();
		j.setTitle("添加英雄");
		j.setSize(700,550);
		j.setLocationRelativeTo(null);
		j.setLayout(null);
		
		JPanel tablepanel = new JPanel();
		tablepanel.setLayout(null);
		HeroDAO dao = new HeroDAO();
		List<Hero> list = dao.list();
		heroTableModel ht = new heroTableModel(list);
		JTable jt = new JTable(ht);
		jt.getTableHeader().setReorderingAllowed(false);
		jt.getTableHeader().setResizingAllowed(false);
		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane js = new JScrollPane(jt);
		js.setBounds(5, 5, 670, 300);
		tablepanel.add(js);
		tablepanel.setBounds(3, 3, 675, 305);
		
		//下半部
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel textid = new JLabel("id");
		textid.setBounds(50, 10, 30, 20);
		JLabel textname = new JLabel("名称");
		textname.setBounds(180, 10, 30, 20);
		JLabel texthp = new JLabel("血量");
		texthp.setBounds(310, 10, 30, 20);
		JLabel textdamage = new JLabel("攻击力");
		textdamage.setBounds(440, 10, 100, 20);
		
		//增加
		JTextField addid =new JTextField();
		addid.setBounds(20,40,70,25);
		
		JTextField addname =new JTextField();
		addname.setBounds(150,40,90,25);
		
		JTextField addhp =new JTextField();
		addhp.setBounds(285,40,80,25);
		
		JTextField adddamage =new JTextField();
		adddamage.setBounds(420,40,80,25);
		
		//删除
		JTextField removeid =new JTextField();
		removeid.setBounds(20,97,70,25);
		removeid.setEditable(false);
		
		JTextField removename =new JTextField();
		removename.setBounds(150,97,90,25);
		removename.setEditable(false);
		
		JTextField removehp =new JTextField();
		removehp.setBounds(285,97,80,25);
		removehp.setEditable(false);
		
		JTextField removedamage =new JTextField();
		removedamage.setBounds(420,97,80,25);
		removedamage.setEditable(false);
		//修改
				
	    JTextField updatename =new JTextField();
	    updatename.setBounds(150,157,90,25);
				
		JTextField updatehp =new JTextField();
		updatehp.setBounds(285,157,80,25);
				
		JTextField updatedamage =new JTextField();
		updatedamage.setBounds(420,157,80,25);
						
		
		jt.getSelectionModel().setSelectionInterval(0, 0);
		Hero h1 = ht.list.get(0);
        // 更新标签内容
        removeid.setText(""+h1.id);
        removename.setText(""+h1.name);
        removehp.setText(""+h1.hp);
        removedamage.setText(""+h1.damage);
		
		
		jt.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
  
                    // 当选择了某一行的时候触发该事件
                    public void valueChanged(ListSelectionEvent e) {
                        // 获取哪一行被选中了
                    	ht.list = dao.list();
                        int row = jt.getSelectedRow();
                        // 根据选中的行，到HeroTableModel中获取对应的对象
                        Hero h = ht.list.get(row);
                        // 更新标签内容
                        removeid.setText(""+h.id);
                        removename.setText(""+h.name);
                        removehp.setText(""+h.hp);
                        removedamage.setText(""+h.damage);
                        
                        //System.out.println("当前选中的英雄是： " + h.name);
  
                    }
                });		
		
		//按钮
		JButton removebutn = new JButton("删除英雄");
		removebutn.setBounds(530,90,110,40);
		//删除英雄
		removebutn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(removeid.getText());
				String name = removename.getText();
				double hp = Double.parseDouble(removehp.getText());
				int damage = Integer.parseInt(removedamage.getText());
				Hero h = new Hero(id,name,hp,damage);
				dao.remove(h);
				ht.list = dao.list();
				jt.updateUI();						
			}
		});
		
		JButton addbutn = new JButton("添加英雄");
		addbutn.setBounds(530,31,110,40);
		//添加英雄
		addbutn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(isNumeric(addid.getText()) && isNumeric(addhp.getText())&&isNumeric(adddamage.getText())) {
					int id = Integer.parseInt(addid.getText());
					String name = addname.getText();
					double hp = Double.parseDouble(addhp.getText());
					int damage = Integer.parseInt(adddamage.getText());
					Hero h = new Hero(id,name,hp,damage);
					dao.add(h);
					ht.list = dao.list();
					jt.updateUI();
					addid.setText(String.valueOf(id+1));
				}else {
					JOptionPane.showMessageDialog(null, "输入错误，英雄信息请勿包含空格！除英雄名称除外其他数据请填入数字！","添加失败",JOptionPane.ERROR_MESSAGE);
				}							
			}
		
		});
		
		JButton updatebutn = new JButton("修改属性");
		updatebutn.setBounds(530,150,110,40);
		//修改属性
		updatebutn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(isNumeric(updatehp.getText())&&isNumeric(updatedamage.getText())) {
					int id = Integer.parseInt(removeid.getText());
					String name = removename.getText();
					double hp = Double.parseDouble(removehp.getText());
					int damage = Integer.parseInt(removedamage.getText());
					Hero h1 = new Hero(id,name,hp,damage);
					
					
					int h2id =0;
					String h2name = updatename.getText();
					double h2hp = Double.parseDouble(updatehp.getText());
					int h2damage = Integer.parseInt(updatedamage.getText());
					Hero h2 = new Hero(h2id,h2name,h2hp,h2damage);
					dao.update(h1, h2);
					ht.list = dao.list();
					jt.updateUI();	
				}else {
					JOptionPane.showMessageDialog(null, "输入错误，英雄信息请勿包含空格！除英雄名称除外其他数据请填入数字！","添加失败",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
				
			}
		});
		
		
		panel.add(removebutn);
		panel.add(updatebutn);
		panel.add(addbutn);
		panel.add(adddamage);
		panel.add(addhp);
		panel.add(addname);
		panel.add(addid);
		panel.add(removedamage);
		panel.add(removehp);
		panel.add(removename);
		panel.add(removeid);
		panel.add(updatedamage);
		panel.add(updatehp);
		panel.add(updatename);
		panel.add(textid);
		panel.add(textname);
		panel.add(texthp);
		panel.add(textdamage);
		panel.setBounds(20, 310, 675, 200);
		
		
		j.add(panel);
		j.add(tablepanel);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}
	
	public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if(!(str.trim().length()== 0) && isNum.matches() ){
            return true;
        }
        return false;
	}

	
	public static void main(String[] args) {
		addherowindow a = new addherowindow();

	}

}
