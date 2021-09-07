import java.net.*;
import java.io.*;

public class Server {
  private Socket socket = null;
  private ServerSocket server = null;
  private DataInputStream in = null;

  //constructor with port
  public Server (int port){

    //start server and wait.... for connection
    try{
      server = new ServerSocket(port);
      System.out.println("Server started");

      System.out.println("Waiting for a client...");

      //when client gets connected to the server
      socket = server.accept();
      System.out.println("Client accepted");
      System.out.println("____________________________");

      //take input from the client socket
      in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

      String line = "";

      //read message from client until "over" is sent

      while(!line.equals("Over")){
        try{
          line = in.readUTF();
          System.out.println(line);
        }
        catch(IOException i){
          System.out.println(i);
        }
      }
      System.out.println("____________________________");
      System.out.println("Closing connection...");

      //close connections
      socket.close();
      in.close();
    }
    catch (IOException i){
      System.out.println(i);
    }
    System.out.println("Connection closed");
  }

  public static void main(String[] args){
    new Server(12000);
  }
}
