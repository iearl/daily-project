package com.ants.dailyproject.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *测试文件上传
 */
@Controller
@RequestMapping("testUploadController")
public class Main {
    @RequestMapping("testUploadImg")
    public String test() {
        return "uploadImg/index";
    }
}
