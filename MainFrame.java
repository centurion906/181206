import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class MainFrame extends JFrame {
    Container cp;
    Container jifcp;
    private JScrollPane jsp;
    private JPanel jpnt;
    private JButton jbtnConnDB = new JButton("Conn. DB");
    private JButton jbtnShowDate = new JButton("Show Data");
    private JButton jbtnExit = new JButton("Exit");
    private JDesktopPane jdp = new JDesktopPane();
    private DBConnection dbconn;
    private JInternalFrame jif1;
    private ResultSet rs;
    private ResultSetMetaData meta;
    private JTextArea jta = new JTextArea();
    public MainFrame(){
        ex();
    }private void ex(){
        cp = getContentPane();
        setBounds(200,200,600,600);
        cp.setLayout(new BorderLayout(3,3));
        jpnt = new JPanel(new GridLayout(1,4,3,3));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp.add(jpnt, BorderLayout.NORTH);
        cp.add(jdp, BorderLayout.CENTER);
        jpnt.add(jbtnConnDB);
        jbtnConnDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dbconnection();
                if (dbconn!=null){
                    jif1 = new JInternalFrame("DB Connected");
                    jifcp = jif1.getContentPane();
                    jif1.setBounds(0,0,400,300);
                }
            }
        });
        jpnt.add(jbtnShowDate);
        jbtnShowDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showData();
            }
        });
        jpnt.add(jbtnExit);
        jbtnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private void dbconnection(){
        dbconn = new DBConnection(this,"s106021050","1SfeNtmE");
    }
    private void showData(){
        try{
            rs = dbconn.getData();
            int colCount = 0;
            if (rs!=null){
                jsp = new JScrollPane(jta);
                meta = rs.getMetaData();
                colCount = meta.getColumnCount();
                while (rs.next()){
                    String[] record = new String[colCount];
                    for (int i = 0;i<colCount;i++){
                        record[i] = rs.getString(i+1);
                        jta.append(record[i]+"\t");
                    }
                    jta.append("\n");
                }
                jifcp.add(jsp);
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}
