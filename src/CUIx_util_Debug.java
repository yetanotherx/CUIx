
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Debugging helper class
 * 
 * TODO: Add debug statements in ALL the places!
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIx_util_Debug {

    protected File debugFile;
    protected BufferedWriter writer;
    protected boolean debugMode = false;
    protected static String CRLF = System.getProperty("line.separator");

    public CUIx_util_Debug(File debugFile) throws IOException {
        this.debugFile = debugFile;
        this.writer = new BufferedWriter(new FileWriter(debugFile));
        this.debugMode = CUIx.getSettings().getProperty("debugMode").equals("true");
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
        
    }
    
    public void debug(String message) {
        if( debugMode ) {
            this.info(message);
        }
    }
    
    public void info(String message) {
        try {
            System.out.println(message);
            writer.write(message + CRLF );
            writer.flush();
            //TODO: Timestamps and don't clear the log!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
