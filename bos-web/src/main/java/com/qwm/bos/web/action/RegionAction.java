package com.qwm.bos.web.action;

import com.qwm.bos.domain.Region;
import com.qwm.bos.service.IRegionService;
import com.qwm.bos.utils.PinYin4jUtils;
import com.qwm.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
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

           //去掉省市区
            province = province.substring(0,province.length()-1);
            city = city.substring(0,city.length()-1);
            district = district.substring(0,district.length()-1);
            String info = province + city + district;
            //简码
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            //获取简码
            String shortcode = StringUtils.join(headByString);
            //城市编码
            String citycode = PinYin4jUtils.hanziToPinyin(city,"");
            //设置简码和城市编码
            region.setShortcode(shortcode);
            region.setCitycode(citycode);
            regionList.add(region);
        }
        //现在我们把读取的数据插入到数据库中
        regionService.saveBatch(regionList);
        return NONE;
    }

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    /**
     * 分页查询
     * @return
     * @throws Exception
     */
    public String pageQuery() throws Exception{
        regionService.pageQuery(pageBean);
        java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
        return NONE;
    }

    //区域下拉搜索的参数,使用属性驱动
    private String q;

    /**
     * 区域下拉搜索
     * @return
     */
    public String listajax(){
        List<Region> list = null;
        //判断,如果搜索的参数不为空,我们使用搜索的方法
        //如果为空,我们使用查询全部的方法
        if(StringUtils.isNotBlank(q)){
            list = regionService.findByQ(q);
        }else{
            list = regionService.findAll();
        }
        java2Json(list,new String[]{"subareas"});
        return NONE;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
