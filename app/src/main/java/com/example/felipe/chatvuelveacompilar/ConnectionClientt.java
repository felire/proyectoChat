package com.example.felipe.chatvuelveacompilar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by root on 07/04/17.
 */

class ConnectionClientt {
    private static final ConnectionClientt ourInstance = new ConnectionClientt();
    public Socket socket;
    private DataOutputStream streamOut = null;
    private DataInputStream streamIn = null;
    String ip;
    int puerto;
    String id;

    public void setIp(String _ip){
        ip = _ip;
    }
    public void setPuerto(int puerto_){
        puerto = puerto_;
    }
    public void setId(String id_){
        id = id_;
    }
    static ConnectionClientt getInstance() {
        return ourInstance;
    }

    private ConnectionClientt() {
    }

    public void conectar(){
        try {
            System.out.println("vamos a crear Socket");
            socket = new Socket(ip, puerto);
            System.out.println("Creamos Socket, vamos a los input");
            streamIn = new DataInputStream(socket.getInputStream());
            streamOut = new DataOutputStream(socket.getOutputStream());
            System.out.println("Creamos input y outputs");
            System.out.println(id);
            streamOut.writeUTF(id); //Lo primero que mandamos es el id, asi el server los va registrando
            //System.out.println(streamIn.readUTF());
            System.out.println("Mandamos el id!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void enviarMensaje(String idReceptor, String mensaje){
        try {
            streamOut.writeUTF(idReceptor);
            streamOut.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
