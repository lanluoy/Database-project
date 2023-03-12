package hrms.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class WorkHistory extends JFrame implements ActionListener {
    String[] str = new String[7];
    DataBase db;
    String sql;
    int id;
    Vector<String[]> vtemp;
    int count = 0;
    private final JButton[] jbArray = {
            new JButton("最 前"), new JButton("上一个"),
            new JButton("下一个"), new JButton("最 后"),
            new JButton("添 加"), new JButton("删 除"),
            new JButton("修 改"), new JButton("重 置")
    };
    private final JTextField[] jtxt = {
            new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField()
    };

    Font font = new Font("Dialog", Font.PLAIN, 14);

    public WorkHistory(){
        this.setLayout(null);
        setTitle("职工任职记录信息查询与管理");
        setSize(700, 400);
        setLocation(300, 150);
        ImageIcon icon = new ImageIcon("src/image/frame.png");
        this.setIconImage(icon.getImage());
        this.setFont(font);
        JLabel[] jlArray = {
                new JLabel("员工ID"), new JLabel("员工姓名"),
                new JLabel("担任职务"), new JLabel("起止时间"),
                new JLabel("所属部门"), new JLabel("工  资"),
                new JLabel("浏览按钮"), new JLabel("功能按钮")
        };
        for(int i = 0; i < 8; i++){
            this.add(jlArray[i]);
            jlArray[i].setFont(font);
        }
        for(int i = 0; i < 6; i++){
            this.add(jtxt[i]);
            jtxt[i].setFont(font);
            if(i < 3){//设置第一行标签和文本框的大小和位置
                jlArray[i].setBounds(50+i*200,60,60,35);
                jtxt[i].setBounds(110+i*200,60,130,35);
            }
            if(i>=3){//设置第二行标签和文本框的大小和位置
                jlArray[i].setBounds(50+(i-3)*200,140,60,35);
                jtxt[i].setBounds(110+(i-3)*200,140,130,35);
            }
        }
        for(int i = 0; i < 6; i++){
            jtxt[i].addActionListener(this);
        }
        jlArray[6].setBounds(50, 210, 60, 25);
        jlArray[7].setBounds(50, 280, 60, 25);
        for(int i = 0; i < 8; i++){
            this.add(jbArray[i]);
            jbArray[i].setFont(font);
            jbArray[i].addActionListener(this);
            if(i < 4){
                jbArray[i].setBounds(100+i*150, 240, 100, 25);
            }
            else{
                jbArray[i].setBounds(100+(i-4)*150, 310, 100, 25);
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
        db = new DataBase();
        db.link();
        vtemp = db.getWorkHistory();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                db.dbClose();
            }
        });
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource()==jtxt[0]){jtxt[1].requestFocus();}
        if(event.getSource()==jtxt[1]){jtxt[2].requestFocus();}
        if(event.getSource()==jtxt[2]){jtxt[3].requestFocus();}
        if(event.getSource()==jtxt[3]){jtxt[4].requestFocus();}
        if(event.getSource()==jtxt[4]){jtxt[5].requestFocus();}
        if(event.getSource()==jbArray[4])
        {//实现添加职工信息的功能
            this.insertWorkHistory();
        }
        if(event.getSource()==jbArray[5])
        {//实现删除职工信息的功能
            this.deleteWorkHistory();
            this.setNull();
        }
        if(event.getSource()==jbArray[6])
        {//实现修改职工信息的功能
            this.updateWorkHistory();
        }
        if(event.getSource()==jbArray[7])
        {//实现"重置"按钮的提示
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
    public void before()
    {
        if(count==0)
        {//当到达数据库第一条信息处，将跳到最后一条信息处
            count=vtemp.size()-1;
        }
        else
        {//否则，到达上一条记录处
            count = count - 1;
        }
        String[] str = vtemp.get(count);
        id = Integer.parseInt(str[6]);
        for(int i=0;i<6;i++)
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
        id = Integer.parseInt(str[6]);
        for(int i=0;i<6;i++)
        {//将从数据库查询的信息添进窗体的文本框中
            jtxt[i].setText(str[i]);
        }
    }
    public void first()
    {//显示数据库中第一条记录
        String[] str = vtemp.get(0);
        id = Integer.parseInt(str[6]);
        for(int i=0;i<6;i++)
        {//显示到文本框
            jtxt[i].setText(str[i]);
        }
        count = 0;
    }
    public void last()
    {//显示数据库最后一条记录
        String[] str = vtemp.get(vtemp.size()-1);
        id = Integer.parseInt(str[6]);
        for(int i=0;i<6;i++)
        {//将信息显示在窗体的文本框中
            jtxt[i].setText(str[i]);
        }
        count = vtemp.size() - 1;
    }

    public void setNull(){
        for(int i = 0; i < 6; i++){
            jtxt[i].setText("");
        }
    }

    public void insertWorkHistory()
    {
        for(int i=0;i<6;i++)
        {//获得文本框所输入的信息
            str[i]=jtxt[i].getText().trim();
        }
        if(str[0].equals("")||str[1].equals("")||str[2].equals("")||str[3].equals("")
                ||str[4].equals("")||str[5].equals(""))
        {//必要信息输入为空提示
            JOptionPane.showMessageDialog(this,	"员工信息不能为空！！！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {//添加信息进数据库
            String s=jtxt[0].getText().trim();//得到职工ID
            int cd=Integer.parseInt(s);
            sql="select * from emp where employee_id="+cd+"";//查询该职工信息是否存在
            db.selectDb(sql);
            try {
                if(!db.rs.next()) {//结果集为不空执行插入功能
                    int in=Integer.parseInt(jtxt[0].getText().trim());
                    sql="insert into work_hist(employee_id,e_name,duty," +
                            "time,depart,pay" +
                            ") values("+in+",'"+str[1]+"','"
                            +str[2]+"','"+str[3]+"','"+str[4]+"','"+str[5]+ "')";
                    db.updateDb(sql);
                    JOptionPane.showMessageDialog(this,"添加成功！！","消息!!",
                            JOptionPane.INFORMATION_MESSAGE);//添加成功提示
                    vtemp = db.getWorkHistory();//重新得到信息
                    this.first();
                }
                else {//弹出提示对话框
                    JOptionPane.showMessageDialog(this,	"插入失败！！\n该员工不存在！请检查员工信息",
                            "消息",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(Exception e) {//捕获异常
                //e.printStackTrace();
            }
        }
    }
    public void deleteWorkHistory()
    {
        for(int i=0;i<6;i++) {//获得文本框所输入的信息
            str[i]=jtxt[i].getText().trim();
        }
        if(str[0].equals("")||str[1].equals("")||str[2].equals("")||str[3].equals("")
                ||str[4].equals("")||str[5].equals(""))
        {//未入职工ID
            JOptionPane.showMessageDialog(this,	"信息不全！！\n请选择需要删除的信息！！！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            sql="select * from work_hist where w_hist_id="+id+"";//查询该职工信息是否存在
            db.selectDb(sql);
            try
            {
                if(db.rs.next())
                {//结果集不为空执行删除功能
                    sql="delete from work_hist where w_hist_id="+id+"";
                    db.updateDb(sql);
                    JOptionPane.showMessageDialog(this,	"成功删除该职工信息记录！！",
                            "消息",JOptionPane.INFORMATION_MESSAGE);
                    vtemp = db.getWorkHistory();//重新得到信息
                }
                else
                {//弹出提示对话框
                    JOptionPane.showMessageDialog(this,	"没有该职工！",
                            "消息",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(Exception e)
            {//捕获异常
                //e.printStackTrace();
            }
        }
    }
    public void updateWorkHistory()
    {
        for(int i=0;i<6;i++)
        {//获得文本框所输入的信息
            str[i]=jtxt[i].getText().trim();
        }
        if(str[0].equals("")||str[1].equals("")||str[2].equals("")||str[3].equals("")
                ||str[4].equals("")||str[5].equals(""))
        {//实现职工信息的更新
            JOptionPane.showMessageDialog(this,"请输入需要修改的信息！！",
                    "消息!!",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {//输入为空弹出提示对话框
            String s=jtxt[0].getText().trim();//得到职工ID
            int cd=Integer.parseInt(s);
            sql="select * from emp where employee_id="+cd+"";//查询该职工信息是否存在
            db.selectDb(sql);
            try
            {
                if(db.rs.next())
                {//结果集不为空执行删除功能
                    sql="update work_hist set e_name='"+str[1]+"',duty='"
                            +str[2]+"',time='"+str[3]+"',depart='"+str[4]+"',pay='"
                            +str[5] +"' where w_hist_id='"+id+"'";
                    db.updateDb(sql);
                    JOptionPane.showMessageDialog(this,"修改信息成功！！","消息!!",
                            JOptionPane.INFORMATION_MESSAGE);//修改成功提示
                    vtemp = db.getWorkHistory();
                }
                else
                {//弹出提示对话框
                    JOptionPane.showMessageDialog(this,	"修改失败！！\n该员工不存在！请先插入员工信息",
                            "消息",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(Exception e)
            {//捕获异常
                //e.printStackTrace();
            }
        }
    }
}