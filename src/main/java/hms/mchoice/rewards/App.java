package hms.mchoice.rewards;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DBApp dbApp = new DBApp();
        dbApp.makeConnection();
        dbApp.createDB("Student");
        dbApp.selectDB("Student");
        dbApp.createTable();
       // dbApp.insertData(10,"Kasun",18);
       // dbApp.insertData(7,"old",19);
        System.out.println("78878"+dbApp.selectById(7));

    }
}
