package hrms.add;

import hrms.model.DataBase;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class PersonInformation extends JFrame {

    JScrollPane centerPanel = new JScrollPane();
    ListSelectionModel listSelectionModel = null;
    JTable table;
    //定义表格的字段

    //用于存放表格记录的二维数组
    String[] colName = {"职工ID", "姓名", "部门", "工种", "职务"};
    String[][] colValue;

    DataBase db;
    Vector<String[]> vtemp;
    Font font = new Font("Dialog", Font.PLAIN, 12);

    public PersonInformation(){
        this.setLayout(null);
        this.setTitle("查询员工基本信息");
        this.setFont(font);
        this.setSize(900, 500);
        ImageIcon icon = new ImageIcon("src/image/frame.png");
        this.setIconImage(icon.getImage());
        this.setLocation(300, 250);
        db = new DataBase();
        db.link();
        getInformation();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                db.dbClose();
            }
        });
        this.setVisible(true);
    }

    public void centerPanelInit(){
        this.remove(centerPanel);
        table = new JTable(colValue, colName);
        table.setPreferredScrollableViewportSize(new Dimension(900, 750));
        listSelectionModel = table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        centerPanel = new JScrollPane(table);
        centerPanel.setBounds(0, 80, 900, 300);
        this.add(centerPanel);
    }

    public void getInformation(){
        String sql = "select employee_id, e_name, d_name, work_type,duty from hrms.emp_view;";
        vtemp = db.getData(sql);
        if(vtemp.isEmpty()){
            colValue = new String[][]{{"无", "0", "0", "0", "0"}};
            JOptionPane.showMessageDialog(this,	"视图未创建！！",
                    "消息",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            int row = vtemp.size();
            colValue = new String[row][5];
            for(int  i = 0; i < row; i++){
                System.arraycopy(vtemp.get(i), 0, colValue[i], 0, 5);
            }
        }
        centerPanelInit();
    }
}
