package main.service.impl

import groovy.io.FileType
import main.models.FileInfo
import main.service.FileInfoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
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

            listFileInfo = listFileInfo.findAll { it.fileSize > 32 }
        }
        return listFileInfo
    }


    @Override
    List<FileInfo> getFileList(String path) {
        //def profile = env.getActiveProfiles()

        final String NULLPARAM = ''
        final Integer ZERO = 0
        List<FileInfo> listFileInfo = []
        def file
        if (path == NULLPARAM) {
            path = new File(System.getProperty("user.dir"))
            logger.warn("Получен Get запрос без параметра, будет использована текущая директория " + path)
        }
        try {
            file = new File(path)
            file.eachFile(FileType.FILES) {
                listFileInfo << new FileInfo(filePath: it.absolutePath,
                        fileSize: it.size(), dateModification: new Date(it.lastModified()).format("MM/dd/yyyy HH:mm:ss"), fileText: it.getText().take(20))
            }
            if (listFileInfo.size() == ZERO) {
                logger.warn(String.format("Директория '%s' не содержит файлов. Лист пустой.", path))
            } else {
                logger.debug(String.format("Список файлов из директории '%s' получен успешно.", path))
            }
        } catch (FileNotFoundException ex) {
            logger.error(String.format("При попытке чтения директории '%s' возникла ошибка, данной директории не существует!!!", path) + ex.toString())

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
