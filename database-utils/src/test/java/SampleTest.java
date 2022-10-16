import com.huyong.ProjectsApplication;
import com.huyong.bo.ConnTree;
import com.huyong.bo.ExecuteSqlBody;
import com.huyong.dao.entity.DataConnection;
import com.huyong.dao.mapper.UserMapper;
import com.huyong.enums.DbType;
import com.huyong.service.DataBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = ProjectsApplication.class)
@RunWith(SpringRunner.class)
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DataBaseService dataBaseService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        System.out.println(userMapper.get(1));
    }
    @Test
    public void testInsert() {
        DataConnection dataConnection = new DataConnection();
        dataConnection.setHost("127.0.0.1");
        dataConnection.setDbType(DbType.MYSQL);
        dataConnection.setLoginUser("root");
        dataConnection.setPassword("123456");
        dataConnection.setName("test2");
        dataBaseService.saveConn(dataConnection);
    }

    @Test
    public void testList() {
        System.out.println(dataBaseService.listConns());
    }

    @Test
    public void testUpdate() {
        DataConnection dataConnection = new DataConnection();
        dataConnection.setHost("127.0.0.2");
        dataConnection.setDbType(DbType.MYSQL);
        dataConnection.setLoginUser("root");
        dataConnection.setId(1L);
        dataConnection.setPassword("123456");
        dataConnection.setName("testConn");
        dataBaseService.updateConn(dataConnection);
    }

    @Test
    public void testListDbs() {
        List<ConnTree> connTrees = dataBaseService.listDbs(1L);
        System.out.println(connTrees);
    }

    @Test
    public void deleteConn() {
        dataBaseService.deleteConn(1581647770814332930L);
    }

    @Test
    public void runSql() {
        System.out.println(dataBaseService.runSql(new ExecuteSqlBody()));
    }

}
