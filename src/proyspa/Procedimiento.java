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
public class Procedimiento {
    private int cod_pro;
    private String tipo_pro;
    private String descripcion_pro;
    private String duracion_pro;
    private double valor_pro;
    
    public Procedimiento(){
        cod_pro=0;
        tipo_pro="";
        descripcion_pro="";
        duracion_pro="";
        valor_pro=0.0;
    }
    
    public Procedimiento(int cod_pro,String tipo_pro,String descripcion_pro,String duracion_pro,double valor_pro){
        this.cod_pro=cod_pro;
        this.tipo_pro=tipo_pro;
        this.descripcion_pro=descripcion_pro;
        this.duracion_pro=duracion_pro;
        this.valor_pro=valor_pro;
    }
    
    public int getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(int cod_pro) {
        this.cod_pro = cod_pro;
    }

    public String getTipo_pro() {
        return tipo_pro;
    }

    public void setTipo_pro(String tipo_pro) {
        this.tipo_pro = tipo_pro;
    }

    public String getDescripcion_pro() {
        return descripcion_pro;
    }

    public void setDescripcion_pro(String descripcion_pro) {
        this.descripcion_pro = descripcion_pro;
    }

    public String getDuracion_pro() {
        return duracion_pro;
    }

    public void setDuracion_pro(String duracion_pro) {
        this.duracion_pro = duracion_pro;
    }

    public double getValor_pro() {
        return valor_pro;
    }

    public void setValor_pro(double valor_pro) {
        this.valor_pro = valor_pro;
    }
    
    public boolean nuevoProcedimiento(Connection cn){
        boolean resp=false;
        try{
            String sql="INSERT INTO Procedimiento(tipo_pro,descripcion_pro,duracion_pro,valor_pro)VALUES(?,?,?,?)";
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setString(1,tipo_pro);
            cmd.setString(2,descripcion_pro);
            cmd.setString(3,duracion_pro);
            cmd.setDouble(4,valor_pro);
            
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
    
    public boolean modificarProcedimiento(Connection cn){
        boolean resp=false;
        try{
            String sql="UPDATE Procedimiento SET tipo_pro=?,descripcion_pro=?,duracion_pro=?,valor_pro=? WHERE cod_pro=?" ; 
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setInt(1, cod_pro);
            cmd.setString(2,tipo_pro);
            cmd.setString(3,descripcion_pro);
            cmd.setString(4,duracion_pro);
            cmd.setDouble(5,valor_pro);
             
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
    
    public boolean consultarProcedimiento(Connection cn){
        boolean resp=false;
        try{
            String sql= "SELECT *FROM Procedimiento WHERE cod_pro=?" ;
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setInt(1,cod_pro);
            ResultSet rs=cmd.executeQuery();
            if(rs.next()){
                resp=true;
                cod_pro=rs.getInt(1);
                tipo_pro=rs.getString(2);
                descripcion_pro=rs.getString(3);
                duracion_pro=rs.getString(4);
                valor_pro=rs.getDouble(5);
      
            } 
            cmd.close();

        } 
        catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());   
        } 
        return resp;
    }
    
    public boolean eliminarProcedimiento(Connection cn){
        boolean resp=false;
        try{
            String sql="DELETE FROM Procedimiento WHERE cod_pro=?" ;
            PreparedStatement cmd=cn.prepareStatement(sql);
            cmd.setInt(1,cod_pro);
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
