package hrms.model;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class User extends JFrame implements ActionListener {

    DataBase db;
    Vector<String[]> vtemp;

    JLabel label= new JLabel();

    JLabel[] jlArray = {
            new JLabel("用 户 名"), new JLabel("登录密码")
    };
    JButton[] jbArray = {
            new JButton("退 出"), new JButton("重 置"), new JButton("登 录")
    };
    JTextField[] jtxt = {
            new JTextField(), new JTextField()
    };

    Font font = new Font("Dialog", Font.PLAIN, 14);

    public User(){
        UIManager.put("OptionPane.messageFont", new FontUIResource(font));
        UIManager.put("OptionPane.buttonFont", new FontUIResource(font));
        this.setLayout(null);//设置本窗体为空布局
        this.setFont(font);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("欢迎使用人事管理系统");
        ImageIcon icon = new ImageIcon("src/image/user.png");
        this.setIconImage(icon.getImage());
        setSize(500, 350);
        setLocation(430, 220);
        this.setResizable(false);
        label.setBounds(110, 50, 241, 60);
        this.add(label);

        for(int i = 0; i < 2; i++){
            this.add(jlArray[i]);
            jlArray[i].setFont(font);
            this.add(jtxt[i]);
            jtxt[i].setFont(font);
            if(i < 1){
                jlArray[i].setBounds(90,120, 80, 25);
                jtxt[i].setBounds(180, 120, 150, 25);
            }else{
                jlArray[i].setBounds(90, 180, 80, 25);
                jtxt[i].setBounds(180, 180, 150, 25);
            }
        }
        jlArray[0].setIcon(new ImageIcon("src/image/userName.png"));
        jlArray[1].setIcon(new ImageIcon("src/image/password.png"));
        jtxt[0].addActionListener(this);
        for(int i = 0; i < 3; i++){
            this.add(jbArray[i]);
            jbArray[i].setFont(font);
            jbArray[i].setBounds(60+i*150, 250,100, 25);
            jbArray[i].addActionListener(this);
        }
        jbArray[0].setIcon(new ImageIcon("src/image/exit.png"));
        jbArray[1].setIcon(new ImageIcon("src/image/reset.png"));
        jbArray[2].setIcon(new ImageIcon("src/image/login.png"));
        jtxt[1].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER){
                    logoIn();
                }
            }
        });
        setVisible(true);
    }

    public boolean linkDb(){
        db = new DataBase();
        if(db.link()){
            vtemp = db.getUser();
            db.dbClose();
            return true;
        }else
        {
            JOptionPane.showMessageDialog(this,	"数据库连接失败！！！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }


    public void actionPerformed(ActionEvent event){
        if(event.getSource() == jtxt[0]){
            jtxt[1].requestFocus();
        }
        if(event.getSource() == jbArray[0]){
            this.exit();
        }
        if(event.getSource() == jbArray[1]){
            this.setNull();
            jtxt[0].requestFocus();
        }
        if(event.getSource() == jbArray[2]){
            this.logoIn();
        }
    }

    public void exit(){
        System.exit(0);
    }

    public void setNull(){
        jtxt[0].setText("");
        jtxt[1].setText("");
    }

    public boolean judgeUser(String name, String pasd){
        for (String[] strings : vtemp) {
            String userName = strings[0];
            String password = strings[1];
            if (name.equals(userName) && pasd.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void logoIn(){
        if(linkDb()){
            String name = jtxt[0].getText();
            String pasd = jtxt[1].getText();
            if(name.equals("")||pasd.equals("")){
                JOptionPane.showMessageDialog(this,	"用户名和密码不能为空！！！",
                        "消息",JOptionPane.INFORMATION_MESSAGE);
            }else{
                if(judgeUser(name, pasd)){
                    JOptionPane.showMessageDialog(this,	"登陆成功",
                            "消息",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new MainFrame();
                }else{
                    JOptionPane.showMessageDialog(this,	"用户名或密码错误！！",
                            "消息",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] avge){
        new User();
    }
}
