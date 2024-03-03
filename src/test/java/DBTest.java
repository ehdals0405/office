import com.nhnacademy.config.RootConfig;
import com.nhnacademy.config.WebConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class DBTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("DB연결 테스트")
    @Disabled
    public void dbTest(){

        Query query = entityManager.createNativeQuery("SELECT 1");
        Object result = query.getSingleResult();
        assertEquals(BigInteger.valueOf(1), result);
    }
}
