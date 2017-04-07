package com.example.felipe.chatvuelveacompilar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by root on 04/04/17.
 */

public class ConnectionClient {
    public Socket socket;
    private DataOutputStream streamOut = null;
    private DataInputStream streamIn = null;
    String ip;
    int puerto;
    String id;
    public ConnectionClient(String ip,int puerto, String id){
        this.ip = ip;
        this.puerto = puerto;
        this.id = id;
    }

    public void conectar(){
        try {
            socket = new Socket(ip, puerto);
            streamIn = new DataInputStream(socket.getInputStream());
            streamOut = new DataOutputStream(socket.getOutputStream());
            System.out.println(id);
            streamOut.writeUTF(id); //Lo primero que mandamos es el id, asi el server los va registrando
            //System.out.println(streamIn.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
