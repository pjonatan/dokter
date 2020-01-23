package sandbox;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class Cari extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable jtb;
	private JMenuItem ubah,hapus, lihat;
	private JPopupMenu pM;
    public String r="", s="";
	Ubah u;
	Hapus h;
	Lihat l;
	
	Cari(String r)
	{
		super("Daftar pasien");
		setBounds(2,10,1310,900);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
	    	  public void windowClosing(WindowEvent e) {
	                Dokter.main(new String[]{});
	                dispose();
	            }
	    });
		pM    = new JPopupMenu();
		ubah  = new JMenuItem("Ubah data pasien");
		hapus = new JMenuItem("Hapus data pasien");
		lihat = new JMenuItem("Lihat history");
		pM.add(ubah);
		pM.add(hapus);
		pM.add(lihat);
		setLayout(null);
		String data[][]=null;
	  	  String column[] = {"Nomor", "Rekam Medis", "Nama", "Umur", "J_Kelamin", "Alamat", "Phone"};
	  	  try{
	  		Connection con=DB.getConnection();
	  		Statement st = con.createStatement();
	  		int rows=0;
	  		ResultSet res = st.executeQuery("SELECT COUNT(*) FROM pasien where nama like '%" + r + "%'");
            while (res.next()){
                rows = res.getInt(1);
            }
	  		String command = "select * from pasien where nama like '%" + r + "%'";
	  		ResultSet rs = st.executeQuery(command);

	  		data=new String[rows][7];
	  		int count=0;
	  		while(rs.next()){
	  		  		for(int i=0;i<=3;i++){
	  		  			data[count][i]=rs.getString(i + 1);
	  		  		}
	  		  		if(Integer.parseInt(rs.getString(5))==1)
	  		  		{	data[count][4] = "Wanita";}else{
	  		  			data[count][4] = "Laki2";
	  		 		}
	  		  		for(int i=5;i<=6;i++){
	  		  			data[count][i]=rs.getString(i + 1);
	  		  		}
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
	  	  jtb.addMouseListener(new PT());
	  	  JTableHeader header = jtb.getTableHeader();
	      header.setForeground(Color.blue);
	  	  TableColumnModel col = jtb.getColumnModel();	
	  	  col.getColumn(0).setMaxWidth(60);
	  	  col.getColumn(1).setMinWidth(100);
	  	  col.getColumn(2).setMinWidth(300);
	  	  col.getColumn(3).setMaxWidth(50);
	  	  col.getColumn(4).setMaxWidth(80);
	  	  col.getColumn(5).setMinWidth(350);
	  	  JScrollPane js = new JScrollPane(jtb);
	  	  js.setBounds(2, 35, 1300, 670);
	  	  add(js); 
	}
	
    class PT extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
        	int row = jtb.rowAtPoint(e.getPoint());
        	s = jtb.getValueAt(row, 0).toString();
        	if (e.getButton()==MouseEvent.BUTTON3) {
               pM.show(jtb, e.getX(), e.getY());
        		ubah.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	if(u==null){
                    		u = new Ubah(s);
                            u.setVisible(true);
                            dispose();
                    	}
                  }
                });  
    	        hapus.addActionListener(new ActionListener() {
    	             public void actionPerformed(ActionEvent e) {
    	            	if(h==null){
    	            	 	 h = new Hapus(s);
    	            		 h.setVisible(true);
    	            		 dispose();
    	            	}
    	           }
    	        });
       	        lihat.addActionListener(new ActionListener() {
   	             public void actionPerformed(ActionEvent e) {
   	            	if(l==null){
   	            	 	 l = new Lihat(s);
   	            		 l.setVisible(true);
   	            		 dispose();
   	            	}
   	           }
   	        });  	
        	}
        }
        public void mouseClicked(MouseEvent e) {
       		JTable target = (JTable) e.getSource();
            int row = target.getSelectedRow();
            r = target.getValueAt(row, 0).toString();	 
        	if(e.getClickCount()==2){
        			Periksa p = new Periksa(r);
        			p.setVisible(true);
        			dispose();
    		}
        }
     }  
}
