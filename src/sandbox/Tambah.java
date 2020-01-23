package sandbox;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Tambah extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9;
	private JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
	private JButton sp;
	private JComboBox<String> cb;
	
	Tambah()
	{
	      super("Tambah Pasien");
	      setLayout(null);
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
	      tf7.setBounds(180, 340, 500, 28);
          add(tf7);
	      tf8 = new JTextField();
	      tf8.setBounds(180, 370, 500, 28);
		  add(tf8);

          sp = new JButton("Simpan");
          sp.addActionListener(new ActionListener () {
	    	    public void actionPerformed(ActionEvent e) {
	    	    	Simpan1();
	    	    }
	    	});
	      sp.setBounds(180, 400, 140, 28);
		  add(sp);
	      setBounds(2, 10, 1300,700);
	      addWindowListener(new WindowAdapter() {
	    	  public void windowClosing(WindowEvent e) {
	                Dokter.main(new String[]{});
	                dispose();
	            }
	      });
	      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public void Simpan1()
	{
		String combo = (String)cb.getSelectedItem();
		if(combo.equals("Wanita"))
		{
			combo = "1";
		}else{
			combo="2";
		}
		int dialogButton = JOptionPane.showConfirmDialog (null, "Sudah benar?",null,JOptionPane.YES_NO_OPTION);
		if (dialogButton == JOptionPane.YES_OPTION) {
			if(tf1.getText().equals("")||tf2.getText().equals("")||tf5.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Ada yang belum diisi!!");
			}else{
				Connection con = DB.getConnection();
				try{
				    PreparedStatement ps = con.prepareStatement("insert into pasien(nomor,nama,umur,sex,alamat,phone,alergi,riwayat,keterangan)values(?,?,?,?,?,?,?,?,?)");
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
				JOptionPane.showMessageDialog(null, "Sudah disimpan!!");
			}
		}
	}
}
