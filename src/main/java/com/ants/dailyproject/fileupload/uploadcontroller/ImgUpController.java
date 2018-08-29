package com.ants.dailyproject.fileupload.uploadcontroller;

import com.ants.dailyproject.fileupload.uploadservice.ImgUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("uploadController")
public class ImgUpController {

    @Autowired
    private ImgUpService imgUpService;

    @RequestMapping("test")
    public String test() {
        return "jsp/index";
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
