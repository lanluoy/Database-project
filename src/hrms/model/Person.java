package hrms.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Person extends JFrame implements ActionListener{
    String[] str=new String[15];
    DataBase db;
    String sql;
    Vector<String[]> vtemp;//创建字符串向量
    Vector<String[]> departName;
    Vector<String> name;

    int count=0;
    public JComboBox<Object> comboBox = new JComboBox<>();
    private final JButton[] jbArray=new JButton[]{//声明按钮并为其指定文本
            new JButton("最 前"),new JButton("上一个"),new JButton("下一个"),
            new JButton("最 后"),new JButton("添 加"),new JButton("删 除"),
            new JButton("修 改"),new JButton("重 置")
    };
    private final JTextField[] jtxt={//声明文本框
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField()
    };

    Font font = new Font("Dialog", Font.PLAIN, 14);

    public Person(){
        this.setLayout(null);//设置本窗体为空布局
        setTitle("职工基本信息查询与管理");
        setSize(700, 580);
        setLocation(350, 150);
        ImageIcon icon = new ImageIcon("src/image/frame.png");
        this.setIconImage(icon.getImage());
        this.setFont(font);
        //声明标签并为其指定文本
        JLabel[] jlArray = {//声明标签并为其指定文本
                new JLabel("职工ID"), new JLabel("姓    名"), new JLabel("性    别"),
                new JLabel("出生年月"), new JLabel("政治面貌"), new JLabel("文化程度"),
                new JLabel("所学专业"), new JLabel("工    种"), new JLabel("职    务"),
                new JLabel("联系电话"), new JLabel("Email"), new JLabel("身份证号"),
                new JLabel("家庭住址"), new JLabel("银行账户"), new JLabel("浏览按钮"),
                new JLabel("功能按钮"), new JLabel("部门名称")
        };
        for(int i = 0; i < 17; i++){
            jlArray[i].setFont(font);
            this.add(jlArray[i]);
        }
        for(int i = 0; i<14; i++){//初始化标签和文本框
            this.add(jtxt[i]);
            jtxt[i].setFont(font);
            if(i<3){//设置第一行标签和文本框的大小和位置
                jlArray[i].setBounds(50+i*200,60,60,25);
                jtxt[i].setBounds(110+i*200,60,130,25);
            }
            if(i>=3&&i<6){//设置第二行标签和文本框的大小和位置
                jlArray[i].setBounds(50+(i-3)*200,100,60,25);
                jtxt[i].setBounds(110+(i-3)*200,100,130,25);
            }
            if(i>=6&&i<9){//设置第三行标签和文本框的大小位置
                jlArray[i].setBounds(50+(i-6)*200,140,60,25);
                jtxt[i].setBounds(110+(i-6)*200,140,130,25);
            }
            if(i>=9&&i<11){
                jlArray[i].setBounds(50+(i-8)*200,180,60,25);
                jtxt[i].setBounds(110+(i-8)*200,180,130,25);
            }
            if(i>=11){//设置第七行标签和文本框的大小位置
                jlArray[i].setBounds(50,180+(i-10)*40,60,25);
                jtxt[i].setBounds(110,180+(i-10)*40,530,25);
            }
        }
        jlArray[16].setBounds(50, 180, 60, 25);
        comboBox.setBounds(110,180, 130,25);
        comboBox.setFont(font);
        this.add(comboBox);
        for(int i=0;i<14;i++){jtxt[i].addActionListener(this);}
        //以下是对一些分布比较零散的按钮及文本框所进行的初始化
        jlArray[14].setBounds(50,350,60,25);
        jlArray[15].setBounds(50,420,60,25);
        for(int i=0;i<8;i++){//初始化按钮
            this.add(jbArray[i]);
            jbArray[i].setFont(font);
            jbArray[i].addActionListener(this);//为按钮设置事件监听器
            if(i<4){//设置第一行按钮的大小位置
                jbArray[i].setBounds(100+i*150,380,100,25);
            }
            else{//设置第二行按钮的大小位置
                jbArray[i].setBounds(100+(i-4)*150,450,100,25);
            }
        }
        jbArray[0].setIcon(new ImageIcon("src/image/first.png"));
        jbArray[1].setIcon(new ImageIcon("src/image/up.png"));
        jbArray[2].setIcon(new ImageIcon("src/image/down.png"));
        jbArray[3].setIcon(new ImageIcon("src/image/last.png"));
        jbArray[4].setIcon(new ImageIcon("src/image/add.png"));
        jbArray[5].setIcon(new ImageIcon("src/image/delete.png"));
        jbArray[6].setIcon(new ImageIcon("src/image/edit.png"));
        jbArray[7].setIcon(new ImageIcon("src/image/reset.png"));
        db=new DataBase();
        db.link();
        vtemp = db.getPerson();//调用getPerson方法以获得职工信息
        departName = db.getDepartName();
        this.updateName();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                db.dbClose();
            }
        });
        this.setVisible(true);		//设置窗体的可见性
    }
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource()==jtxt[0]){jtxt[1].requestFocus();}
        if(event.getSource()==jtxt[1]){jtxt[2].requestFocus();}
        if(event.getSource()==jtxt[2]){jtxt[3].requestFocus();}
        if(event.getSource()==jtxt[3]){jtxt[4].requestFocus();}
        if(event.getSource()==jtxt[4]){jtxt[5].requestFocus();}
        if(event.getSource()==jtxt[5]){jtxt[6].requestFocus();}
        if(event.getSource()==jtxt[6]){jtxt[7].requestFocus();}
        if(event.getSource()==jtxt[7]){jtxt[8].requestFocus();}
        if(event.getSource()==jtxt[8]){jtxt[9].requestFocus();}
        if(event.getSource()==jtxt[9]){jtxt[10].requestFocus();}
        if(event.getSource()==jtxt[10]){jtxt[11].requestFocus();}
        if(event.getSource()==jtxt[11]){jtxt[12].requestFocus();}
        if(event.getSource()==jtxt[12]){jtxt[13].requestFocus();}
        if(event.getSource()==jbArray[4])
        {//实现添加职工信息的功能
            this.insertPerson();
        }
        if(event.getSource()==jbArray[5])
        {//实现删除职工信息的功能
            this.deletePerson();
            this.setNull();
        }
        if(event.getSource()==jbArray[6])
        {//实现修改职工信息的功能
            this.updatePerson();
        }
        if(event.getSource()==jbArray[7])
        {//实现"查询"按钮的提示
            this.setNull();
        }
        if(event.getSource()==jbArray[0])
        {//显示数据库中第一条职工信息记录
            this.first();
        }
        if(event.getSource()==jbArray[3])
        {//显示数据库中最后一条职工信息记录
            this.last();
        }
        if(event.getSource()==jbArray[1])
        {//显示当前显示信息的前一条信息记录
            this.before();
        }
        if(event.getSource()==jbArray[2])
        {//显示当前显示信息的下一条信息记录
            this.next();
        }
    }

    public void updateName(){
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

    public void before()
    {
        if(count==0)
        {//当到达数据库第一条信息处，将跳到最后一条信息处
            count=vtemp.size()-1;
        }
        else
        {//否则，到达上一条记录处
            count--;
        }
        String[] str = vtemp.get(count);
        getDepartName(vtemp.get(count)[14]);
        for(int i=0;i<14;i++)
        {//将信息添进窗体的文本框中
            jtxt[i].setText(str[i]);
        }
    }
    public void next()
    {
        if(count==(vtemp.size()-1))
        {//当到达数据库最后一条信息处，将跳到第一条信息处
            count=0;
        }
        else
        {//否则到达下一条记录处
            count++;
        }
        String[] str = vtemp.get(count);
        getDepartName(vtemp.get(count)[14]);
        for(int i=0;i<14;i++)
        {//将从数据库查询的信息添进窗体的文本框中
            jtxt[i].setText(str[i]);
        }
    }
    public void first()
    {//显示数据库中第一条记录
        String[] str = vtemp.get(0);
        getDepartName(vtemp.get(count)[14]);
        for(int i=0;i<14;i++)
        {//显示到文本框
            jtxt[i].setText(str[i]);
        }
        count = 0;
    }
    public void last()
    {//显示数据库最后一条记录
        String[] str = vtemp.get(vtemp.size()-1);
        getDepartName(vtemp.get(count)[14]);
        for(int i=0;i<14;i++)
        {//将信息显示在窗体的文本框中
            jtxt[i].setText(str[i]);
        }
        count = vtemp.size() - 1;
    }

    public void setNull(){
        for(int i = 0; i < 14; i++){
            jtxt[i].setText("");
        }
        comboBox.setSelectedIndex(0);
    }

    public void insertPerson()
    {
        String s=jtxt[0].getText().trim();//得到职工ID
        int departINdex = comboBox.getSelectedIndex() - 1;
        String departId = departName.get(departINdex)[0];
        for(int i=0;i<14;i++)
        {//获得文本框所输入的信息
            str[i]=jtxt[i].getText().trim();
        }
        if(str[0].equals("")||str[1].equals("")||str[2].equals("")||str[3].equals("")
                ||str[4].equals("")||str[5].equals(""))
        {//必要信息输入为空提示
            JOptionPane.showMessageDialog(this,	"职工信息不能为空！！！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {//添加职工信息进数据库
            int cd=Integer.parseInt(s);
            sql="select * from emp where employee_id="+cd+"";//查询该职工信息是否存在
            db.selectDb(sql);
            try
            {
                if(!db.rs.next())
                {//结果集不为空执行删除功能
                    sql="insert into emp(employee_id,e_name,sex,date,polity,culture," +
                            "spec,work_type,duty,telephone,email,id_card," +
                            "address,account,depart) values("+cd+",'"+str[1]+"','"
                            +str[2]+"','"+str[3]+"','"+str[4]+"','"+str[5]+"','"+str[6]+"','"
                            +str[7]+"','"+str[8]+"','"+str[9]+"','"+str[10]+"','"+str[11]+"','"
                            +str[12]+"','"+str[13]+"','"+departId+"')";
                    db.updateDb(sql);
                    JOptionPane.showMessageDialog(this,"添加成功！！","消息!!",
                            JOptionPane.INFORMATION_MESSAGE);//添加成功提示
                    vtemp = db.getPerson();//重新得到联系人信息
                    this.first();
                }
                else
                {//弹出提示对话框
                    JOptionPane.showMessageDialog(this, """
                                    添加失败！！
                                    该员工·已存在
                                    请重新填写员工信息或修改该部门记录""","消息!!",
                            JOptionPane.INFORMATION_MESSAGE);//添加成功提示
                }
            }
            catch(Exception e)
            {//捕获异常
                e.printStackTrace();
            }
        }
    }
    public void deletePerson()
    {
        String s=jtxt[0].getText().trim();//得到职工ID
        for(int i=0;i<14;i++)
        {//获得文本框所输入的信息
            str[i]=jtxt[i].getText().trim();
        }
        if(str[0].equals("")||str[1].equals("")||str[2].equals("")||str[3].equals("")
                ||str[4].equals("")||str[5].equals(""))
        {//未入职工ID
            JOptionPane.showMessageDialog(this,	"职工编号不能为空！！！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int cd=Integer.parseInt(s);
            sql="select * from emp where employee_id="+cd+"";//查询该职工信息是否存在
            db.selectDb(sql);
            try
            {
                if(db.rs.next())
                {//结果集不为空执行删除功能
                    sql="delete from emp where employee_id="+cd+"";
                    db.updateDb(sql);
                    JOptionPane.showMessageDialog(this,	"成功删除该职工信息记录！！",
                            "消息",JOptionPane.INFORMATION_MESSAGE);
                    vtemp = db.getPerson();//重新得到联系人信息

                }
                else
                {//弹出提示对话框
                    JOptionPane.showMessageDialog(this,	"没有该职工！",
                            "消息",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(Exception e)
            {//捕获异常
                e.printStackTrace();
            }
        }
    }
    public void updatePerson()
    {
        String s=jtxt[0].getText().trim();//得到职工ID
        for(int i=0;i<14;i++)
        {//获得文本框所输入的信息
            str[i]=jtxt[i].getText().trim();
        }
        if(str[0].equals("")||str[1].equals("")||str[2].equals("")||str[3].equals("")
                ||str[4].equals("")||str[5].equals(""))
        {//实现职工信息的更新
            JOptionPane.showMessageDialog(this,"请选择需要修改的信息！！",
                    "消息!!",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {//输入为空弹出提示对话框
            int departINdex = comboBox.getSelectedIndex() - 1;
            String departId = departName.get(departINdex)[0];
            int cd=Integer.parseInt(s);
            sql="select * from emp where employee_id="+cd+"";//查询该职工信息是否存在
            db.selectDb(sql);
            try
            {
                if(db.rs.next())
                {//结果集不为空执行删除功能
                    sql="update emp set e_name='"+str[1]+"',sex='"
                            +str[2]+"',date='"+str[3]+"',polity='"+str[4]+"',culture='"
                            +str[5] +"',spec='"+str[6]+"',work_type='"+str[7]+"',duty='"
                            +str[8] +"',telephone='"+str[9]+"',email='"+str[10]+ "',id_card='"
                            +str[11]+"',address='" +str[12]+"',account='"+str[13]+
                            "',depart='"+departId+"' where employee_id='"+str[0]+"'";
                    db.updateDb(sql);
                    JOptionPane.showMessageDialog(this,"修改信息成功！！","消息!!",
                            JOptionPane.INFORMATION_MESSAGE);//修改成功提示
                    vtemp = db.getPerson();
                }
                else
                {//弹出提示对话框
                    JOptionPane.showMessageDialog(this, """
                                    修改失败！！
                                    该员工不存在
                                    请重新填写员工信息或修改该部门记录""","消息!!",
                            JOptionPane.INFORMATION_MESSAGE);//添加成功提示
                }
            }
            catch(Exception e)
            {//捕获异常
                e.printStackTrace();
            }
        }
    }
}