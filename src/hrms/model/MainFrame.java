package hrms.model;

import hrms.add.DepartInformation;
import hrms.add.DepartNumber;
import hrms.add.PersonInformation;
import hrms.fun.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainFrame extends JFrame implements ActionListener, TreeSelectionListener{

    Dimension facesize = new Dimension(1200,700);

    JTree tree;
    DefaultMutableTreeNode root;
    DefaultMutableTreeNode node1;
    DefaultMutableTreeNode node2;
    DefaultMutableTreeNode node3;
    DefaultMutableTreeNode node4;
    DefaultMutableTreeNode node5;
    DefaultMutableTreeNode node6;
    DefaultMutableTreeNode node7;
    DefaultMutableTreeNode node8;
    DefaultMutableTreeNode node9;
    DefaultMutableTreeNode leafnode;    //叶子节点
    TreePath treePath;

    public static JSplitPane splitPane;
    JPanel panel1;
    JPanel panel2;
    JLabel welcome = new JLabel();   	//用来显示欢迎信息
    JScrollPane scrollPane;             //滚动面板
    Font font = new Font("Dialog", Font.PLAIN, 14);

    public MainFrame(){
        this.setLayout(null);//设置本窗体为空布局
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setSize(facesize);
        this.setLocation(100, 50);
        setTitle("人事管理系统");
        ImageIcon icon = new ImageIcon("src/image/mainframe.png");
        this.setIconImage(icon.getImage());
        this.setResizable(false);
        this.setFont(font);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(MainFrame.this,
                        "是否确定退出系统？", "系统退出",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        this.setVisible(true);
        Init();
    }

    private void Init() {
        //将各个子节点，添加到root上去。
        root = new DefaultMutableTreeNode("人事管理系统");
        //实例化root的四个子节点。
        node1 = new DefaultMutableTreeNode("部门基本信息管理");
        node2 = new DefaultMutableTreeNode("员工基本信息管理");
        node3 = new DefaultMutableTreeNode("员工学习信息管理");
        node4 = new DefaultMutableTreeNode("员工任职经历管理");
        node5 = new DefaultMutableTreeNode("员工家庭信息管理");
        node6 = new DefaultMutableTreeNode("员工奖惩信息管理");
        node7 = new DefaultMutableTreeNode("部门职称信息查询");
        node8 = new DefaultMutableTreeNode("视图级员工信息查询");
        node9 = new DefaultMutableTreeNode("数据备份与恢复");

        leafnode = new DefaultMutableTreeNode("添加部门信息");
        node1.add(leafnode);
        leafnode = new DefaultMutableTreeNode("查询部门信息");
        node1.add(leafnode);
        root.add(node1);
        leafnode = new DefaultMutableTreeNode("添加员工信息");
        node2.add(leafnode);
        leafnode = new DefaultMutableTreeNode("查询员工信息");
        node2.add(leafnode);
        root.add(node2);
        leafnode = new DefaultMutableTreeNode("添加学习记录");
        node3.add(leafnode);
        leafnode = new DefaultMutableTreeNode("查询学习记录");
        node3.add(leafnode);
        root.add(node3);
        leafnode = new DefaultMutableTreeNode("添加任职经历");
        node4.add(leafnode);
        leafnode = new DefaultMutableTreeNode("查询任职信息");
        node4.add(leafnode);
        root.add(node4);
        leafnode = new DefaultMutableTreeNode("添加家庭信息");
        node5.add(leafnode);
        leafnode = new DefaultMutableTreeNode("查询家庭信息");
        node5.add(leafnode);
        root.add(node5);
        leafnode = new DefaultMutableTreeNode("添加奖惩信息");
        node6.add(leafnode);
        leafnode = new DefaultMutableTreeNode("查询奖惩信息");
        node6.add(leafnode);
        root.add(node6);
        leafnode = new DefaultMutableTreeNode("查询部门职称信息");
        node7.add(leafnode);
        leafnode = new DefaultMutableTreeNode("查询部门职称人数");
        node7.add(leafnode);
        root.add(node7);
        leafnode = new DefaultMutableTreeNode("职工基本信息查询");
        node8.add(leafnode);
        root.add(node8);
        leafnode = new DefaultMutableTreeNode("系统数据备份");
        node9.add(leafnode);
        leafnode = new DefaultMutableTreeNode("系统数据恢复");
        node9.add(leafnode);
        root.add(node9);
        leafnode = new DefaultMutableTreeNode("退出系统");
        root.add(leafnode);
        tree = new JTree(root);
        tree.setFont(font);
        scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(200, 850));
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel1.add(scrollPane);
        welcome.setText("欢迎使用人事管理系统");
        welcome.setFont(new Font("Dialog", Font.PLAIN, 20));
        welcome.setBounds(0, 200, 200, 300);
        panel2.add(welcome);
        splitPane = new JSplitPane();
        splitPane.setOneTouchExpandable(false);
        splitPane.setContinuousLayout(true);
        splitPane.setPreferredSize(new Dimension(1100,900));
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(panel1);
        splitPane.setRightComponent(panel2);
        splitPane.setDividerSize(2);
        splitPane.setDividerLocation(200);
        this.setContentPane(splitPane);
        this.setVisible(true);
        //为左侧的tree添加事件监听器。
        tree.addTreeSelectionListener(this);
    }

    public void actionPerformed(ActionEvent event) {


    }

    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
        String nodeText = currentNode.toString();
        if(Objects.equals(nodeText, "人事管理系统")) {
            splitPane.setRightComponent(panel2);
        }
        else if(Objects.equals(nodeText, "部门基本信息管理")) {
            treePath = new TreePath(node1.getPath());
            if(tree.isExpanded(treePath)) {
                tree.collapsePath(treePath);
            }
            else {
                tree.expandPath(treePath);
            }
        }
        else if (Objects.equals(nodeText, "添加部门信息")) {
           new Depart();
        }
        else if(Objects.equals(nodeText, "查询部门信息")) {
            new QueryDepart();
        }
        else if(Objects.equals(nodeText, "添加员工信息")) {
            new Person();
        }
        else if(Objects.equals(nodeText, "查询员工信息")) {
            new QueryPerson();
        }
        else if(Objects.equals(nodeText, "添加学习记录")) {
            new Edu();
        }
        else if(Objects.equals(nodeText, "查询学习记录")) {
            new QueryEdu();
        }
        else if(Objects.equals(nodeText, "添加任职经历")) {
            new WorkHistory();
        }
        else if(Objects.equals(nodeText, "查询任职信息")) {
            new QueryWorkHistory();
        }
        else if(Objects.equals(nodeText, "添加家庭信息")) {
            new Family();
        }
        else if(Objects.equals(nodeText, "查询家庭信息")) {
            new QueryFamily();
        }
        else if(Objects.equals(nodeText, "添加奖惩信息")) {
            new Wage();
        }
        else if(Objects.equals(nodeText, "查询奖惩信息")){
            new QueryWage();
        }
        else if(Objects.equals(nodeText, "查询部门职称信息")){
            new DepartInformation();
        }
        else if(Objects.equals(nodeText, "查询部门职称人数")){
            new DepartNumber();
        }
        else if(Objects.equals(nodeText, "职工基本信息查询")){
            new PersonInformation();
        }
        else if(Objects.equals(nodeText, "系统数据备份")){
            JOptionPane.showMessageDialog(this,	"此功能未实现！！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(Objects.equals(nodeText, "系统数据恢复")){
            JOptionPane.showMessageDialog(this,	"此功能未实现！！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(Objects.equals(nodeText, "退出系统")){
            if (JOptionPane.showConfirmDialog(MainFrame.this,
                    "是否确定退出系统？", "系统退出",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }
}
