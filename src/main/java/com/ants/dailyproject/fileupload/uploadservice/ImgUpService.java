package com.ants.dailyproject.fileupload.uploadservice;

import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class ImgUpService {

    public void uploadImg(List<FileItem> itemList) throws Exception {
        System.out.println("----------------------1111111111111111--"+itemList.size());
        for (FileItem item : itemList) {
            //判断输入的类型是 普通输入项 还是文件
            if (item.isFormField()) {
                //普通输入项 ,得到input中的name属性的值
                String name = item.getFieldName();
                System.out.println("------------------------"+name);
                //得到输入项中的值
                String value = item.getString("UTF-8");
                System.out.println("name=" + name + "  value=" + value);
            } else {
                //上传的是文件，获得文件上传字段中的文件名
                //注意IE或FireFox中获取的文件名是不一样的，IE中是绝对路径，FireFox中只是文件名。
                System.out.println("----------------"+ item.getName());
                String fileName = item.getName();
                System.out.println(fileName);
                //返回表单标签name属性的值
                String namede = item.getFieldName();
                System.out.println(namede);

                //方法一：保存上传文件到指定的文件路径
                InputStream is = item.getInputStream();
                FileOutputStream fos = new FileOutputStream("E:\\wps\\" + fileName);
                byte[] buff = new byte[1024];
                int len = 0;
                while ((len = is.read(buff)) > 0) {
                    fos.write(buff);
                }

                //方法二：保存到指定的路径
                //将FileItem对象中保存的主体内容保存到某个指定的文件中。
                // 如果FileItem对象中的主体内容是保存在某个临时文件中，该方法顺利完成后，临时文件有可能会被清除
                item.write(new File("E:\\sohucache\\" + fileName));
                is.close();
                fos.close();
            }
        }
    }
}

