package logFile;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogToFile {
	private static Logger logger = Logger.getLogger("NextCloset-Log");
	
	static {
        try {
            // ファイルハンドラーの設定
        	FileHandler fh = new FileHandler("/Users/shiokawa.taiga/Desktop/nextClosetLog.txt", true);

            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }

}
