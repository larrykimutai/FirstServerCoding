import java.net.*;
import java.io.*;

public class Client {
  //initialize socket and input/output stream
  private Socket socket = null;
  private DataInputStream input = null;
  private DataOutputStream out = null;

  //create constructor that accepts IP address and port number
  public Client(String address, int port) throws IOException {

    //establish a connection
    try{
      //create socket and pass ip address and port number
      socket = new Socket(address, port);
      System.out.println("Connected");

      //take input from terminal
      input = new DataInputStream(System.in);

      //send output to the socket
      out = new DataOutputStream(socket.getOutputStream());
    }
    catch(UnknownHostException uhe){
      System.out.println(uhe);
    }

    //string to read message from the input tab
    String line = "";

    //keep reading until "Over" is displayed on the screen
    while(!line.equals("Over")){
      try{
        line = input.readLine();
        out.writeUTF(line);
      }
      catch (IOException i){
        System.out.println(i);
      }
    }

    //close the connection
    try{
      input.close();
      out.close();
      socket.close();
    }
    catch (IOException i){
      System.out.println(i);
    }
  }

  public static void main(String[] args) throws IOException {
    new Client("127.0.0.1", 5000);
  }
}
