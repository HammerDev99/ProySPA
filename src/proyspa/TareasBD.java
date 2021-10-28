/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyspa;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author Daniel
 */
public class TareasBD {
    Connection CN;
    
    public TareasBD(){
        CN=null;
    }
    
    public Connection getConectar(){
        return CN;
    }
    
    public boolean Conectar(){
        boolean respuesta = false;
        String BD = "DB_Spa";
        String direc="jdbc:sqlserver://Daniel-PC;databaseName=" + BD + ";user=sa;password=1234";
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");        
        }
        catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Error del Driver", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error del Driver");
            System.out.println(ex.getMessage());
        }
        
        try{
            CN = DriverManager.getConnection(direc);
            respuesta = true;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al conectar a la Base de Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al Conectar");
            System.out.println(ex.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Conexión lista a " + BD, "Conexión", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Conexión lista a " + BD);
        return respuesta;
    }
    
    public static void configuraColumnas(final ResultSet rs, final DefaultTableModel modelo){
        try{
            ResultSetMetaData metaDatos = rs.getMetaData();
            int numeroColumnas = metaDatos.getColumnCount();
            Object[] etiquetas = new Object[numeroColumnas];
            for (int i = 0; i < numeroColumnas; i++)
            {
                     etiquetas[i] = metaDatos.getColumnLabel(i + 1);
            }
            modelo.setColumnIdentifiers(etiquetas);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private static void borrarFilasModelo(final DefaultTableModel modelo){
        try{
            while (modelo.getRowCount() > 0){	
                modelo.removeRow(0);
            }
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private static void adicionarFilasModelo(ResultSet rs,DefaultTableModel modelo){
        int numeroFila = 0;
        try{
            while (rs.next()){
                Object[] datosFila = new Object[modelo.getColumnCount()];
                for (int i = 0; i < modelo.getColumnCount(); i++){
                	datosFila[i] = rs.getObject(i + 1);
                }                    
                modelo.addRow(datosFila);
                numeroFila++;
            }
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public ResultSet consultar(String consulta){
        ResultSet rs = null;  
        try{
            Statement s = CN.createStatement();
            rs = (ResultSet) s.executeQuery(consulta.trim());
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    
    public void listar(String s, DefaultTableModel modelo){
       java.sql.ResultSet rs =consultar(s);
       if( rs!=null){
           mostrarRs(rs, modelo);
       }   
    }    
    
    public void mostrarRs(ResultSet rs, DefaultTableModel modelo){
        configuraColumnas(rs, modelo);
        borrarFilasModelo(modelo);
        adicionarFilasModelo(rs, modelo);
    }
    
    public void cierraConexion(){
        try{
            CN.close();
            JOptionPane.showMessageDialog(null, "Base de datos desconectada", "Desconexión", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Base de datos desconectada");
        }
        catch(Exception e){
            e.printStackTrace();   
        }
    }
    
    public boolean nuevoCliente(Cliente obj){
        return  obj.nuevoCliente(CN);
    }
    
    public boolean modificarCliente(Cliente obj){
        return  obj.modificarCliente(CN);
    }
    
    public boolean consultarCliente(Cliente obj){
        return  obj.consultarCliente(CN);
    }
    
    public boolean eliminarCliente(Cliente obj){
        return  obj.eliminarCliente(CN);
    }
    
    public boolean nuevoProcedimiento(Procedimiento obj){
        return  obj.nuevoProcedimiento(CN);
    }
    
    public boolean modificarProcedimiento(Procedimiento obj){
        return  obj.modificarProcedimiento(CN);
    }
    
    public boolean consultarProcedimiento(Procedimiento obj){
        return  obj.consultarProcedimiento(CN);
    }
    
    public boolean eliminarProcedimiento(Procedimiento obj){
        return  obj.eliminarProcedimiento(CN);
    }
    
    public boolean nuevoActividad(Agenda obj){
        return  obj.nuevoActividad(CN);
    }
    
    public boolean modificarActividad(Agenda obj){
        return  obj.modificarActividad(CN);
    }
    
    public boolean consultarActividad(Agenda obj){
        return  obj.consultarActividad(CN);
    }
    
    public boolean eliminarActividad(Agenda obj){
        return  obj.eliminarActividad(CN);
    }
    
}
