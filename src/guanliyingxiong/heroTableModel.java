package guanliyingxiong;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class heroTableModel extends AbstractTableModel {
	String[] columnNames = new String[] { "id", "name", "hp", "damage" };
	
	List<Hero> list = new ArrayList<>();
	
	public heroTableModel(List<Hero> list) {
		this.list  = list;
	}
	
	public int getRowCount() {
        return list.size();
    }
 
    public int getColumnCount() {
        return columnNames.length;
    }
 
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
 
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        Hero hero = list.get(rowIndex);
        if(columnIndex == 0) {
        	return hero.id;
        }
        if(columnIndex == 1) {
        	return hero.name;
        }
        if(columnIndex == 2) {
        	return hero.hp;
        }
        if(columnIndex == 3) {
        	return hero.damage;
        }
        return null;
    }


	
	
	
}
