/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 *
 * @author Sam
 */
public class utils {
    
    public static void showErrMsg(JFrame frame, String msg) {
        JOptionPane.showMessageDialog(frame, msg);
        Logger.getLogger("Builder").log(Level.INFO, msg);
    }

    /**
    * Read the file and calculate the SHA-1 checksum
    * 
    * @param file
    *            the file to read
    * @return the hex representation of the SHA-1 using uppercase chars
    * @throws FileNotFoundException
    *             if the file does not exist, is a directory rather than a
    *             regular file, or for some other reason cannot be opened for
    *             reading
    * @throws IOException
    *             if an I/O error occurs
    * @throws NoSuchAlgorithmException
    *             should never happen
    */
    public static String calcSHA1(File file) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
       MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
       try (InputStream input = new FileInputStream(file)) {
           byte[] buffer = new byte[8192];
           int len = input.read(buffer);

           while (len != -1) {
               sha1.update(buffer, 0, len);
               len = input.read(buffer);
           }
           return new HexBinaryAdapter().marshal(sha1.digest());
       }
    }
}
