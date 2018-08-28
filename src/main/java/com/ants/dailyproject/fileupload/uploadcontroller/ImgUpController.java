package com.ants.dailyproject.fileupload.uploadcontroller;


import com.ants.dailyproject.fileupload.uploadservice.ImgUpService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.security.ProtectionDomain;
import java.util.List;

@RestController
@RequestMapping("uploadController")
public class ImgUpController {

    @Autowired
    private ImgUpService imgUpService;

    @RequestMapping("test")
    public String test(){
        return "success";
    }

    @PostMapping("uploadImg")
    public String uploadImg(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");//设置服务器端编码
        response.setContentType("text/html;charset=utf-8");//设置浏览器端解码

        //创建一个解析器工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置编码格式
        upload.setHeaderEncoding("UTF-8");
        //解析请求，将表单中每个输入项封装成一个FileItem对象
        try {
            List<FileItem> itemList = upload.parseRequest(request);
            imgUpService.uploadImg(itemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
