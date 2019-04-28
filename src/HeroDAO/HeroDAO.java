package HeroDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import guanliyingxiong.Hero;

public class HeroDAO {
	
	public HeroDAO() {
		try {
			
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	
	}
	
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:how2j.db");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return connection;
	}
	
	public void add(Hero h) {
		if(iscunzai(h)) {
			String sql = "insert into hero values(?,?,?,?)";
			
			try (	
					Connection C = getConnection();
					PreparedStatement S = C.prepareStatement(sql);
					){
				S.setInt(1, h.id);
				S.setString(2, h.name);
				S.setDouble(3, h.hp);
				S.setInt(4, h.damage);
				S.execute();
				JOptionPane.showMessageDialog(null,"添加成功！");
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "添加失败！","添加失败",JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "添加失败id重复！","添加失败",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	public boolean iscunzai(Hero h){
		boolean is = true;
		 String sql = "select * from hero where id = ? ";
		 
		 try (
				Connection c = getConnection();
				PreparedStatement S = c.prepareStatement(sql);
				 ){
			 	
			 	S.setInt(1, h.id);
				ResultSet rs = S.executeQuery();
				if(rs.next()) {
					is = false;
				}
				
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}	 
		 return is;
	}
	
	public void remove(Hero h) {
		String sql ="delete from hero where id=?";
		try (	
				Connection  c = getConnection();
				PreparedStatement S =c.prepareStatement(sql);
				){	
			S.setInt(1,h.id);	
			S.execute();
			JOptionPane.showMessageDialog(null,"删除成功！");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "删除失败！","删除失败",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public List<Hero> list(){
		List<Hero> herolist = new ArrayList<>();
		String sql ="select * from hero";
		try (	
				Connection  c = getConnection();
				PreparedStatement S = c.prepareStatement(sql);
				){	
			ResultSet rs = S.executeQuery();
			
			while(rs.next()) {
				int id =rs.getInt(1);
				String name = rs.getString(2);
				double hp = rs.getDouble(3);
				int damage = rs.getInt(4);
				Hero h = new Hero(id,name,hp,damage);
				herolist.add(h);	
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return herolist;
	}
	
	public void update(Hero h1,Hero h2) {
		String sql = "update hero set name = ? ,hp = ? ,damage=? where id = ?";
		try (	
				Connection  c = getConnection();
				PreparedStatement S = c.prepareStatement(sql);
				){	
			S.setString(1, h2.name);
			S.setDouble(2, h2.hp);
			S.setInt(3, h2.damage);
			S.setInt(4, h1.id);
			S.execute();
			JOptionPane.showMessageDialog(null,"修改成功！");
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败！","修改失败",JOptionPane.ERROR_MESSAGE);
		}
	}	
			
	public static void main(String[] args) {
		Hero a = new Hero(5,"garren",515,616);
		HeroDAO b= new HeroDAO();
		
		b.add(a);
	}
}
