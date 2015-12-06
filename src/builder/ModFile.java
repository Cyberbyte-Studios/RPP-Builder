/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sam
 */
@XmlRootElement
public class ModFile {
    private String folder;
    private String name;
    private String hash;
    
    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @XmlAttribute
    public void setFolder(String folder) {
        this.folder = folder;
    }
    
    public String getFolder() {
        return folder;
    }
    
    @XmlAttribute
    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public String getHash() {
        return hash;
    }
    
}