/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyspa;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jvnet.substance.SubstanceLookAndFeel;
/**
 *
 * @author 
 */
public class Main 
{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) 
    {
        try
        {
            UIManager.setLookAndFeel(new SubstanceLookAndFeel());
            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.MagmaSkin");
            SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceColorBlindTheme" );
            SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceMetalWallWatermark");
            //SubstanceLookAndFeel.setCurrentBorderPainter("org.jvnet.substance.border.ClassicBorderPainter");
            //SubstanceLookAndFeel.setCurrentButtonShaper("org.jvnet.substance.button.StandardButtonShaper");
	    frmSpa.setDefaultLookAndFeelDecorated(true);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERROR"+e.getMessage());
        }
        
        
        frmSpa obj=new frmSpa();
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
        obj.setExtendedState(frmSpa.MAXIMIZED_BOTH);
    }
}
