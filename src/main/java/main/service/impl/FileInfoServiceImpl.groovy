package main.service.impl

import groovy.io.FileType
import main.models.FileInfo
import main.service.FileInfoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
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

   /* @Autowired
    public UserDetailsService userDetailsService*/
    @Override
    List<FileInfo> getFileList(String path) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        //def profile = env.getActiveProfiles()

        final String NULLPARAM = ''
        final Integer ZERO = 0
        List<FileInfo> listFileInfo = []
        def file
        if (path == NULLPARAM) {
            path = new File(System.getProperty("user.dir"))
            logger.warn("Получен запрос без параметра, будет использована текущая директория: '${path}' ")
        }
        try {
            file = new File(path)
            file.eachFile(FileType.FILES) {
                listFileInfo << new FileInfo(filePath: it.absolutePath,
                        fileSize: it.size(), dateModification: new Date(it.lastModified()).format("MM/dd/yyyy HH:mm:ss"), fileText: it.getText().take(20))
            }
            if (listFileInfo.size() == ZERO) {
                logger.warn("Директория '${path}' не содержит файлов. Лист пустой.")
            } else {
                logger.debug("Запрос от пользователя: [ ${name} ]. Список файлов из директории '${path}' получен успешно. Колличество найденых файлов в папке: ${listFileInfo}")
            }
        } catch (FileNotFoundException ex) {

            logger.error("При попытке чтения директории '${path}' возникла ошибка, данной директории не существует!!!", ex)
          // throw new FileNotFoundException("not found")
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
