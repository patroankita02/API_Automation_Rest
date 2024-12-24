import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class TestLog
{
    private static Logger logger = LogManager.getLogger(TestLog.class);
    public static void main(String[] args)
    {
        System.out.println("Execution started: ");

        // logging messages
        logger.info("This is for information");
        logger.error("This is for error information");
        logger.warn("This is for warning information");
        logger.fatal("This is for fatal information");

        System.out.println("Execution done: ");
    }
}
