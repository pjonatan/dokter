package sandbox;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Hapus extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lb1, lb2, lb3, lb4;
	private JTextField tf1, tf2, tf3, tf4;
	private JButton hp;
	
	public Hapus(String r)
	{
		setTitle("Hapus data pasien");
		setLayout(null);
	      lb1 = new JLabel("No");
	      lb1.setBounds(70, 130, 100, 25);
		  lb1.setForeground(Color.blue);
		  add(lb1);
	      lb2 = new JLabel("Rekam Medis");
	      lb2.setBounds(70, 160, 100, 25);
		  lb2.setForeground(Color.blue);
		  add(lb2);
	      lb3 = new JLabel("Nama");
	      lb3.setBounds(70, 190, 100, 25);
		  lb3.setForeground(Color.blue);
		  add(lb3);
	      lb4 = new JLabel("Alamat");
	      lb4.setBounds(70, 220, 100, 25);
		  lb4.setForeground(Color.blue);
		  add(lb4);
	      tf1 = new JTextField();
	      tf1.setBounds(180, 130, 60, 28);
		  add(tf1);
	      tf2 = new JTextField();
	      tf2.setBounds(180, 160, 160, 28);
          add(tf2);
	      tf3 = new JTextField();
	      tf3.setBounds(180, 190, 400, 28);
          add(tf3);
	      tf4 = new JTextField();
	      tf4.setBounds(180, 220, 400, 28);
		  add(tf4);
          hp = new JButton("Hapus?");
          hp.addActionListener (new ActionListener () {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	Tambah1(tf1.getText());
	    	    }
	    	});
	      hp.setBounds(180, 250, 140, 28);
		  add(hp);
		  try{
				Connection con=DB.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from pasien where p_id=?");
				ps.setString(1, r);
			    ResultSet rs=ps.executeQuery();
			    rs.next();
				tf1.setText(rs.getString(1));
				tf2.setText(rs.getString(2));
				tf3.setText(rs.getString(3));
				tf4.setText(rs.getString(6));			  
				con.close();

		  }catch (Exception e) {e.printStackTrace();}
		  
	      setBounds(2, 10, 1300, 700);
	      addWindowListener(new WindowAdapter() {
	    	  public void windowClosing(WindowEvent e) {
	                Dokter.main(new String[]{});
	                dispose();
	            }
	      });
	      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	  }
	
  	public void Tambah1(String r)
  	{
  			int tanya = JOptionPane.showConfirmDialog (null, "Mau hapus data?",null, JOptionPane.YES_NO_OPTION);
  			if(tanya ==JOptionPane.YES_OPTION)
  			{
  				try{
  					Connection con=DB.getConnection();
  					PreparedStatement ps = con.prepareStatement("delete from pasien where p_id=?");
  					ps.setString(1, r);
  				    ps.executeUpdate();
  				    con.close();
  				}catch(Exception e){System.out.println(e);}
  				JOptionPane.showMessageDialog(this, "Data sudah dihapus!!");
  			}
  		
  	}
}
