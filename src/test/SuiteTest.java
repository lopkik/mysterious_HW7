import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ChatHistoryTest.class, ChatServerTest.class, MessageTest.class,
        MessageMementoTest.class, SearchMessagesByUserTest.class,
        UserTest.class
})
public class SuiteTest {
}
