package hrms.add;

import hrms.model.DataBase;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class DepartInformation extends JFrame implements ActionListener, ListSelectionListener {

    JPanel upPanel = new JPanel();
    JScrollPane centerPanel = new JScrollPane();
    JScrollPane downPanel = new JScrollPane();

    ListSelectionModel listSelectionModel = null;
    JTable table1;
    JTable table2;
    String id;
    //定义表格的字段

    String[] colName1 = {"部门ID","部门名称","负责人","部门人数","联系电话", "部门描述"};
    String[] colName2 = {"职  称", "人数"};
    //用于存放表格记录的二维数组
    String[][] colValue1;
    String[][] colValue2;

    public JLabel[] jlArray = {//声明标签并为其指定文本
            new JLabel("部门ID"), new JLabel("部门名称")
    };

    public JButton[] jbArray = {
            new JButton("查询"), new JButton("重置")
    };

    public JTextField[] jtxt = {
            new JTextField(),new JTextField()
    };

    DataBase db;
    Vector<String[]> vtemp;
    Vector<String[]> vtemp2;
    Font font = new Font("Dialog", Font.PLAIN, 12);

    public DepartInformation(){
        this.setLayout(null);
        this.setTitle("查询部门职称信息");
        this.setFont(font);
        setSize(700, 650);
        ImageIcon icon = new ImageIcon("src/image/frame.png");
        this.setIconImage(icon.getImage());
        setLocation(300, 60);
        db = new DataBase();
        db.link();
        initItem();
        upPanelInit();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                db.dbClose();
            }
        });
        this.setVisible(true);
    }

    public void initItem(){
        for(int i = 0; i < jtxt.length; i++){
            jlArray[i].setFont(font);
            jtxt[i].setFont(font);
        }
        for (JButton jButton : jbArray) {
            jButton.setFont(font);
            jButton.addActionListener(this);
        }
    }

    public  void upPanelInit(){
        upPanel.setLayout(null);
        upPanel.add(jlArray[0]);
        jlArray[0].setBounds(50, 10, 60, 25);
        upPanel.add(jlArray[1]);
        jlArray[1].setBounds(250, 10, 60, 25);
        jtxt[0].setBounds(110, 10, 130, 25);
        jtxt[0].addActionListener(this);
        jtxt[1].setBounds(310, 10, 130, 25);
        jtxt[1].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER){
                    query();
                    getColValue();
                }
            }
        });
        jbArray[0].setBounds(580, 10, 80, 25);
        jbArray[1].setBounds(470, 10, 80, 25);
        jbArray[0].setIcon(new ImageIcon("src/image/search.png"));
        jbArray[1].setIcon(new ImageIcon("src/image/reset.png"));
        jbArray[1].addActionListener(this);
        upPanel.add(jtxt[0]);
        upPanel.add(jtxt[1]);
        upPanel.add(jbArray[0]);
        upPanel.add(jbArray[1]);
        upPanel.setBounds(0, 30, 700, 40);
        this.add(upPanel);
    }

    public void centerPanelInit(){
        this.remove(centerPanel);
        table1 = new JTable(colValue1, colName1);
        table1.setPreferredScrollableViewportSize(new Dimension(700, 250));
        listSelectionModel = table1.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(this);
        centerPanel = new JScrollPane(table1);
        centerPanel.setBounds(0, 110, 700, 250);
        this.add(centerPanel);
    }

    public  void downPanelInit(){
        this.remove(downPanel);
        table2 = new JTable(colValue2, colName2);
        table2.setPreferredScrollableViewportSize(new Dimension(700, 250));
        listSelectionModel = table2.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        downPanel = new JScrollPane(table2);
        downPanel.setBounds(0, 400, 700, 250);
        this.add(downPanel);
    }

    public void query(){
        String s = jtxt[0].getText().trim();
        String ename = jtxt[1].getText().trim();
        String sql = "select depart_id,d_name,manager,"+
                "employee_number,telephone,depart_desc from dept";
        if(!s.equals("")){
            int id = Integer.parseInt(s);
            sql = sql + " and depart_id = "+id+"";
        }
        if(!ename.equals("")){
            sql = sql + " and d_name like '%"+ename+"%'";
        }
        sql = sql.replaceFirst("and", "where");
        vtemp = db.getData(sql);
        if(vtemp.isEmpty()){
            JOptionPane.showMessageDialog(this,	"未找到该部门信息！！\n即将显示全部部门信息！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
            vtemp = db.getDepart();
        }
        getColValue();
        centerPanelInit();
    }

    public void getColValue(){
        int row = vtemp.size();
        colValue1 = new String[row][11];
        for(int i = 0; i < row; i++){
            System.arraycopy(vtemp.get(i), 0, colValue1[i], 0, 6);
        }
    }

    public void getInformation(){
        String sql = "select work_type ,count(work_type)" +
                "from emp where depart ='" + id +"'group by work_type";
        vtemp2 = db.getData(sql);
        if(vtemp2.isEmpty()){
            colValue2 = new String[][]{{"无", "0"}};
        }
        else{
            int row = vtemp2.size();
            colValue2 = new String[row][2];
            for(int  i = 0; i < row; i++){
                System.arraycopy(vtemp2.get(i), 0, colValue2[i], 0, 2);
            }
        }
        downPanelInit();
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == jtxt[0]){
            jtxt[1].requestFocus();
        }
        if(event.getSource() == jbArray[0]){
            query();
            getColValue();
        }
        if(event.getSource() == jbArray[1]){
            setNull();
        }
    }

    public void setNull(){
        for (JTextField jTextField : jtxt) {
            jTextField.setText("");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = table1.getSelectedRow();
        id = vtemp.get(row)[0];
        getInformation();
    }
}
