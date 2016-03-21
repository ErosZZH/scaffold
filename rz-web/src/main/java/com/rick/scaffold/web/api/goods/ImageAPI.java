package com.rick.scaffold.web.api.goods;

import com.rick.scaffold.exception.APIException;
import com.rick.scaffold.web.api.generic.BaseAPI;
import net.mikesu.fastdfs.FastdfsClient;
import net.mikesu.fastdfs.FastdfsClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by user on 16/3/21.
 */
@Controller
@RequestMapping(value="/api/v1/image",produces={"application/json;charset=UTF-8"})
public class ImageAPI extends BaseAPI {

    @Autowired
    private FastdfsClientFactory fcf;

    @RequestMapping(value = "/upload", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> upload(HttpServletRequest request,
                                      @RequestParam(value = "file", required = true) MultipartFile[] file)
            throws APIException {
        File convFile = new File(file[0].getOriginalFilename());
        String fileId = "";
        try {
            file[0].transferTo(convFile);
            fcf.getFastdfsClient();
            FastdfsClient fastdfsClient = fcf.getFastdfsClient();
            fileId = fastdfsClient.upload(convFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Write file fail");
        }
        return responseSuccess(fileId);
    }
}
