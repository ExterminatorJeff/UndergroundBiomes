/*
 * author Zeno410
 * using code by Lars Vogel
 */

package exterminatorJeff.undergroundBiomes.constructs.util;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Zeno410Logger {
  static private FileHandler fileTxt;
  static private SimpleFormatter formatterTxt;

  static private FileHandler fileHTML;
  static private Formatter formatterHTML;

  private Logger logger;

  static public Logger globalLogger() {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    logger.setLevel(Level.ALL);
        /*try {
            fileTxt = new FileHandler("/Zeno410Logging.txt");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SecurityException ex) {
            throw new RuntimeException(ex);
        }*/

    // Create txt Formatter
    formatterTxt = new SimpleFormatter();
    //fileTxt.setFormatter(formatterTxt);
    //logger.addHandler(fileTxt);
    logger.log(Level.INFO,"Starting");
    return logger;
  }

  public Logger logger() {return logger;}

  public Zeno410Logger(String name) {
       logger = Logger.getLogger(name);
        try {
            fileTxt = new FileHandler("/"+name+".txt");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SecurityException ex) {
           throw new RuntimeException(ex);
        }
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
  }
}
