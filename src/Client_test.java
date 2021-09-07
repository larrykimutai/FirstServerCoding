import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client_test{

  //create constructor to accept IP address and port number
  public Client_test(String IpAddress, int portNum) throws IOException {

    Socket socket = null;

    //try to establish connection with server via socket that accepts Ip Address and port number
    try{
      socket = new Socket(IpAddress, portNum);
      System.out.println("Connected");

    }catch(UnknownHostException unknownHostException){
      System.out.println("IP Address of host could not be determined " + unknownHostException);
    }

    //get data input from user
    Scanner toServer = new Scanner(System.in);

    System.out.print("Enter Radius: ");
    double radius = toServer.nextDouble();
    System.out.print("Enter Height: ");
    double height = toServer.nextDouble();

    //create PrintWriter object used to send data to server via socket
    PrintWriter p = new PrintWriter(socket.getOutputStream(), true);

    //send the data to server
    p.println(radius);
    p.println(height);

    //used to receive messages sent by the PrintWriter object from the server
    Scanner fromServer = new Scanner(socket.getInputStream());

    //store data sent by the server
    double radius_fromServer = fromServer.nextDouble();
    double height_fromServer = fromServer.nextDouble();
    double volume = fromServer.nextDouble();

    //print results from server to console
    System.out.println("Radius: " + radius_fromServer + "\nHeight: " + height_fromServer +
            "\nVolume: " + volume);


    //close the connection
    toServer.close();
    fromServer.close();
    p.close();
    socket.close();

    System.out.println("Connection Closed");


  }

  public static void main(String[] args)throws IOException{
    new Client_test("localhost", 12000);
  }
}


