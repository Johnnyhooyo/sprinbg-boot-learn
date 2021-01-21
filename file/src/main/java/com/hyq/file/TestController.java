package com.hyq.file;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author dibulidohu
 * @classname TestController
 * @date 2019/4/219:31e
 * @description
 */
@RestController
public class TestController {

    @ResponseBody
    @PostMapping("/cbs/v1/balanceSearch")
    public void skjfd(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {
        //如果是这个类型的请求
        if (ServletFileUpload.isMultipartContent(servletRequest)) {
            MultipartHttpServletRequest httpServletRequest = (MultipartHttpServletRequest) servletRequest;
            Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
            //去文件类型的formdata
            MultipartFile file = httpServletRequest.getFile("file");
            //取其他类型的formdata
            String providerCode = parameterMap.get("providerCode")[0];
            String staff = parameterMap.get("staff")[0];
            String pageNo = parameterMap.get("pageNo")[0];
            String pageSize = parameterMap.get("pageSize")[0];
            String[] split = file.getOriginalFilename().split("\\.");
            List<List<String>> lists = ExcelUtils.readExcel(file.getInputStream(), split[split.length - 1], 1);
            for (List<String> list : lists) {
                for (String s : list) {
                    System.out.print(s + "\t");
                }
                System.out.println();
            }
        }
    }

    @ResponseBody
    @GetMapping("/cbs/v1/test")
    public void skjsfd(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {
        TestObject testObject = new TestObject();
    }
}
