package sandbox;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import resources.ResourceLoader;

public class Dokter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuItem ubah, hapus, lihat;
	private JComboBox<String> cb;
	private String r="",s="";
	private JTable jtb;
	private JPopupMenu pM;
	private JTextField jtf;
	private JScrollPane js;
	private JToolBar tb;
	Hapus h;
	Ubah u;
	Lihat l;
	Cari c;
	Abjad a;
	Tambah t;
	Image img1=null, img2=null;
	
    Dokter()    
    {
      setTitle("Daftar Pasien");	
      setBounds(2, 10, 1310, 800);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
	  pM    = new JPopupMenu();
	  ubah  = new JMenuItem("Ubah data pasien");
	  hapus = new JMenuItem("Hapus data pasien");
	  lihat = new JMenuItem("Lihat history");
	  pM.add(ubah);
	  pM.add(hapus);
	  pM.add(lihat);
      addWidgets();
      addTable();
      setVisible(true);
    }
    
    public void addWidgets()
    {
    	cb = new JComboBox<String>();
    	cb.setBounds(10, 0, 30, 30);
	    cb.setPrototypeDisplayValue("XX");
	    String str = "@ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    for (int i=1; i < str.length() - 1; i++)
	      {
	    	  cb.addItem(str.substring(i-1 , i));
	      }
	    cb.setToolTipText("Cari dengan huruf depan");
	    cb.addActionListener (new ActionListener () {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	r = (String)cb.getSelectedItem();
	    	    	a = new Abjad(r);
	    	    	a.setVisible(true);
	    	    	dispose();
	    	    }
	    });
	    img1 = ResourceLoader.loadImage("tambah.png");
	    JButton tm = new JButton(new ImageIcon(img1));
	    tm.setBounds(40, 10, 30, 30);
        tm.setToolTipText("Tambah data pasien");
	    tm.addActionListener (new ActionListener () {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	t= new Tambah();
	    	    	t.setVisible(true);
	    	    	dispose();
	    	    }
	    });
	    jtf = new JTextField();
	    jtf.setBounds(70, 10, 250, 30);
	    jtf.setToolTipText("Tik nama disini!");
	    img2 = ResourceLoader.loadImage("cari.png");
	    JButton Cr   = new JButton(new ImageIcon(img2));
	    Cr.setBounds(320, 10, 30, 30);
        Cr.setToolTipText("Cari dengan nama");
	    Cr.addActionListener (new ActionListener () {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	s = jtf.getText();
	    	    	c = new Cari(s);
	    	    	c.setVisible(true);
	    	    	dispose();
	    	    }
	    });
	    tb = new JToolBar();
	    tb.add(cb);
	    tb.add(tm);
	    tb.add(jtf);
	    tb.add(Cr);
	    tb.setBounds(5, 0, 400, 35);
	    add(tb);
    }
    
    public void addTable()
    {
    	String data[][]=null;
    	String column[] = {"Nomor", "Rekam Medis", "Nama", "Umur", "J_Kelamin", "Alamat", "Phone"};
    	try{
    		Connection con=DB.getConnection();
    		Statement st = con.createStatement();
    		int rows=0;
	  		ResultSet res = st.executeQuery("SELECT COUNT(*) FROM pasien");
            while (res.next()){
                rows = res.getInt(1);
            }
    		String command = "select * from pasien";
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
    	jtb.addMouseListener(new PTL());
    	JTableHeader header = jtb.getTableHeader();
        header.setForeground(Color.blue);
    	TableColumnModel col = jtb.getColumnModel();	
    	col.getColumn(0).setMaxWidth(60);
    	col.getColumn(1).setMinWidth(100);
    	col.getColumn(2).setMinWidth(300);
    	col.getColumn(3).setMaxWidth(50);
    	col.getColumn(4).setMaxWidth(80);
    	col.getColumn(5).setMinWidth(350);
    	js = new JScrollPane(jtb);
    	js.setBounds(2, 35, 1300, 670);
    	add(js, BorderLayout.CENTER);
    }
    
    public class PTL extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
        	int row = jtb.rowAtPoint(e.getPoint());
        	r = jtb.getValueAt(row, 0).toString();
        	if (e.getButton()==MouseEvent.BUTTON3) {
                pM.show(jtb, e.getX(), e.getY());
        		ubah.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	if(u==null){
                    		u = new Ubah(r);
                            u.setVisible(true);
                            dispose();
                    	}
                  }
                });  
    	        hapus.addActionListener(new ActionListener() {
    	             public void actionPerformed(ActionEvent e) {
    	            	if(h==null){
    	            	 	 h = new Hapus(r);
    	            		 h.setVisible(true);
    	            		 dispose();
    	            	}
    	           }
    	        });  	
    	        lihat.addActionListener(new ActionListener() {
    	             public void actionPerformed(ActionEvent e) {
    	            	if(l==null){
    	            	 	 l = new Lihat(r);
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
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run(){
				try {
					  new Dokter();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
}
