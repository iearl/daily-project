package com.ants.dailyproject.fileupload.uploadservice;

import com.ants.dailyproject.fileupload.uploaddao.UploadDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Slf4j
public class ImgUpService {

    @Autowired
    private UploadDao uploadDaoImpl;

    public byte[] findImgByfileName(Map<String, Object> map) {
        try{
            return uploadDaoImpl.findImgByfileName(map);
        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    //把图片以流的形式存储在数据库
    public void uploadImgByStream(StandardMultipartHttpServletRequest req) throws Exception {
        Iterator<String> iterator = req.getFileNames();
        while (iterator.hasNext()) {
            MultipartFile file = req.getFile(iterator.next());
            Map<String, Object> map = new HashMap<>();
            String fileName = file.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            map.put("fileName", newFileName);
            map.put("fileStream", file.getInputStream());
            uploadDaoImpl.save(map);
        }
    }

    //文件上传到指定路径
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

