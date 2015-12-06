/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import gui.window;
import builder.ModFile;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Sam
 */
public class Builder {

    public static Logger logger;
    public static FileHandler fileHand;
    private static String dataFolder;
    private static boolean mkdir;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            JAXBContext jc = JAXBContext.newInstance(ModFile.class);

            ModFile file = new ModFile();
            file.setName("@CBA_A3");
            file.setFolder("@CBA_A3");
            file.setHash("@CBA_A3");

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(file, System.out);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        /*
        * Display The Launcher Window
        */
        EventQueue.invokeLater(() -> {
            new window().setVisible(true);
        });
        
        /**
         * Get users APPDATA folder
         */
        dataFolder = System.getenv("APPDATA");
        File folder = new File(dataFolder + "\\RPP\\Builder");
        if (!folder.exists()) {
           mkdir = folder.mkdir();
        }

        try {      
            setupLogger();
        } catch (IOException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
           
    /**
    * Setup a Logger
    * @throws java.io.IOException
    */ 
    public static void setupLogger() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());

        File logFile = new File(dataFolder + "\\RPP\\Builder" + timeStamp + ".log");
        logFile.createNewFile();
        
        logger = Logger.getLogger("Builder");
        fileHand = new FileHandler(dataFolder + "\\RPP\\Builder" + timeStamp + ".log");
        logger.addHandler(fileHand);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHand.setFormatter(formatter);
    }
    
}
