package com.ants.dailyproject.fileupload.uploadutil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("testUploadController")
public class Main {
    @RequestMapping("testUploadImg")
    public String test() {
        return "uploadImg/index";
    }
}