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
public class Agenda {
    private String fecha_A;
    private String hora_A;
    private String cedula;
    private String cod_pro;
    
    public Agenda(){
        fecha_A="";
        hora_A="";
        cedula="";
        cod_pro="";
    }
    
    public Agenda(String fecha_A,String hora_A,String cedula,String cod_pro){
        this.fecha_A=fecha_A;
        this.hora_A=hora_A;
        this.cedula=cedula;
        this.cod_pro=cod_pro;
    }

    public String getFecha_A() {
        return fecha_A;
    }

    public void setFecha_A(String fecha_A) {
        this.fecha_A = fecha_A;
    }

    public String getHora_A() {
        return hora_A;
    }

    public void setHora_A(String hora_A) {
        this.hora_A = hora_A;
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }
    
    public boolean nuevoActividad(Connection cn){
        boolean resp=false;
        try{
            String sql="INSERT INTO Agenda(fecha_A,hora_A,cedula,cod_pro)VALUES(?,?,?,?)";
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setString(1,fecha_A);
            cmd.setString(2,hora_A);
            cmd.setString(3, cedula);
            cmd.setString(4, cod_pro);
            
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

    public boolean modificarActividad(Connection cn){
        boolean resp=false;
        try{
            String sql="UPDATE Agenda SET cedula=?,cod_pro=? WHERE fecha_A=? and hora_A=?"; 
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setString(1,fecha_A);
            cmd.setString(2,hora_A);
            cmd.setString(3,cedula);
            cmd.setString(4,cod_pro);
             
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
    
    public boolean consultarActividad(Connection cn){
        boolean resp=false;
        try{
            String sql= "SELECT *FROM Agenda WHERE fecha_A=? and hora_A=?" ;
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setString(1,fecha_A);
            cmd.setString(2, hora_A);
            ResultSet rs=cmd.executeQuery();
            if(rs.next()){
                resp=true;
                fecha_A=rs.getString(1);
                hora_A=rs.getString(2);
                cedula=rs.getString(3);
                cod_pro=rs.getString(4);
      
            } 
            cmd.close();

        } 
        catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());   
        } 
        return resp;
    }
    
    public boolean eliminarActividad(Connection cn){
        boolean resp=false;
        try{
            String sql="DELETE FROM Agenda WHERE fecha_A=? and hora_A=?" ;
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setString(1,fecha_A);
            cmd.setString(2, hora_A);
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
