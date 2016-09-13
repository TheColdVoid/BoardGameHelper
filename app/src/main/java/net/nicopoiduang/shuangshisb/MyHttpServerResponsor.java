package net.nicopoiduang.shuangshisb;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class MyHttpServerResponsor extends Thread{
    public String postData;
    //服务器根目录，post.html, upload.html都放在该位置
    public static String WEB_ROOT = "c:/root";
    //端口
    private int port;
    //用户请求的文件的url
    private String requestPath;
    //mltipart/form-data方式提交post的分隔符,
    private String boundary = null;
    //post提交请求的正文的长度
    private int contentLength = 0;
    private Socket socket=null;
    private game game;

    @Override
    public void run() {
        super.run();
        try {
            Response();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public MyHttpServerResponsor(Socket socket,game game) {
        WEB_ROOT = "";
        requestPath = null;
        this.socket=socket;
        this.game=game;
    }
    
    private void doGet(BufferedReader reader, BufferedWriter out) throws Exception {
            

        String body=new String();
        player player=game.getNewPlayer(socket.getRemoteSocketAddress());
        if(player!=null)
        {
            body=game.responseCombiner.getResponsePage(player);
        }
        else body=game.responseCombiner.getNewUserPage(player);
        out.write(body);
        out.flush();
        //Thread.sleep(100);
        String line = null;
        while (!(line = reader.readLine()).isEmpty()) {
            //System.out.println(line);
            ;
        }
            System.out.println("request complete.");
    }
    //处理post请求
    private void doPost(BufferedReader reader, BufferedWriter out) throws Exception {
        String body=new String();
        String line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            line = reader.readLine();
            if ("".equals(line)) {
                break;
            } else if (line.indexOf("Content-Length") != -1) {
                this.contentLength = Integer.parseInt(line.substring(line.indexOf("Content-Length") + 16));
            }
            else if(line.indexOf("multipart/form-data")!= -1){
                this.boundary = line.substring(line.indexOf("boundary") + 9);
                return;
            }
        }
        player player=game.getPlayer(socket.getRemoteSocketAddress());
        String postData="";
        byte[] buf = {};
        int size = 0;
        if (this.contentLength != 0) {
            buf = new byte[this.contentLength];
            while(size<this.contentLength){
                int c = reader.read();
                buf[size++] = (byte)c;

            }
            postData=new String(buf, 0, size);
        }
        System.out.println(postData);
//        game.dataPoster.update();
        game.dataPoster.update(postData,player);
        body=game.responseCombiner.getResponsePage(player);
        out.write(body);
        out.flush();
        String line2 = null;
        //while (!(line2 = reader.readLine()).isEmpty()) {
        //    //System.out.println(line);
        //    ;
        //}
        //Thread.sleep(100);

        System.out.println("request complete.");

    }

    public void Response() throws Exception {
        BufferedWriter out=null;
        BufferedReader reader=null;
        boolean t=true;
        boolean isCracked=false;
        while (t) {
            try {
                String line=null;
                if (socket!=null&&socket.isClosed()!=true) {
                    reader = new BufferedReader(new InputStreamReader(
                            socket.getInputStream(),"UTF-8"));;
                    line = reader.readLine();
                } else {

                    t=false;
                }
                if (line!=null&&socket!=null&&socket.isClosed()!=true) {
                    //System.out.println(line);
                    if(line.indexOf("favicon.ico")!=-1)
                        return;
                    String method = line.substring(0, 4).trim();

                    out = new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream(),"UTF-8"));
                    this.requestPath = line.split(" ")[1];
                    //System.out.println(method);
                    if ("GET".equalsIgnoreCase(method)) {
                        System.out.println("do get......");
                        this.doGet(reader, out);
                    } else if ("POST".equalsIgnoreCase(method)) {
                        System.out.println("do post......");
                        this.doPost(reader, out);
                        //this.doGet(reader,out);
                    }
                    //t=false;
                    //socket.close();
                    System.out.println("socket closed.");
                    t=false;
                }
                else if(socket!=null&&socket.isClosed()!=true){
                    t=true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    if ( socket!= null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
