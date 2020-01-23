package sandbox;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class Lihat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable jtb;
	public String r="", s="";
	Ubhis h;
	
	Lihat(String u)
	{
		super("Daftar history");
		setBounds(2,10,1310,900);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
	    	  public void windowClosing(WindowEvent e) {
	                Dokter.main(new String[]{});
	                dispose();
	            }
	    });
		String pid=u;
		setLayout(null);
		String data[][]=null;
	  	String column[] = {"Nomor","Nama","Keluhan","Diagnosa","Pengobatan","Tanggal"};
	  	try{
	  		Connection con=DB.getConnection();
	  		Statement st = con.createStatement();
	  		int rows=0;
	  		ResultSet res = st.executeQuery("SELECT COUNT(*) FROM history WHERE p_id= " + pid);
	  		while (res.next()){
                rows = res.getInt(1);
            }
	  		String command = "select * from history where p_id= " + pid;
	  		ResultSet rs = st.executeQuery(command);

	  		data=new String[rows][6];
	  		int count=0;
	  		while(rs.next()){
	  			data[count][0]=rs.getString(1);
	  			data[count][2]=rs.getString(3);
	  			data[count][3]=rs.getString(4);
	  			data[count][4]=rs.getString(5);
	  			data[count][5]=rs.getString(6);
	  			String qry ="select * from pasien where p_id= " + rs.getString(2);
	  			ResultSet rt = st.executeQuery(qry);
	  			rt.next();
	  			data[count][1]=rt.getString(3);
  				count++;
	  		}
	  		con.close();
	  	}catch(Exception e){System.out.println(e);}
	  	jtb = new JTable(data,column){
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	  	            return false;
	  		 }    
	  	  };
	      jtb.addMouseListener(new PTL());
	  	  JTableHeader header = jtb.getTableHeader();
	      header.setForeground(Color.blue);
	  	  TableColumnModel col = jtb.getColumnModel();	
	  	  col.getColumn(0).setMaxWidth(50);
	  	  col.getColumn(1).setMaxWidth(290);
	  	  col.getColumn(2).setMaxWidth(290);
	  	  col.getColumn(3).setMaxWidth(290);
	  	  col.getColumn(4).setMaxWidth(290);
          col.getColumn(5).setMaxWidth(100);
	  	  JScrollPane js = new JScrollPane(jtb);
	  	  js.setBounds(2, 35, 1300, 670);
	  	  add(js); 
	 }

	public class PTL extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	        	int row = jtb.rowAtPoint(e.getPoint());
	        	r = jtb.getValueAt(row, 0).toString();
	    }
	            public void mouseClicked(MouseEvent e) {
	           		JTable target = (JTable) e.getSource();
	                int row = target.getSelectedRow();
	                r = target.getValueAt(row, 0).toString();	 
	            	if(e.getClickCount()==2){
	            			h = new Ubhis(r);
	            			h.setVisible(true);
	            			dispose();
	    			}
	            }
	    }  
}
