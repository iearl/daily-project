package com.ants.dailyproject.fileupload.uploaddao;

import java.util.Map;

public interface UploadDao {
    int save(Map<String,Object> map) throws Exception;

    byte[] findImgByfileName(Map<String,Object> map) throws Exception;
}
