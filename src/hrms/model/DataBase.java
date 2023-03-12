package hrms.model;

import java.sql.*;
import java.util.*;

public class DataBase
{
    Connection con=null;//声明Connection引用
    Statement stat;
    ResultSet rs;
    int count;
    public DataBase()
    {
        try
        {//加载MySQL的驱动类，并创建数据库连接
            Class.forName("org.gjt.mm.mysql.Driver");
            //
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hrms","root","");
        }
        catch(Exception e)
        {//捕获异常，并打印出来
            //e.printStackTrace();
        }
    }

    public boolean link(){
        try{
            stat=con.createStatement();//创建Statement对象
        }
        catch(Exception e)
        {//捕获异常，并打印出来
            //e.printStackTrace();
        }
        return stat != null;
    }

    public void selectDb(String sql)
    {//声明select方法
        try
        {
            rs=stat.executeQuery(sql);
        }
        catch(Exception ei)
        {//捕获异常，并打印出来
            ei.printStackTrace();
        }
    }
    public void updateDb(String sql)
    {//声明update方法
        try
        {
            count=stat.executeUpdate(sql);
        }
        catch(Exception ei)
        {//捕获异常，并打印出来
            ei.printStackTrace();
        }
    }
    public void dbClose()
    {//声明close方法
        try
        {
            stat.close();
            con.close();//执行数据库关闭动作
        }
        catch(Exception e)
        {//捕获异常，并打印出来
            //e.printStackTrace();
        }
    }

    public Vector<String[]> getUser(){
        Vector<String[]> vtemp = new Vector<>();
        String sql = "select user_name, password from user";
        try{
            rs = stat.executeQuery(sql);
            while(rs.next()){
                String[] str = new String[2];
                for(int i = 0; i < str.length; i++){
                    str[i] = rs.getString(i + 1);
                }
                vtemp.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return vtemp;
    }

    public Vector<String[]> getDepart()
    {// 查询部门信息
        Vector<String[]> vtemp = new Vector<>();
        String sql = "select depart_id,d_name,manager,"+
                "employee_number,telephone,depart_desc from dept";//执行查询的SQL语句
        try
        {
            rs = stat.executeQuery(sql);
            while(rs.next())
            {
                String[] str = new String[6];
                for(int i = 0;i < str.length; i++)
                {
                    str[i] = rs.getString(i+1);
                }
                vtemp.add(str);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return vtemp;
    }

    public Vector<String[]> getDepartName(){
        Vector<String[]> vtemp = new Vector<>();
        String sql = "select depart_id,d_name from dept";
        try{
            rs = stat.executeQuery(sql);
            while(rs.next()){
                String[] str = new String[2];
                for(int i = 0; i < str.length; i++){
                    str[i] = rs.getString(i + 1);
                }
                vtemp.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vtemp;
    }

    public Vector<String[]> getPerson()
    {//对员工信息进行搜索
        Vector<String[]> vtemp = new Vector<>();
        String sql = "select employee_id,e_name,sex,date,polity,culture," +
                "spec,work_type,duty,telephone,email,id_card," +
                "address,account, depart from emp";//执行查询的SQL语句
        try
        {
            rs=stat.executeQuery(sql);
            while(rs.next())
            {//当结果集不为空
                String[] str = new String[15];
                for(int i=0;i<str.length;i++)
                {
                    str[i] = rs.getString(i+1);
                }
                vtemp.add(str);
            }
        }
        catch(Exception e)
        {//捕获异常，并打印出来
            //e.printStackTrace();
        }
        return vtemp;
    }
    public Vector<String[]> getWorkHistory()
    {
        Vector<String[]> vtemp = new Vector<>();
        String sql = "select employee_id,e_name,duty,time,"+
                "depart,pay, w_hist_id from work_hist";
        try
        {
            rs = stat.executeQuery(sql);
            while(rs.next())
            {
                String[] str = new String[7];
                for(int i = 0; i <str.length; i++)
                {
                    str[i] = rs.getString(i + 1);
                }
                vtemp.add(str);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return vtemp;
    }
    public Vector<String[]> getFamily()
    {
        Vector<String[]> vtemp = new Vector<>();
        String sql = "select employee_id,e_name,marriage,family_number,"+
                "child_number,dep_number from family";
        try
        {
            rs = stat.executeQuery(sql);
            while(rs.next())
            {
                String[] str = new String[6];
                for(int i = 0; i < str.length; i++)
                {
                    str[i] = rs.getString(i + 1);
                }
                vtemp.add(str);
            }

        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return vtemp;
    }
    public Vector<String[]> getEdu()
    {
        Vector<String[]> vtemp = new Vector<>();
        String sql = "select employee_id,e_name,time,culture,"+
                "school,spec, edu_id from edu";
        try
        {
            rs = stat.executeQuery(sql);
            while(rs.next()){
                String[] str = new String[7];
                for(int i = 0; i < str.length; i++){
                    str[i] = rs.getString(i + 1);
                }
                vtemp.add(str);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return vtemp;
    }
    public Vector<String[]> getWage(){
        Vector<String[]> vtemp = new Vector<>();
        String sql = "select employee_id,e_name,base_pay,prize,"+
                "prize_record,deprive_pay,deprive_record from wage";
        try{
            rs = stat.executeQuery(sql);
            while(rs.next()){
                String[] str = new String[7];
                for(int i = 0; i < str.length; i++){
                    str[i] = rs.getString(i + 1);
                }
                vtemp.add(str);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return vtemp;
    }

    public Vector<String[]> getData(String sql){
        Vector<String[]> vtemp = new Vector<>();
        try{
            rs = stat.executeQuery(sql);
            while(rs.next()){
                int num = rs.getMetaData().getColumnCount();
                String[] str = new String[num];
                for(int i = 0; i < num; i++){
                    str[i] = rs.getString(i + 1);
                }
                vtemp.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vtemp;
    }
}