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

public class QueryPerson extends JFrame implements ActionListener, ListSelectionListener {

    JPanel upPanel = new JPanel();
    JScrollPane centerPanel = new JScrollPane();
    JPanel downPanel = new JPanel();

    ListSelectionModel listSelectionModel = null;
    JTable table;
    //定义表格的字段
    public JComboBox<Object> comboBox = new JComboBox<>();
    Vector<String[]> departName;
    Vector<String> name;

    String[] colName = {"职工ID","姓名","性别","出生年月","政治面貌",
                "文化程度","部门名称","所学专业","职务","职称","联系电话"};
    //用于存放表格记录的二维数组
    String[][] colValue;
    String[] str = new String[15];

    public JLabel[] jlArray = {//声明标签并为其指定文本
            new JLabel("职工ID"), new JLabel("姓    名"), new JLabel("性    别"),
            new JLabel("出生年月"), new JLabel("政治面貌"), new JLabel("文化程度"),
            new JLabel("所学专业"), new JLabel("工    种"),  new JLabel("职    务"),
            new JLabel("部门名称"), new JLabel("联系电话"), new JLabel("Email"),
            new JLabel("身份证号"), new JLabel("家庭住址"), new JLabel("银行账户")
    };

    public JButton[] jbArray = {
            new JButton("查询"), new JButton("删     除"), new JButton("修     改"),
            new JButton("重置")
    };

    public JTextField[] jtxt = {
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField()
    };

    DataBase db;
    Vector<String[]> vtemp;
    Font font = new Font("Dialog", Font.PLAIN, 12);

    public QueryPerson(){
        this.setLayout(null);
        this.setTitle("查询员工基本信息");
        this.setFont(font);
        this.setSize(900, 700);
        ImageIcon icon = new ImageIcon("src/image/frame.png");
        this.setIconImage(icon.getImage());
        this.setLocation(300, 50);
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
        jlArray[14].setFont(font);
    }

    public  void upPanelInit(){
        upPanel.setLayout(null);
        upPanel.add(jlArray[0]);
        jlArray[0].setBounds(90, 10, 60, 25);
        upPanel.add(jlArray[1]);
        jlArray[1].setBounds(330, 10, 60, 25);
        jtxt[0].setBounds(150, 10, 130, 25);
        jtxt[0].addActionListener(this);
        jtxt[1].setBounds(390, 10, 130, 25);
        jtxt[1].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER){
                    query();
                    getColValue();
                }
            }
        });
        jbArray[0].setBounds(700, 10, 80, 25);
        jbArray[3].setBounds(570, 10, 80, 25);
        jbArray[3].addActionListener(this);
        jbArray[0].setIcon(new ImageIcon("src/image/search.png"));
        jbArray[3].setIcon(new ImageIcon("src/image/reset.png"));
        upPanel.add(jtxt[0]);
        upPanel.add(jtxt[1]);
        upPanel.add(jbArray[0]);
        upPanel.add(jbArray[3]);
        upPanel.setBounds(0, 0, 900, 40);
        this.add(upPanel);
    }

    public void centerPanelInit(){
        this.remove(centerPanel);
        table = new JTable(colValue, colName);
        table.setPreferredScrollableViewportSize(new Dimension(900, 750));
        listSelectionModel = table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(this);
        centerPanel = new JScrollPane(table);
        centerPanel.setBounds(0, 80, 900, 300);
        this.add(centerPanel);
    }

    public  void downPanelInit(){
        downPanel.setLayout(null);
        for(int i = 2; i < jlArray.length; i++){
            downPanel.add(jlArray[i]);
            if(i < 6){
                jlArray[i].setBounds(50+(i-2)*200,10, 60, 25);
                jtxt[i].setBounds(110+(i-2)*200, 10, 130, 25);
                downPanel.add(jtxt[i]);
            }else if(i < 9){
                jlArray[i].setBounds(50+(i-6)*200, 60, 60, 25);
                jtxt[i].setBounds(110+(i-6)*200, 60, 130, 25);
                downPanel.add(jtxt[i]);
            }else if(i == 9){
                jlArray[9].setBounds(650, 60, 60, 25);
            }else if(i < 12){
                jlArray[i].setBounds(50+(i-10)*200, 110, 60, 25);
                jtxt[i-1].setBounds(110+(i-10)*200, 110, 130, 25);
                downPanel.add(jtxt[i-1]);
            }else if(i < 13){
                jlArray[i].setBounds(450, 110, 60, 25);
                jtxt[i-1].setBounds(510, 110, 330, 25);
                downPanel.add(jtxt[i-1]);
            }else{
                jlArray[i].setBounds(50+(i-13)*400, 160, 60, 25);
                jtxt[i-1].setBounds(110+(i-13)*400, 160, 330, 25);
                downPanel.add(jtxt[i-1]);
            }
        }
        jbArray[1].setBounds(230, 210, 100, 25);
        downPanel.add(jbArray[1]);
        jbArray[2].setBounds(560, 210, 100, 25);
        jbArray[1].setIcon(new ImageIcon("src/image/delete.png"));
        jbArray[2].setIcon(new ImageIcon("src/image/edit.png"));
        downPanel.add(jbArray[2]);
        downPanel.setBounds(0, 410, 900, 300);
        comboBox.setBounds(710,60, 130,25);
        comboBox.setFont(font);
        comboBoxInit();
        downPanel.add(comboBox);
        this.add(downPanel);
    }

    public void query(){
        String s = jtxt[0].getText().trim();
        String ename = jtxt[1].getText().trim();
        String sql = "select employee_id,e_name,sex,date,polity,culture," +
                "spec,work_type,duty,telephone,email,id_card," +
                "address,account, depart from emp ";
        if(!s.equals("")){
            int id = Integer.parseInt(s);
            sql = sql + " and employee_id = "+id+"";
        }
        if(!ename.equals("")){
            sql = sql + " and e_name like '%"+ename+"%'";
        }
        sql = sql.replaceFirst("and", "where");
        vtemp = db.getData(sql);
        if(vtemp.isEmpty()){
            JOptionPane.showMessageDialog(this,	"未找到该员工信息！！\n即将显示全部员工信息！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
            vtemp = db.getPerson();
        }
        getColValue();
        centerPanelInit();
    }

    public void getColValue(){
        int row = vtemp.size();
        colValue = new String[row][11];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < 11; j++){
                if(j < 6){
                    colValue[i][j] = vtemp.get(i)[j];
                }else if(j == 6){
                    colValue[i][j] = vtemp.get(i)[14];
                }else{
                    colValue[i][j] = vtemp.get(i)[j-1];
                }

            }
        }
    }

    public void comboBoxInit(){
        departName = db.getDepartName();
        name = new Vector<>();
        name.add(".......");
        for (String[] strings : departName) {
            name.add(strings[1]);
        }
        comboBox.setModel(new DefaultComboBoxModel<>(name.toArray()));
    }

    public void getDepartName(String id){
        for(int i = 0; i < departName.size(); i++) {
            if(departName.get(i)[0].equals(id)){
                comboBox.setSelectedIndex(i + 1);
            }
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
        comboBox.setSelectedIndex(0);
    }

    public void updatePerson(){
        for(int i=0;i<14;i++)
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
            int departINdex = comboBox.getSelectedIndex() - 1;
            String departId = departName.get(departINdex)[0];
            String sql="update emp set e_name='"+str[1]+"',sex='"
                    +str[2]+"',date='"+str[3]+"',polity='"+str[4]+"',culture='"
                    +str[5] +"',spec='"+str[6]+"',work_type='"+str[7]+"',duty='"
                    +str[8] +"',telephone='"+str[9]+"',email='"+str[10]+ "',id_card='"
                    +str[11]+"',address='" +str[12]+"',account='"+str[13]+
                    "',depart='"+departId+"' where employee_id='"+str[0]+"'";
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
            JOptionPane.showMessageDialog(this,	"请先选择需要删除的员工信息！！！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int cd=Integer.parseInt(s);
            sql="delete from emp where employee_id="+cd+"";
            db.updateDb(sql);
            JOptionPane.showMessageDialog(this,	"成功删除该职工信息记录\n请使用查询按钮刷新！！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = table.getSelectedRow();
        for(int i = 0; i < jlArray.length; i++){
            if(i < 9){
                jtxt[i].setText(vtemp.get(row)[i]);
            }else if(i == 9){
                String id = vtemp.get(row)[14];
                getDepartName(id);
            }else{
                jtxt[i-1].setText(vtemp.get(row)[i - 1]);
            }
        }
    }
}
