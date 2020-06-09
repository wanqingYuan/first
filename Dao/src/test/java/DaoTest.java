import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.dao.OrderDao;
import org.example.dao.ProductDao;
import org.example.dao.UserDao;
import org.example.daomain.Order;
import org.example.daomain.Product;
import org.example.daomain.UserInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class DaoTest {
    private ApplicationContext ac;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private ProductDao productDao;
    private OrderDao orderDao;
    private UserDao userDao;

    @Before
    public void before(){
        ac=new ClassPathXmlApplicationContext("classpath:spring/applicationConfig_dao.xml");
        factory= (SqlSessionFactory) ac.getBean("factory");
        sqlSession=factory.openSession();
        productDao=sqlSession.getMapper(ProductDao.class);
        orderDao=sqlSession.getMapper(OrderDao.class);
        userDao=sqlSession.getMapper(UserDao.class);
    }
    @After
    public void after(){
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testOrder(){
        Order list=orderDao.findById(1);
        System.out.println(list);
        System.out.println(list.getTravellers());
    }

    @Test
    public void test(){
        Product list=productDao.findById(2);
        System.out.println(list);
    }

    @Test
    public void date(){
        java.sql.Date date=new java.sql.Date(2020010100);
        System.out.println(date);
    }
    @Test
    public void testUserDao(){
       return;
    }

}
