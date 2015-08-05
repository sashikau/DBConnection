package hms.mchoice.rewards;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class DBAppTest {


    DBApp dbApp;

    @BeforeSuite
    public void testMakeConnection() throws Exception {
        dbApp = new DBApp();
        dbApp.makeConnection();
        assertTrue(dbApp.isConnected());
        dbApp.selectDB("Student");
    }

    @Test
    public void testCreateDB() throws Exception {
        dbApp.createDB("Student");
        assertNotNull(dbApp.selectDB("Student"));

    }

    @Test
    public void testInsertData() throws Exception {
        assertTrue(dbApp.insertData(15,"test",25));
        assertEquals(dbApp.selectById(15), "test");
        assertTrue(!dbApp.insertData(15, "test",25));
    }

    @Test
    public void testDisplay() throws Exception {

    }

    @Test
    public void testSelectById() throws Exception {
        dbApp.insertData(25,"test1",25);
        assertEquals(dbApp.selectById(25), "test1");
    }

    @Test
    public void testDeleteById() throws Exception {
        dbApp.insertData(5,"test1",25);
        assertTrue(dbApp.deleteById(5));

    }

    @Test
    public void testChangeNameById() throws Exception {
        dbApp.selectDB("Student");
        dbApp.insertData(7,"old",15);
        dbApp.changeNameById(7,"new");
        assertEquals(dbApp.selectById(7),"new");
    }

    @AfterSuite
    public void testCloseCon() throws Exception {
        dbApp.deleteById(7);
        dbApp.deleteById(15);
        dbApp.deleteById(25);
        dbApp.closeCon();
        assertTrue(!dbApp.isConnected());

    }
}