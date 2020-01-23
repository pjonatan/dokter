package sandbox;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Periksa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9, lba, lbb, lbc;
	private JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tfa, tfb;
	private JButton sp, ub;
	private JComboBox<String> cb;
	String pid=null;
	
	Periksa(String r)
	{
	      super("Periksa Pasien");
	      setLayout(null);
	      pid=r;
	      lb1 = new JLabel("Rekam Medis");
	      lb1.setBounds(70, 130, 100, 25);
		  lb1.setForeground(Color.blue);
		  add(lb1);
	      lb2 = new JLabel("Nama");
	      lb2.setBounds(70, 160, 100, 25);
		  lb2.setForeground(Color.blue);
		  add(lb2);
	      lb3 = new JLabel("Umur");
	      lb3.setBounds(70, 190, 100, 25);
		  lb3.setForeground(Color.blue);
		  add(lb3);
	      lb4 = new JLabel("Sex");
	      lb4.setBounds(70, 220, 100, 25);
		  lb4.setForeground(Color.blue);
		  add(lb4);
	      lb5 = new JLabel("Alamat");
	      lb5.setBounds(70, 250, 100, 25);
		  lb5.setForeground(Color.blue);
		  add(lb5);
	      lb6 = new JLabel("Phone");
	      lb6.setBounds(70, 280, 100, 25);
		  lb6.setForeground(Color.blue);
		  add(lb6);
	      lb7 = new JLabel("Alergi");
	      lb7.setBounds(70, 310, 100, 25);
		  lb7.setForeground(Color.blue);
		  add(lb7);
	      lb8 = new JLabel("Riwayat");
	      lb8.setBounds(70, 340, 100, 25);
		  lb8.setForeground(Color.blue);
		  add(lb8);
	      lb9 = new JLabel("Keterangan");
	      lb9.setBounds(70, 370, 100, 25);
		  lb9.setForeground(Color.blue);
		  add(lb9);
	      lba = new JLabel("Keluhan");
	      lba.setBounds(70, 430, 100, 25);
		  lba.setForeground(Color.blue);
		  add(lba);
	      lbb = new JLabel("Diagnosa");
	      lbb.setBounds(70, 460, 100, 25);
		  lbb.setForeground(Color.blue);
		  add(lbb);
	      lbc = new JLabel("Perawatan");
	      lbc.setBounds(70, 490, 100, 25);
		  lbc.setForeground(Color.blue);
		  add(lbc);
		  
	      tf1 = new JTextField();
	      tf1.setBounds(180, 130, 150, 28);
		  add(tf1);
	      tf2 = new JTextField();
	      tf2.setBounds(180, 160, 400, 28);
          add(tf2);
	      tf3 = new JTextField();
	      tf3.setBounds(180, 190, 60, 28);
          add(tf3);
		  cb = new JComboBox<String>();
		  cb.setBounds(180, 220, 100, 25);
		  cb.addItem("Wanita");
		  cb.addItem("Laki2");
		  add(cb);          
	      tf4 = new JTextField();
	      tf4.setBounds(180, 250, 500, 28);
		  add(tf4);
	      tf5 = new JTextField();
	      tf5.setBounds(180, 280, 180, 28);
		  add(tf5);
	      tf6 = new JTextField();
	      tf6.setBounds(180, 310, 400, 28);
          add(tf6);
	      tf7 = new JTextField();
	      tf7.setBounds(180, 340, 600, 28);
          add(tf7);
	      tf8 = new JTextField();
	      tf8.setBounds(180, 370, 600, 28);
		  add(tf8);
          ub = new JButton("Ubah data pasien");
          ub.addActionListener(new ActionListener () {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	Ubah1(pid);
	    	    }
	    	});
          ub.setForeground(Color.red);
	      ub.setBounds(180, 400, 250, 28);
		  add(ub);
	      tf9 = new JTextField();
	      tf9.setBounds(180, 430, 600, 28);
          add(tf9);
	      tfa = new JTextField();
	      tfa.setBounds(180, 460, 600, 28);
          add(tfa);
	      tfb = new JTextField();
	      tfb.setBounds(180, 490, 600, 28);
		  add(tfb);
          sp = new JButton("Simpan data pemeriksaan");
          sp.addActionListener(new ActionListener () {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	Simpan1();
	    	    }
	    	});
          sp.setForeground(Color.magenta);
	      sp.setBounds(180, 520, 300, 28);
		  add(sp);
		  
		  try{
				Connection con=DB.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from pasien where p_id=?");
				ps.setString(1, pid);
			    ResultSet rs=ps.executeQuery();
			    rs.next();
				tf1.setText(rs.getString(2));
				tf2.setText(rs.getString(3));
				tf3.setText(rs.getString(4));
				if(rs.getString(5).equals("1"))
				{
					cb.setSelectedIndex(0);
				}else{
					cb.setSelectedIndex(1);
				}
				tf4.setText(rs.getString(6));			  
				tf5.setText(rs.getString(7));
				tf6.setText(rs.getString(8));
				tf7.setText(rs.getString(9));
				tf8.setText(rs.getString(10));			  
				con.close();

		  }catch (Exception e) {e.printStackTrace();}		  
	      setBounds(2, 10, 1300,700);
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
			int tanya = JOptionPane.showConfirmDialog (null, "Mau ubah data?",null, JOptionPane.YES_NO_OPTION);
			if(tanya ==JOptionPane.YES_OPTION)
			{
				String combo = (String)cb.getSelectedItem();
				if(combo.equals("Wanita"))
				{
					combo = "1";
				}else{
					combo="2";
				}
				try{
					Connection con=DB.getConnection();
					PreparedStatement ps = con.prepareStatement("update pasien set nomor=?,nama=?,umur=?,sex=?,alamat=?,phone=?,alergi=?,riwayat=?,keterangan=? where p_id=" + r);
					ps.setString(1, tf1.getText());
					ps.setString(2, tf2.getText());
					ps.setString(3, tf3.getText());
					ps.setString(4, combo);
					ps.setString(5, tf4.getText());
					ps.setString(6, tf5.getText());
					ps.setString(7, tf6.getText());
					ps.setString(8, tf7.getText());
					ps.setString(9, tf8.getText());  
				    ps.executeUpdate();
				    con.close();
				}catch(Exception e){System.out.println(e);}
				JOptionPane.showMessageDialog(this, "Data sudah diubah!!");
			}	
	}
	
	public void Simpan1()
	{
		Date date = new Date();
		SimpleDateFormat ft = 
		new SimpleDateFormat ("dd-MM-yyyy");
		String hari = ft.format(date).toString();
		int dialogButton = JOptionPane.showConfirmDialog (null, "Mau simpan history?",null,JOptionPane.YES_NO_OPTION);
		if (dialogButton == JOptionPane.YES_OPTION) {
			Connection con = DB.getConnection();
			try{
			    PreparedStatement ps = con.prepareStatement("insert into history(p_id,keluhan,diagnos,pengobatan,tanggal)values(?,?,?,?,?)");
			    ps.setString(1, pid);
			    ps.setString(2, tf9.getText());
			    ps.setString(3, tfa.getText());
			    ps.setString(4, tfb.getText());
			    ps.setString(5, hari);
			    ps.executeUpdate();
				    con.close();
				}catch(Exception e){System.out.println(e);}
				JOptionPane.showMessageDialog(null, "Sudah disimpan!!");
			}
	}

}
