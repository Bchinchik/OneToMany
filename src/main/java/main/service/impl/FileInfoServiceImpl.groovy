package main.service.impl

import groovy.io.FileType
import main.models.FileInfo
import main.service.FileInfoService
import org.springframework.stereotype.Service

/**
 * Created by chinchik_b on 10.11.2016.
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Override
    List<FileInfo> getFileListAdmin(String path) {

        List<FileInfo> listFileInfo = []
        def file = new File(path)
        file.eachFile(FileType.FILES) {
            listFileInfo << new FileInfo(filePath: it.absolutePath,
                    fileSize: it.size(), dateModification: new Date(it.lastModified()).format("MM/dd/yyyy HH:mm:ss"), fileText: it.getText())
        }
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
        def file = new File(path)
        file.eachFile(FileType.FILES) {
            listFileInfo << new FileInfo(filePath: it.absolutePath,
                    fileSize: it.size(), dateModification: new Date(it.lastModified()).format("MM/dd/yyyy HH:mm:ss"), fileText: it.getText())
        }
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
