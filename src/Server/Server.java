package Server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Server {
    static List<Socket> socketList = new ArrayList<>();
    ServerSocket ss;
    Server() {
        try{
        ss = new ServerSocket(337);
        while(true){
            Socket s;
            s = ss.accept();
            socketList.add(s);
            new ReadThreadServer(s,socketList);
        }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws IOException {
        new Server();
    }
}