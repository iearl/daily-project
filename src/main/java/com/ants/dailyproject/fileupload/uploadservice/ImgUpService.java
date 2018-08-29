package com.ants.dailyproject.fileupload.uploadservice;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;


@Service
public class ImgUpService {

    public void uploadImg(StandardMultipartHttpServletRequest req) throws Exception {
        Iterator<String> iterator = req.getFileNames();
        while (iterator.hasNext()) {
            MultipartFile file = req.getFile(iterator.next());
            String fileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();
            String destPath = "E:/12";
            File file1 = new File(destPath);
            //如果文件夹不存在则创建
            if (!file1.exists() && !file1.isDirectory()) {
                file1.mkdirs();
            }
            File targetFile = new File(file1, fileName);
            if (!targetFile.exists()) {
                file.transferTo(targetFile);
            }
        }
    }
}

