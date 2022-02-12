package LeeJerry.realWorld;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RealWorldApplicationTests {


    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void sqlSessionTemplateSimpleTest() {
        assertThat(sqlSessionTemplate).isInstanceOf(sqlSessionTemplate.getClass());
    }
}
