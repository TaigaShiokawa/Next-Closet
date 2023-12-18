package logFile;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class LogFileDelete {
	public static void deleteLogFileAfter(String filePath, long delayInMilliseconds) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                File logFile = new File(filePath);
                if (logFile.exists() && logFile.delete()) {
                    System.out.println("ログファイルを正常に削除しました: " + filePath);
                } else {
                    System.out.println("ログファイルが見つからない, または削除に失敗しました: " + filePath);
                }
            }
        };
        new Timer().schedule(task, delayInMilliseconds);
    }

}
