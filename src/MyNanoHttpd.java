import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Override;
import java.lang.Runnable;
import java.lang.Thread;
import java.lang.Throwable;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xuan on 2016/12/26 0026.
 */
public class MyNanoHttpd {
    private int myTcpPort;
    private File myRootDir;
    private ServerSocket myServerSocket;
    private Thread myThread;
    private Socket mySocket;

    private class HttpSession implements Runnable {
        public HttpSession(Socket s) {
            mySocket = s;
            Thread t = new Thread(this);
            t.setDaemon(true);
            t.start();
        }

        @Override
        public void run() {
            try {
                InputStream is = mySocket.getInputStream();
                if (is == null) return;

                //read the http header
                //8kb
                int bufsize = 8192;
                byte[] buf = new byte[bufsize];
                int rlen = is.read(buf, 0, bufsize);
                if (rlen <= 0) return;

                //
            } catch (IOException ioe) {}
        }
    }

    public MyNanoHttpd(int port, File wwwroot) throws IOException {
        myTcpPort = port;
        myRootDir = wwwroot;
        myServerSocket = new ServerSocket(myTcpPort);
        myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (True) {
                        new HTTPSession
                    }
                } catch (IOException ioe) {}
            }
        });
    }

    public static void main(String[] args) {
        //default port and www root
        int port = 80;
        File wwwroot = new File(".").getAbsoluteFile();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-p"))
                port = args[i+1];
            else if (args[i].equalsIgnoreCase("-d"))
                wwwroot = new File(args[i+1]);
        }

        try {
            new MyNanoHttpd(port, wwwroot);
        } catch (IOException ioe) {
            System.err.println("Could start the server:\n" + ioe);
            System.exit(-1);
        }

        System.out.println("Now serving files in port " + port + " from \""
            + wwwroot + "\"");
        System.out.println("Hit enter to stop.\n");
        //catch enter key to stop
        try {System.in.read();} catch (Throwable t) {}
    }
}
