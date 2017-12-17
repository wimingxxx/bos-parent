package com.qwm.bos.web.action;

import com.qwm.bos.domain.Region;
import com.qwm.bos.service.IRegionService;
import com.qwm.bos.web.action.base.BaseAction;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 上午4:06
 * @className: RegionAction
 * @description: 区域
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

    @Autowired
    private IRegionService regionService;

    //使用属性驱动接收伤处的文件
    private File regionFile;
    /**
     * 上传文件
     * @return
     */
    public String importXls() throws IOException {
        List<Region> regionList = new ArrayList<Region>();
        //创建一个工作簿(包装了一个Excel文件对象)
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream( regionFile ));
        //读取文件中的第一个Sheet标签页  可以通过 Sheet名称或者索引获取到.
        HSSFSheet hssfSheet = workbook.getSheetAt(0);
        //遍历便签也中的每一行
        for (Row row: hssfSheet) {
            //第一行为标题,我们可以不遍历第一行
            if(row.getRowNum()==0)
                continue;
            //上传的文件是按要求上传的,所以我们可以直接读取对应的单元格
            //区域编号	省份	城市	区域	邮编
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();

            //创建区域对象
            Region region = new Region(id,province,city,district,postcode,null,null,null);
            regionList.add(region);
        }
        //现在我们把读取的数据插入到数据库中
        regionService.saveBatch(regionList);
        return NONE;
    }

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

}
