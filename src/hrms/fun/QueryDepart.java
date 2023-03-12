package hrms.fun;

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

public class QueryDepart extends JFrame implements ActionListener, ListSelectionListener {

    JPanel upPanel = new JPanel();
    JScrollPane centerPanel = new JScrollPane();
    JPanel downPanel = new JPanel();

    ListSelectionModel listSelectionModel = null;
    JTable table;
    //定义表格的字段

    String[] colName = {"部门ID","部门名称","负责人","部门人数","联系电话", "部门描述"};
    //用于存放表格记录的二维数组
    String[][] colValue;
    String[] str = new String[6];

    public JLabel[] jlArray = {//声明标签并为其指定文本
            new JLabel("部门ID"), new JLabel("部门名称"), new JLabel("负责人"),
            new JLabel("部门人数"), new JLabel("联系电话"), new JLabel("部门描述"),
    };

    public JButton[] jbArray = {
            new JButton("查询"), new JButton("删     除"), new JButton("修     改"),
            new JButton("重置")
    };

    public JTextField[] jtxt = {
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField()
    };

    DataBase db;
    Vector<String[]> vtemp;
    Font font = new Font("Dialog", Font.PLAIN, 12);

    public QueryDepart(){
        this.setLayout(null);
        this.setTitle("查询部门基本信息");
        this.setFont(font);
        setSize(700, 650);
        ImageIcon icon = new ImageIcon("src/image/frame.png");
        this.setIconImage(icon.getImage());
        setLocation(300, 60);
        db = new DataBase();
        db.link();
        initItem();
        upPanelInit();
        downPanelInit();
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
        jbArray[3].setBounds(470, 10, 80, 25);
        jbArray[0].setIcon(new ImageIcon("src/image/search.png"));
        jbArray[3].setIcon(new ImageIcon("src/image/reset.png"));
        jbArray[3].addActionListener(this);
        upPanel.add(jtxt[0]);
        upPanel.add(jtxt[1]);
        upPanel.add(jbArray[0]);
        upPanel.add(jbArray[3]);
        upPanel.setBounds(0, 30, 700, 40);
        this.add(upPanel);
    }

    public void centerPanelInit(){
        this.remove(centerPanel);
        table = new JTable(colValue, colName);
        table.setPreferredScrollableViewportSize(new Dimension(700, 250));
        listSelectionModel = table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(this);
        centerPanel = new JScrollPane(table);
        centerPanel.setBounds(0, 110, 700, 250);
        this.add(centerPanel);
    }

    public  void downPanelInit(){
        downPanel.setLayout(null);
        for(int i = 2; i < 5; i++){
            downPanel.add(jtxt[i]);
            downPanel.add(jlArray[i]);
            jlArray[i].setBounds(50+(i-2)*200,10,60,25);
            jtxt[i].setBounds(110+(i-2)*200,10,130,25);
        }
        jlArray[5].setBounds(50, 60, 60, 25);
        jtxt[5].setBounds(110, 60, 530, 60);
        downPanel.add(jlArray[5]);
        downPanel.add(jtxt[5]);
        jbArray[1].setBounds(130, 150, 100, 25);
        downPanel.add(jbArray[1]);
        jbArray[2].setBounds(460, 150, 100, 25);
        jbArray[1].setIcon(new ImageIcon("src/image/delete.png"));
        jbArray[2].setIcon(new ImageIcon("src/image/edit.png"));
        downPanel.add(jbArray[2]);
        downPanel.setBounds(0, 400, 700, 190);
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
        colValue = new String[row][11];
        for(int i = 0; i < row; i++){
            System.arraycopy(vtemp.get(i), 0, colValue[i], 0, 6);
        }
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
            deletePerson();
        }
        if(event.getSource() == jbArray[2]){
            updatePerson();
        }
        if(event.getSource() == jbArray[3]){
            setNull();
        }
    }

    public void setNull(){
        for (JTextField jTextField : jtxt) {
            jTextField.setText("");
        }
    }

    public void updatePerson(){
        for(int i=0;i<jtxt.length;i++)
        {//获得文本框所输入的信息
            str[i]=jtxt[i].getText().trim();
        }
        if(str[0].equals("")||str[1].equals("")||str[2].equals("")||str[3].equals("")
                ||str[4].equals("")||str[5].equals(""))
        {//实现职工信息的更新
            JOptionPane.showMessageDialog(this,"请查询需要修改的信息！！",
                    "消息!!",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {//输入为空弹出提示对话框
            String sql="update dept set d_name='"+str[1]+"',manager='"
                    +str[2]+"',telephone='"+str[4]+"',depart_desc='"
                    +str[5] +"' where depart_id='"+str[0]+"'";
            db.updateDb(sql);
            JOptionPane.showMessageDialog(this,"修改信息成功！！\n请使用查询按钮查询","消息!!",
                    JOptionPane.INFORMATION_MESSAGE);//修改成功提示
        }
    }

    public void deletePerson(){
        String sql;
        String s=jtxt[0].getText().trim();//得到职工ID
        for(int i=0;i< jtxt.length;i++)
        {//获得文本框所输入的信息
            str[i]=jtxt[i].getText().trim();
        }
        if(str[0].equals("")||str[1].equals("")||str[2].equals("")||str[3].equals("")
                ||str[4].equals("")||str[5].equals(""))
        {//未入职工ID
            JOptionPane.showMessageDialog(this,	"请先选择需要删除的部门信息！！！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int number = Integer.parseInt(jtxt[3].getText().trim());
            if(number > 0){
                JOptionPane.showMessageDialog(this, """
                                        删除失败！！
                                        该部门下存在职工！！
                                        请先删除该部门的职工！！""",
                        "消息",JOptionPane.INFORMATION_MESSAGE);
            }else{
                int cd=Integer.parseInt(s);
                sql="delete from dept where depart_id="+cd+"";
                db.updateDb(sql);
                JOptionPane.showMessageDialog(this,	"成功删除该部门信息记录\n请使用查询按钮刷新！！",
                        "消息",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = table.getSelectedRow();
        for(int i = 0; i < 6; i++){
            jtxt[i].setText(vtemp.get(row)[i]);
        }
    }
}
