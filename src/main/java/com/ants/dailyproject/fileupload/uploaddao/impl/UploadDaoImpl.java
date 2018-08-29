package com.ants.dailyproject.fileupload.uploaddao.impl;

import com.ants.dailyproject.fileupload.uploaddao.UploadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UploadDaoImpl implements UploadDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Map<String, Object> map) throws Exception{
        return jdbcTemplate.update("insert into uploadimg(file_name, file_stream) values(?, ?)",
                new Object[]{map.get("fileName"), map.get("fileStream")});
    }

    @Override
    public byte[] findImgByfileName(Map<String, Object> map)  throws Exception{
        return jdbcTemplate.queryForObject("SELECT file_stream FROM uploadimg WHERE file_name=?", new Object[]{map.get("fileName")}, byte[].class);
    }

}
