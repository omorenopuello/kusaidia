package org.kusaidia.util.logging;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kusaidia.util.logging.bean.SimpleBean;
import org.kusaidia.util.logging.bean.SimpleBeanSubclass;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "/aop-context.xml",
        "/application-context.xml",
        "/logger-test-context.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class LoggingAspectTest {

    @Autowired
    @Qualifier("logger")
    private Log logger;

    private MockLogger mockLogger;

    @Autowired
    @Qualifier(value = "simpleBean")
    private SimpleBean simpleBean;

    @Autowired
    private SimpleBeanSubclass simpleBeanSubclass;

    @Before
    public void before() throws Exception {
        this.mockLogger = (MockLogger) ((Advised) this.logger).getTargetSource().getTarget();
        this.mockLogger.setLogLevel(SimpleBean.class, LogLevel.TRACE);
        this.mockLogger.setLogLevel(SimpleBeanSubclass.class, LogLevel.TRACE);
        this.mockLogger.resetLoggers();
    }

    @Test
    public void testSimpleBean_SetDateProperty() throws Exception {
        simpleBean.setDateProperty(
                DateUtils.parseDate("01/01/2010", new String[]{"dd/MM/yyyy"}));

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBean.class).size());
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(0),
                LogLevel.TRACE,
                "[ entering < setDateProperty > with params Fri Jan 01 00:00:00 AST 2010 ]");
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(1),
                LogLevel.TRACE,
                "[ leaving < setDateProperty > ]");
    }

    @Test
    public void testSimpleBean_SetIntegerProperty() {
        simpleBean.setIntegerProperty(100);

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBean.class).size());
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(0),
                LogLevel.TRACE,
                "[ entering < setIntegerProperty > with params 100 ]");
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(1),
                LogLevel.TRACE,
                "[ leaving < setIntegerProperty > ]");
    }

    @Test
    public void testSimpleBean_SetStringProperty() {
        simpleBean.setStringProperty("stringProperty");

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBean.class).size());
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(0),
                LogLevel.TRACE,
                "[ entering < setStringProperty > with params stringProperty ]");
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(1),
                LogLevel.TRACE,
                "[ leaving < setStringProperty > ]");
    }

    @Test
    public void testSimpleBean_GetDateProperty() {
        simpleBean.getDateProperty();

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBean.class).size());
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(0),
                LogLevel.TRACE,
                "[ entering < getDateProperty > ]");
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(1),
                LogLevel.TRACE,
                "[ leaving < getDateProperty > returning 1/1/10 12:00 AM ]");
    }

    @Test
    public void testSimpleBean_GetIntegerProperty() {
        simpleBean.getIntegerProperty();

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBean.class).size());
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(0),
                LogLevel.TRACE,
                "[ entering < getIntegerProperty > ]");
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(1),
                LogLevel.TRACE,
                "[ leaving < getIntegerProperty > returning 100 ]");
    }

    @Test
    public void testSimpleBean_GetStringProperty() {
        simpleBean.getStringProperty();

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBean.class).size());
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(0),
                LogLevel.TRACE,
                "[ entering < getStringProperty > ]");
        assertEquals(mockLogger.getMessages(SimpleBean.class).get(1),
                LogLevel.TRACE,
                "[ leaving < getStringProperty > returning stringProperty ]");
    }

    @Test
    public void testSimpleBeanSubclass_SetDateProperty() throws Exception {
        simpleBeanSubclass.setDateProperty(
                DateUtils.parseDate("01/01/2010", new String[]{"dd/MM/yyyy"}));

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBeanSubclass.class).size());
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(0),
                LogLevel.TRACE,
                "[ entering < setDateProperty > with params Fri Jan 01 " +
                        "00:00:00 AST 2010 ]");
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(1),
                LogLevel.TRACE,
                "[ leaving < setDateProperty > ]");
    }

    @Test
    public void testSimpleBeanSubclass_SetDecimalProperty() {
        simpleBeanSubclass.setDecimalProperty(new BigDecimal("0.25"));

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBeanSubclass.class).size());
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(0),
                LogLevel.TRACE,
                "[ entering < setDecimalProperty > with params 0.25 ]");
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(1),
                LogLevel.TRACE,
                "[ leaving < setDecimalProperty > ]");
    }

    @Test
    public void testSimpleBeanSubclass_SetIntegerProperty() {
        simpleBeanSubclass.setIntegerProperty(100);

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBeanSubclass.class).size());
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(0),
                LogLevel.TRACE,
                "[ entering < setIntegerProperty > with params 100 ]");
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(1),
                LogLevel.TRACE,
                "[ leaving < setIntegerProperty > ]");
    }

    @Test
    public void testSimpleBeanSubclass_SetStringProperty() {
        simpleBeanSubclass.setStringProperty("stringProperty");

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBeanSubclass.class).size());
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(0),
                LogLevel.TRACE,
                "[ entering < setStringProperty > with params stringProperty ]");
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(1),
                LogLevel.TRACE,
                "[ leaving < setStringProperty > ]");
    }

    @Test
    public void testSimpleBeanSubclass_GetDateProperty() {
        simpleBeanSubclass.getDateProperty();

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBeanSubclass.class).size());
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(0),
                LogLevel.TRACE,
                "[ entering < getDateProperty > ]");
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(1),
                LogLevel.TRACE,
                "[ leaving < getDateProperty > returning 1/1/10 12:00 AM ]");
    }

    @Test
    public void testSimpleBeanSubclass_GetDecimalProperty() {
        simpleBeanSubclass.getDecimalProperty();

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBeanSubclass.class).size());
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(0),
                LogLevel.TRACE,
                "[ entering < getDecimalProperty > ]");
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(1),
                LogLevel.TRACE,
                "[ leaving < getDecimalProperty > returning 0.25 ]");
    }

    @Test
    public void testSimpleBeanSubclass_GetIntegerProperty() {
        simpleBeanSubclass.getIntegerProperty();

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBeanSubclass.class).size());
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(0),
                LogLevel.TRACE,
                "[ entering < getIntegerProperty > ]");
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(1),
                LogLevel.TRACE,
                "[ leaving < getIntegerProperty > returning 100 ]");
    }

    @Test
    public void testSimpleBeanSubclass_GetStringProperty() {
        simpleBeanSubclass.getStringProperty();

        Assert.assertEquals(2, mockLogger.getMessages(SimpleBeanSubclass.class).size());
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(0),
                LogLevel.TRACE,
                "[ entering < getStringProperty > ]");
        assertEquals(mockLogger.getMessages(SimpleBeanSubclass.class).get(1),
                LogLevel.TRACE,
                "[ leaving < getStringProperty > returning stringProperty ]");
    }

    private void assertEquals(MockLogger.LogMessage logMessage, LogLevel logLevel, String message) {
        Assert.assertEquals(logLevel, logMessage.getLogLevel());

        Assert.assertEquals(message, logMessage.getMessage());
    }
}

