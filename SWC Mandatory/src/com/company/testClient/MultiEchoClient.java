package com.company.testClient;

import java.io.*;
import java.net.*;
import java.util.*;

public class MultiEchoClient
{
    private static String host;
    private static final int PORT = 6000;
    Scanner networkInput;
    public static void main(String[] args)
    {
//        try
//        {
        host = "192.168.1.93";
//        }
//        catch(UnknownHostException uhEx)
//        {
//            System.out.println("\nHost ID not found!\n");
//            System.exit(1);
//        }
        sendMessages();
    }
    private static void sendMessages()
    {
        Socket socket = null;
        try
        {
            socket = new Socket(host,PORT);
            Scanner networkInput =
                    new Scanner(socket.getInputStream());
            PrintWriter networkOutput =
                    new PrintWriter(
                            socket.getOutputStream(),true);
            //Set up stream for keyboard entry…
            Scanner userEntry = new Scanner(System.in);
            String message, response;
            do
            {
                System.out.print(
                        "Enter message ('QUIT' to exit): ");
                message = userEntry.nextLine();
                //Send message to server on the
//socket's output stream…
                //Accept response from server on the
                //socket's intput stream…
                networkOutput.println(message);

                Thread t3 = new Thread();
                response = networkInput.nextLine();
                //Display server's response to user…
                System.out.println(
                        "\nSERVER> " + response);
            }while (!message.equals("QUIT"));
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println(
                        "\nClosing connection…");
                socket.close();
            }
            catch(IOException ioEx)
            {
                System.out.println(
                        "Unable to disconnect!");
                System.exit(1);
            }
        }
    }

    public void receiveMsg(Socket socket){
        try {
            networkInput = new Scanner(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        String response = networkInput.nextLine();
        //Display server's response to user…
        System.out.println(
                "\nSERVER> " + response);
    }

}