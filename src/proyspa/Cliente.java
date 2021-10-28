/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyspa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Daniel
 */
public class Cliente {
    private String cedula;
    private String nombre;
    private String telefono;
    private String celular;
    private String email;
    
    public Cliente(){
        cedula="";
        nombre="";
        telefono="";
        celular="";
        email="";
    }
    
    public Cliente(String cedula,String nombre,String telefono,String celular,String email){
        this.cedula=cedula;
        this.nombre=nombre;
        this.telefono=telefono;
        this.celular=celular;
        this.email=email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean nuevoCliente(Connection cn){
        boolean resp=false;
        try{
            String sql="INSERT INTO Cliente(cedula,nombre,telefono,celular,email)VALUES(?,?,?,?,?)";
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setString(1,cedula);
            cmd.setString(2,nombre);
            cmd.setString(3,telefono);
            cmd.setString(4, celular);
            cmd.setString(5, email);
            
            if(!cmd.execute()){
                resp=true;  
            }
            cmd.close();
        }
        catch(Exception ex){
            System.out.println("Error al guardar: "+ex.getMessage());
        }
        return resp;
    }
    
    public boolean modificarCliente(Connection cn){
        boolean resp=false;
        try{
            String sql="UPDATE Cliente SET nombre=? ,telefono=?,celular=?,email=? WHERE cedula=?" ; 
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setString(1,cedula);
            cmd.setString(2,nombre);
            cmd.setString(3,telefono);
            cmd.setString(4, celular);
            cmd.setString(5, email);
             
            if(!cmd.execute()){
                resp=true;
            }
            cmd.close();
        }
        catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());   
        } 
        return resp;
    }
    
    public boolean consultarCliente(Connection cn){
        boolean resp=false;
        try{
            String sql= "SELECT *FROM Cliente WHERE cedula=?" ;
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setString(1,cedula);
            ResultSet rs=cmd.executeQuery();
            if(rs.next()){
                resp=true;
                cedula=rs.getString(1);
                nombre=rs.getString(2);
                telefono=rs.getString(3);
                celular=rs.getString(4);
                email=rs.getString(5);
      
            } 
            cmd.close();
        } 
        catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());   
        } 
        return resp;
    }
    
    public boolean eliminarCliente(Connection cn){
        boolean resp=false;
        try{
            String sql="DELETE FROM Cliente WHERE cedula=?" ;
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setString(1,cedula);
            if(!cmd.execute()){
                resp=true;   
            } 
            cmd.close();
     
        } 
        catch(Exception ex){
            System.out.println("Error:"+ex.getMessage());   
        }
        return resp;
    }
}
