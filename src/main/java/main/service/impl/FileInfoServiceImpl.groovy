package main.service.impl

import groovy.io.FileType
import main.models.FileInfo
import main.service.FileInfoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
/**
 * Created by chinchik_b on 10.11.2016.
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

    public Logger logger = LoggerFactory.getLogger(this.class.getName())
    @Override
    List<FileInfo> getFileListAdmin(String path) {

        List<FileInfo> listFileInfo = []
        def file = new File(path)
        file.eachFile(FileType.FILES) {
            listFileInfo << new FileInfo(filePath: it.absolutePath,
                    fileSize: it.size(), dateModification: new Date(it.lastModified()).format("MM/dd/yyyy HH:mm:ss"), fileText: it.getText())
        }
        //logger.debug("лист для админа получен" )
        return listFileInfo
    }

    @Override
    List<FileInfo> getFileListUser(String path) {
        List<FileInfo> listFileInfo = []
       // def list =[]
        def file = new File(path)
        file.eachFile(FileType.FILES) {
            listFileInfo << new FileInfo(filePath: it.absolutePath,
                    fileSize: it.size(), dateModification: new Date(it.lastModified()).format("MM/dd/yyyy HH:mm:ss"), fileText: it.getText())

            listFileInfo = listFileInfo.findAll{it.fileSize>32}
        }
        return listFileInfo
    }

    @Override
    List<FileInfo> getFileList(String path) {
        List<FileInfo> listFileInfo = []
       def file

        try {
             file = new File(path)
        //    def count = file.listFiles().length.toInteger()

       //          if (count ==0){logger.warn(String.format("Папка %s не содержит файлов",path))}
        //    else{
                 file.eachFile(FileType.FILES) {
                     listFileInfo << new FileInfo(filePath: it.absolutePath,
                             fileSize: it.size(), dateModification: new Date(it.lastModified()).format("MM/dd/yyyy HH:mm:ss"), fileText: it.getText())
            //     }
                     def count = file.listFiles().length.toInteger()
                     if (count ==0){logger.warn(String.format("Папка %s не содержит файлов",path))}
            }
        } catch (FileNotFoundException ex) { logger.error(String.format("При попытке открытия папки %s возникла ошибка, данной папки не существует!!!",path))
        }

        if (listFileInfo.size()== 0 ){
            logger.warn("Возвращаемый лист пустой!")}
       else{ logger.debug("лист получен и отдан нормально")}
        return listFileInfo
    }

    @Override
    List<FileInfo> createFile(String path, String textBody) {

        File file = new File(path)
        file.write(textBody)
        FileInfo fileInfo = new FileInfo(filePath: file.getAbsolutePath(), fileSize: file.size(),
                dateModification: new Date(file.lastModified()).format("MM/dd/yyyy HH:mm:ss"), fileText: file.getText())
        def listFileInfo = [fileInfo]
        return listFileInfo
    }
}
