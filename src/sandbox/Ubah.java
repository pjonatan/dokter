package sandbox;

import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class Ubah extends JFrame
{
    private static final long serialVersionUID = 1L;
    private JLabel lb1;
    private JLabel lb2;
    private JLabel lb3;
    private JLabel lb4;
    private JLabel lb5;
    private JLabel lb6;
    private JLabel lb7;
    private JLabel lb8;
    private JLabel lb9;
    private JTextField tf1;
    private JTextField tf2;
    private JTextField tf3;
    private JTextField tf4;
    private JTextField tf5;
    private JTextField tf6;
    private JTextField tf7;
    private JTextField tf8;
    private JButton ub;
    private JComboBox<String> cb;
    private String pid;
    
    public Ubah(final String r) {
        this.pid = null;
        this.setTitle("Ubah data pasien");
        this.setLayout(null);
        this.pid = r;
        (this.lb1 = new JLabel("Rekam Medis")).setBounds(70, 130, 100, 25);
        this.lb1.setForeground(Color.blue);
        this.add(this.lb1);
        (this.lb2 = new JLabel("Nama")).setBounds(70, 160, 100, 25);
        this.lb2.setForeground(Color.blue);
        this.add(this.lb2);
        (this.lb3 = new JLabel("Umur")).setBounds(70, 190, 100, 25);
        this.lb3.setForeground(Color.blue);
        this.add(this.lb3);
        (this.lb4 = new JLabel("Sex")).setBounds(70, 220, 100, 25);
        this.lb4.setForeground(Color.blue);
        this.add(this.lb4);
        (this.lb5 = new JLabel("Alamat")).setBounds(70, 250, 100, 25);
        this.lb5.setForeground(Color.blue);
        this.add(this.lb5);
        (this.lb6 = new JLabel("Phone")).setBounds(70, 280, 100, 25);
        this.lb6.setForeground(Color.blue);
        this.add(this.lb6);
        (this.lb7 = new JLabel("Alergi")).setBounds(70, 310, 100, 25);
        this.lb7.setForeground(Color.blue);
        this.add(this.lb7);
        (this.lb8 = new JLabel("Riwayat")).setBounds(70, 340, 100, 25);
        this.lb8.setForeground(Color.blue);
        this.add(this.lb8);
        (this.lb9 = new JLabel("Keterangan")).setBounds(70, 370, 100, 25);
        this.lb9.setForeground(Color.blue);
        this.add(this.lb9);
        (this.tf1 = new JTextField()).setBounds(180, 130, 150, 28);
        this.add(this.tf1);
        (this.tf2 = new JTextField()).setBounds(180, 160, 300, 28);
        this.add(this.tf2);
        (this.tf3 = new JTextField()).setBounds(180, 190, 60, 28);
        this.add(this.tf3);
        (this.cb = new JComboBox<String>()).setBounds(180, 220, 100, 25);
        this.cb.addItem("Wanita");
        this.cb.addItem("Laki2");
        this.add(this.cb);
        (this.tf4 = new JTextField()).setBounds(180, 250, 400, 28);
        this.add(this.tf4);
        (this.tf5 = new JTextField()).setBounds(180, 280, 180, 28);
        this.add(this.tf5);
        (this.tf6 = new JTextField()).setBounds(180, 310, 400, 28);
        this.add(this.tf6);
        (this.tf7 = new JTextField()).setBounds(180, 340, 500, 28);
        this.add(this.tf7);
        (this.tf8 = new JTextField()).setBounds(180, 370, 500, 28);
        this.add(this.tf8);
        (this.ub = new JButton("Ubah?")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                Ubah.this.Ubah1(Ubah.this.pid);
            }
        });
        this.ub.setBounds(180, 400, 80, 28);
        this.add(this.ub);
        try {
            final Connection con = DB.getConnection();
            final PreparedStatement ps = con.prepareStatement("select * from pasien where p_id=?");
            ps.setString(1, this.pid);
            final ResultSet rs = ps.executeQuery();
            rs.next();
            this.tf1.setText(rs.getString(2));
            this.tf2.setText(rs.getString(3));
            this.tf3.setText(rs.getString(4));
            if (rs.getString(5).equals("1")) {
                this.cb.setSelectedIndex(0);
            }
            else {
                this.cb.setSelectedIndex(1);
            }
            this.tf4.setText(rs.getString(6));
            this.tf5.setText(rs.getString(7));
            this.tf6.setText(rs.getString(8));
            this.tf7.setText(rs.getString(9));
            this.tf8.setText(rs.getString(10));
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.setBounds(2, 10, 1300, 700);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                Dokter.main(new String[0]);
                Ubah.this.dispose();
            }
        });
        this.setDefaultCloseOperation(0);
    }
    
    public void Ubah1(final String r) {
        final int tanya = JOptionPane.showConfirmDialog(null, "Mau ubah data?", null, 0);
        if (tanya == 0) {
            String combo = (String)this.cb.getSelectedItem();
            if (combo.equals("Wanita")) {
                combo = "1";
            }
            else {
                combo = "2";
            }
            try {
                final Connection con = DB.getConnection();
                final PreparedStatement ps = con.prepareStatement("update pasien set nomor=?,nama=?,umur=?,sex=?,alamat=?,phone=?,alergi=?,riwayat=?,keterangan=? where p_id=" + r);
                ps.setString(1, this.tf1.getText());
                ps.setString(2, this.tf2.getText());
                ps.setString(3, this.tf3.getText());
                ps.setString(4, combo);
                ps.setString(5, this.tf4.getText());
                ps.setString(6, this.tf5.getText());
                ps.setString(7, this.tf6.getText());
                ps.setString(8, this.tf7.getText());
                ps.setString(9, this.tf8.getText());
                ps.executeUpdate();
                con.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }
            JOptionPane.showMessageDialog(this, "Data sudah diubah!!");
        }
    }
}
