package main.service

import main.models.FileInfo
import org.springframework.security.access.prepost.PostFilter
import java.util.regex.*

/**
 * Created by chinchik_b on 10.11.2016.
 */
public interface FileInfoService {
    List<FileInfo> getFileListAdmin(String path)
    List<FileInfo> getFileListUser(String path)

    //@PostAuthorize("returnObject.fileSize == 32")
    @PostFilter ('filterObject.filePath.toLowerCase().contains(authentication.name.toString())')
    List<FileInfo> getFileList(String path)

    List<FileInfo> createFile(String path,String textBody)
}
