package completeTest;

import flyweight.FlyweightUnitTest;
import proxy.ProxyUnitTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        FlyweightUnitTest.class,
        ProxyUnitTest.class
})
public class CompleteUnitTest {
}
