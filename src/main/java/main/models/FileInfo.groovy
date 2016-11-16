package main.models

import groovy.transform.ToString

import javax.xml.bind.annotation.XmlRootElement
/**
 * Created by chinchik_b on 09.11.2016.
 */
@ToString(includeNames = true)
@XmlRootElement(name = "FileInfo")
class FileInfo {
    String filePath
    Long fileSize
    String dateModification
    String fileText


}
