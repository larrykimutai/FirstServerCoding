import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server_test {

  public double volume(double r, double h){
    return (Math.PI * (Math.pow(r,2)) * h);
  }

  public Server_test(int port) throws IOException {

    //start a server socket object that will listen to given port
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("Server Started");
    System.out.println("Waiting for client...");

    //create socket object to allow communication with client and accept a connection from client
    Socket socket = serverSocket.accept();
    System.out.println("Client accepted");

    //scanner object used to accept the data from the client
    Scanner scanner = new Scanner(socket.getInputStream());
    double radius = scanner.nextDouble();
    System.out.println(radius);
    double height = scanner.nextDouble();
    System.out.println(height);

    //calculate volume
    double vol = volume(radius,height);
    System.out.println(vol);

    //send result back to client
    PrintWriter p = new PrintWriter(socket.getOutputStream(), true);
    p.println(radius);
    p.println(height);
    p.println(vol);


    //close connection
    socket.close();
    p.close();
    scanner.close();
    System.out.println("Connection closed");
  }

  public static void main(String[] args) throws IOException {
    new Server_test(12000);
  }
}
