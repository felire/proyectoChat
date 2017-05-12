package com.example.felipe.chatvuelveacompilar;

import android.content.ContentValues;
import android.content.Context;

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
    DataInputStream streamIn = null;
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

    public void conectar() throws IOException{
            socket = new Socket(ip, puerto);
            streamIn = new DataInputStream(socket.getInputStream());
            streamOut = new DataOutputStream(socket.getOutputStream());
            streamOut.writeUTF(id); //Lo primero que mandamos es el id, asi el server los va registrando
            //System.out.println(streamIn.readUTF());
    }
    public void enviarMensaje(String idReceptor, String mensaje, Context context) throws IOException{
            System.out.println("Mandamos msj!!");
            streamOut.writeUTF(idReceptor);
            streamOut.writeUTF(mensaje);

        ContentValues registro;
        registro = new ContentValues();
        registro.put("user_id",id); //Es la id del dueño del fono
        registro.put("contacto_id",idReceptor);
        int idConver;
        DataBase baseDatos = new DataBase(context, "BASE_DATOS_CHAT", null, 2);
        if(baseDatos.existeConversacion(id, idReceptor)){ //agregamos msj a la conver
            idConver = baseDatos.darIdConversacion(id, idReceptor);
            registro.put("conversacion_id",idConver);
            registro.put("esDeUser", true);
            registro.put("mensaje",mensaje);
            baseDatos.getWritableDatabase().insert("MensajeXConv", null, registro);
        }else{ //Si no existe la conver la creamos y dsps agregamos el msj
            baseDatos.getWritableDatabase().insert("Conversacion",null,registro);
            registro = new ContentValues();
            registro.put("user_id",id); //Es la id del dueño del fono
            registro.put("contacto_id",idReceptor);
            idConver = baseDatos.darIdConversacion(id, idReceptor);
            registro.put("conversacion_id",idConver);
            registro.put("esDeUser", true);
            registro.put("mensaje",mensaje);
            baseDatos.getWritableDatabase().insert("MensajeXConv", null, registro);
        }
            System.out.println("Enviado");
    }

    public void recibirMensaje(Context context) throws IOException{
        while (true) {
                System.out.println("Vamos a ver si recibimos mensajes............");
                String emisorId = streamIn.readUTF();
                String mensaje = streamIn.readUTF();
                System.out.println("Mensaje de: " + emisorId + " y dice: " + mensaje);
                 ContentValues registro;
                 registro = new ContentValues();
                 registro.put("user_id",id); //Es la id del dueño del fono
                 registro.put("contacto_id",emisorId);
                 int idConver;
                 DataBase baseDatos = new DataBase(context, "BASE_DATOS_CHAT", null, 2);
                 if(baseDatos.existeConversacion(id, emisorId)){ //agregamos msj a la conver
                    idConver = baseDatos.darIdConversacion(id, emisorId);
                     registro.put("conversacion_id",idConver);
                     registro.put("esDeUser", false);
                     registro.put("mensaje",mensaje);
                     baseDatos.getWritableDatabase().insert("MensajeXConv", null, registro);
                 }else{ //Si no existe la conver la creamos y dsps agregamos el msj
                    baseDatos.getWritableDatabase().insert("Conversacion",null,registro);
                     registro = new ContentValues();
                     registro.put("user_id",id); //Es la id del dueño del fono
                     registro.put("contacto_id",emisorId);
                     idConver = baseDatos.darIdConversacion(id, emisorId);
                     registro.put("conversacion_id",idConver);
                     registro.put("esDeUser", false);
                     registro.put("mensaje",mensaje);
                     baseDatos.getWritableDatabase().insert("MensajeXConv", null, registro);
                 }
        }
    }


}
