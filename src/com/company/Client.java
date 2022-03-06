package com.company;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try {
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);
            String yazı;
            do {
                yazı = scanner.nextLine();
                byte[] buffer= yazı.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length,address,5000);
                socket.send(packet);

                byte[] buf = new byte[50];
                packet = new DatagramPacket(buf, buf.length,address,5000);
                socket.receive(packet);
                System.out.println("test client: "+ new String(buf,0,packet.getLength()));

            }while (!yazı.equals("exit"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
