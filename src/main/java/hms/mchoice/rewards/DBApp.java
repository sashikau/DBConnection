package hms.mchoice.rewards;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by shashika on 8/3/15.
 */
public class DBApp {
    private static final String driver ="com.mysql.jdbc.Driver";
    private static final String url ="jdbc:mysql://localhost/";
    private static final String user="user";
    private static final String pass="password";

    private boolean isConnected =false;
    private Statement stmnt=null;
    private Connection con =null;

    public void makeConnection(){

        try {
            Class.forName(driver);
            con= DriverManager.getConnection(url, user, pass);
            isConnected=true;
        }catch (Exception e){
            System.out.println("Couldn't make the connection with the database");
        }

    }

    public boolean isConnected() {
        return isConnected;
    }

    private void checkCon(){
        if(!isConnected) {
            makeConnection();
        }
    }

    public void createDB(String name){

        try {
            checkCon();
            String sql="CREATE DATABASE IF NOT EXISTS "+name;
            stmnt=con.createStatement();

            stmnt.execute(sql);
            System.out.println("Database successfully created ");
        }catch (Exception e){
            System.out.println("Unable to create the Database ");
        }
    }

    public String selectDB(String db){
        String sql="Use "+db;

        try {
            stmnt = con.createStatement();
            stmnt.execute(sql);
            return db;

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public void createTable(){


        String sql="CREATE TABLE IF NOT EXISTS details ( SID INT(5) , Name VARCHAR(20), Age INT(2), Primary Key(SID))";

        try{
            checkCon();
            stmnt.execute(sql);
            System.out.println("done");
        }catch (Exception e){
            System.out.println("Table creation failed");
        }
    }

    public boolean insertData(int id, String name, int age ){

        try {
            checkCon();
            String sql = "INSERT INTO details (SID, Name, Age) VALUES ("+id+", '"+name+"', "+age+")";
            stmnt=con.createStatement();

            stmnt.executeUpdate(sql);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    public void display(){
        try{
            checkCon();
            String sql = "SELECT * FROM details";
            stmnt=con.createStatement();

            ResultSet rs = stmnt.executeQuery(sql);

            while(rs.next()){
                System.out.print(rs.getString(1) + "|");
                System.out.print(rs.getString(2)+"|");
                System.out.print(rs.getString(3)+"|");
                System.out.println();
                System.out.println();
            }

        }catch (Exception e){
            System.out.println(e);
            System.out.println("Unable to display");
        }
    }

    public String selectById(int id){
        try {
            checkCon();
            String sql = "SELECT * FROM details WHERE SID = "+id ;
            stmnt = con.createStatement();

            ResultSet rs = stmnt.executeQuery(sql);

            if(rs.next()){
                System.out.println("Student name is : "+rs.getString(2));
                System.out.println("Student age is  : "+rs.getString(3));
                return rs.getString(2);
            }else {
                System.out.println("There is no such student");
                return null;
            }

        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    public boolean deleteById(int id){
        try{
            checkCon();
            String sql = "DELETE FROM details WHERE SID = "+id;
            stmnt=con.createStatement();

            stmnt.executeUpdate(sql);
            System.out.println("Successfully deleted");
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public void changeNameById(int id, String Name){

        try{
            checkCon();
            String sql1= " SELECT * FROM details WHERE SID = "+id;
            stmnt=con.createStatement();

            ResultSet rs = stmnt.executeQuery(sql1);

            if(rs.next()){
                System.out.println("Student's current name : "+rs.getString(2));

                String sql="UPDATE details SET Name = '"+Name+"' WHERE SID = "+id;
                stmnt.executeUpdate(sql);

            }else {
                System.out.println("Invalid SID");
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void closeCon(){
        try {
            con.close();
            isConnected=false;

        }catch (Exception e){
            System.out.println("Couldn't close the connection");
        }
    }


}
