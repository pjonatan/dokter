package sandbox;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Ubhis extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9, lba, lbb, lbc;
	private JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tfa, tfb;
	private JButton ub;
	private JComboBox<String> cb;
	private String hid=null;
	
	public Ubhis(String r)
	{
		setTitle("Ubah history");
		setLayout(null);
		hid = r;
	      lb1 = new JLabel("Nomor");
	      lb1.setBounds(70, 130, 100, 25);
		  lb1.setForeground(Color.blue);
		  add(lb1);
	      lb2 = new JLabel("Rekam Medis");
	      lb2.setBounds(70, 160, 160, 25);
		  lb2.setForeground(Color.blue);
		  add(lb2);
	      lb3 = new JLabel("Nama");
	      lb3.setBounds(70, 190, 100, 25);
		  lb3.setForeground(Color.blue);
		  add(lb3);
	      lb4 = new JLabel("Umur");
	      lb4.setBounds(70, 220, 100, 25);
		  lb4.setForeground(Color.blue);
		  add(lb4);
	      lb5 = new JLabel("Sex");
	      lb5.setBounds(70, 250, 100, 25);
		  lb5.setForeground(Color.blue);
		  add(lb5);
	      lb6 = new JLabel("Alergi");
	      lb6.setBounds(70, 280, 100, 25);
		  lb6.setForeground(Color.blue);
		  add(lb6);
	      lb7 = new JLabel("Riwayat");
	      lb7.setBounds(70, 310, 100, 25);
		  lb7.setForeground(Color.blue);
		  add(lb7);
	      lb8 = new JLabel("Keterangan");
	      lb8.setBounds(70, 340, 100, 25);
		  lb8.setForeground(Color.blue);
		  add(lb8);
	      lb9 = new JLabel("Keluhan");
	      lb9.setBounds(70, 370, 100, 25);
		  lb9.setForeground(Color.blue);
		  add(lb9);
	      lba = new JLabel("Diagnosa");
	      lba.setBounds(70, 400, 100, 25);
		  lba.setForeground(Color.blue);
		  add(lba);
	      lbb = new JLabel("Perawatan");
	      lbb.setBounds(70, 430, 100, 25);
		  lbb.setForeground(Color.blue);
		  add(lbb);
	      lbc = new JLabel("Tanggal");
	      lbc.setBounds(70, 460, 100, 25);
		  lbc.setForeground(Color.blue);
		  add(lbc);		  
	      tf1 = new JTextField();
	      tf1.setBounds(180, 130, 100, 28);
		  add(tf1);
	      tf2 = new JTextField();
	      tf2.setBounds(180, 160, 150, 28);
		  add(tf2);
	      tf3 = new JTextField();
	      tf3.setBounds(180, 190, 400, 28);
        add(tf3);
	      tf4 = new JTextField();
	      tf4.setBounds(180, 220, 60, 28);
        add(tf4);
		  cb = new JComboBox<String>();
		  cb.setBounds(180, 250, 100, 25);
		  cb.addItem("Wanita");
		  cb.addItem("Laki2");
		  add(cb);          
	      tf5 = new JTextField();
	      tf5.setBounds(180, 280, 400, 28);
		  add(tf5);
	      tf6 = new JTextField();
	      tf6.setBounds(180, 310, 600, 28);
        add(tf6);
	      tf7 = new JTextField();
	      tf7.setBounds(180, 340, 600, 28);
		  add(tf7);
	      tf8 = new JTextField();
	      tf8.setBounds(180, 370, 600, 28);
        add(tf8);
	      tf9 = new JTextField();
	      tf9.setBounds(180, 400, 600, 28);
          add(tf9);
	      tfa = new JTextField();
	      tfa.setBounds(180, 430, 600, 28);
		  add(tfa);
	      tfb = new JTextField();
	      tfb.setBounds(180, 460, 100, 28);
		  add(tfb);
	      ub = new JButton("Ubah history");
	      ub.addActionListener(new ActionListener () {
	   	    public void actionPerformed(ActionEvent e) {
	    	    	Ubah1(hid);
	    	    }
	      });
	      ub.setForeground(Color.red);
		  ub.setBounds(180, 490, 200, 28);
		  add(ub);
		  
		  try{
				Connection con=DB.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from history where h_id=?");
				ps.setString(1, hid);
			    ResultSet rs=ps.executeQuery();
			    rs.next();
				tf1.setText(rs.getString(1));
				tf1.setEnabled(false);
				tf8.setText(rs.getString(3));
				tf9.setText(rs.getString(4));
				tfa.setText(rs.getString(5));
				tfb.setText(rs.getString(6));
				tfb.setEnabled(false);
				PreparedStatement pst = con.prepareStatement("select * from pasien where p_id=?");
				pst.setString(1,rs.getString(2));
				ResultSet rst=pst.executeQuery();
				tf2.setText(rst.getString(2));
				tf2.setEnabled(false);
				tf3.setText(rst.getString(3));
				tf3.setEnabled(false);
				tf4.setText(rst.getString(4));
				tf4.setEnabled(false);
				if(rst.getString(5).equals("1"))
				{
					cb.setSelectedIndex(0);
				}else{
					cb.setSelectedIndex(1);
				}
				cb.setEnabled(false);
				tf5.setText(rst.getString(8));
				tf5.setEnabled(false);
				tf6.setText(rst.getString(9));
				tf6.setEnabled(false);
				tf7.setText(rst.getString(10));
				tf7.setEnabled(false);
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
	
  	public void Ubah1(String r)
  	{
  			int tanya = JOptionPane.showConfirmDialog (null, "Mau ubah history?",null, JOptionPane.YES_NO_OPTION);
  			if(tanya ==JOptionPane.YES_OPTION)
  			{
  				try{
  					Connection con=DB.getConnection();
  					PreparedStatement ps = con.prepareStatement("update history set keluhan=?,diagnos=?,pengobatan=? where h_id=" + r);
  					ps.setString(1, tf8.getText());
  					ps.setString(2, tf9.getText());
  					ps.setString(3, tfa.getText());
  				    ps.executeUpdate();
  				    con.close();
  				}catch(Exception e){System.out.println(e);}
  				JOptionPane.showMessageDialog(this, "History sudah diubah!!");
  			}
  	}
}
