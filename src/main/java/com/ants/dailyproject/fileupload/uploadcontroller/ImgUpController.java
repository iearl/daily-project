package com.ants.dailyproject.fileupload.uploadcontroller;

import com.ants.dailyproject.fileupload.uploadservice.ImgUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("uploadController")
public class ImgUpController {

    @Autowired
    private ImgUpService imgUpService;

    @GetMapping("/showImgByFileName")
    public void showImgByFileName(String fileName, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("fileName", fileName);
        byte[] fileDowmload = imgUpService.findImgByfileName(map);
        if(fileDowmload==null){
            //查询不到图片
            return;
        }
        fileName = fileName.substring(fileName.lastIndexOf('.'));
        if ("jpg".equals(fileName.substring(1)) || "jpeg".equals(fileName.substring(1))) {
            response.setContentType("image/jpeg; charset=utf-8");
        } else {
            response.setContentType("image/" + fileName.substring(1) + "; charset=utf-8");
        }
        BufferedImage image = null;
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            image = ImageIO.read(new ByteArrayInputStream(fileDowmload));
            ImageIO.write(image, fileName.substring(1), out);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (image != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }
    }


    @PostMapping("uploadImgByStream")
    @ResponseBody
    public String uploadImgByStream(HttpServletRequest request) {
        try {
            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            imgUpService.uploadImgByStream(req);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @PostMapping("uploadImg")
    @ResponseBody
    public String uploadImg(HttpServletRequest request) {
        try {
            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            imgUpService.uploadImg(req);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
