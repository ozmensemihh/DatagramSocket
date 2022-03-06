package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5000);
            while (true){
                byte[] yazı = new byte[50];
                DatagramPacket packet = new DatagramPacket(yazı, yazı.length);
                socket.receive(packet);
                System.out.println("Text : "+new String(yazı,0,packet.getLength()));

                String buffer2 = "echo "+  new String(yazı,0,packet.getLength());
                byte[] buf2 = buffer2.getBytes(StandardCharsets.UTF_8);
                InetAddress adres = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf2, buf2.length,adres,port);
                socket.send(packet);

            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
