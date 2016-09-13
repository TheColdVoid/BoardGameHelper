package net.nicopoiduang.shuangshisb;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by The_Void on 2016/8/25.
 */
public class MyHttpServerListener extends Thread{
    int port;
    game game;
    ServerSocket serverSocket;
    public MyHttpServerListener(int port,game game)
    {
        this.port=port;
        this.game=game;
    }
    public void run()  {
        super.run();
        System.out.println("server is ok.");
        Thread responseThread=null;
        try {
            serverSocket = new ServerSocket(port);
            while (true)
            {

                Socket socket = serverSocket.accept();
                System.out.println("New Client");


                        responseThread= new MyHttpServerResponsor(socket,game);
                        responseThread.start();
                        System.out.println("Create Thread");
                        //Thread.sleep(5000);
                //else System.out.println("same");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

}
