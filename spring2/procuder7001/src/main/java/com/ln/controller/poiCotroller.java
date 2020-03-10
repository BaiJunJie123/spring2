package com.ln.controller;

import com.ln.utils.poi.PoiUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 白俊杰
 * @Date 2019/12/24
 * @Description
 **/
@RestController
public class poiCotroller {
    @RequestMapping("/readDoc")
    public String readDoc(String patName){
      String data =  PoiUtils.readDoc(patName);
      return data;
    }
    //读docx
    @RequestMapping("/readDocx")
    public String readDocx(String patName){
        String data =  PoiUtils.readDocx(patName);
        return data;
    }
    // 读xls
    @RequestMapping("/readXls")
    public String readXls(String patName){
        String data =  PoiUtils.readXls(patName);
        return data;
    }
    //读xlsx
    @RequestMapping("/readXlsx")
    public String readXlsx(String patName){
        String data =  PoiUtils.readXlsx(patName);
        return data;
    }
}
