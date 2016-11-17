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

    String getFilePath() {
        return filePath
    }

    void setFilePath(String filePath) {
        this.filePath = filePath
    }

    Long getFileSize() {
        return fileSize
    }

    void setFileSize(Long fileSize) {
        this.fileSize = fileSize
    }

    String getDateModification() {
        return dateModification
    }

    void setDateModification(String dateModification) {
        this.dateModification = dateModification
    }

    String getFileText() {
        return fileText
    }

    void setFileText(String fileText) {
        this.fileText = fileText
    }
}
