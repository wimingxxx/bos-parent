package com.qwm.bos.web.action;

import com.qwm.bos.domain.Region;
import com.qwm.bos.domain.Subarea;
import com.qwm.bos.service.ISubareaService;
import com.qwm.bos.utils.FileUtils;
import com.qwm.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 下午8:55
 * @className: SubareaAction
 * @description:
 * 分区管理
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService subareaService;

    /**
     * 添加分区
     * @return
     */
    public String add(){
        subareaService.save(model);
        return LIST;
    }


    /**
     * 分页查询
     * @return
     */
    public String pageQuery(){
        DetachedCriteria dc = pageBean.getDetachedCriteria();
        String addresskey = model.getAddresskey();
        //动态添加过滤条件
        if(StringUtils.isNotBlank(addresskey)){
            //添加过滤条件，根据地址关键字模糊查询
            dc.add(Restrictions.like("addresskey","%"+addresskey+"%"));
        }
        Region region = model.getRegion();
        if(region!=null) {
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            //添加过滤条件，根据省份模糊查询-----多表关联查询，使用别名方式实现
            //参数一：分区对象中关联的区域对象属性名称
            //参数二：别名，可以任意
            dc.createAlias("region","r");
            if(StringUtils.isNotBlank(province)){
                dc.add(Restrictions.like("r.province", "%"+province+"%"));
            }
            if(StringUtils.isNotBlank(city)){
                dc.add(Restrictions.like("r.city", "%"+city+"%"));
            }
            if(StringUtils.isNotBlank(district)){
                dc.add(Restrictions.like("r.district", "%"+district+"%"));
            }
        }
        subareaService.pageQuery(pageBean);
        java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzone"});
        return NONE;
    }

    /**
     *分区数据导出功能
     * @return
     */
    public String exportXls() throws IOException {
        //第一步，查询所有的分区数据
        List<Subarea> list = subareaService.findAll();

        //第二步，使用POI将数据写入到Excel文件中
        //1创建工作簿对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //2 创建sheet
        HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");

        //创建标题
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("分区编号");
        headRow.createCell(1).setCellValue("开始编号");
        headRow.createCell(2).setCellValue("结束编号");
        headRow.createCell(3).setCellValue("位置信息");
        headRow.createCell(4).setCellValue("省市区");

        for (Subarea subarea : list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(subarea.getId());
            dataRow.createCell(1).setCellValue(subarea.getStartnum());
            dataRow.createCell(2).setCellValue(subarea.getEndnum());
            dataRow.createCell(3).setCellValue(subarea.getPosition());
            dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
        }

        //第三步，使用输出流进行文件下载
        String fileName = "分区数据.xls";
        //获取MIME类型
        String contentType = ServletActionContext.getServletContext().getMimeType(fileName);
        ServletActionContext.getResponse().setContentType(contentType);

        //对文件名进行处理---
        //获取浏览器类型
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        //文件名进行编码处理
        fileName = FileUtils.encodeDownloadFilename(fileName,agent);
        ServletActionContext.getResponse().setHeader("content-disposition","attachment;filename="+fileName);

        //获取流输出
        hssfWorkbook.write( ServletActionContext.getResponse().getOutputStream() );

        return NONE;
    }

    /**
     * 查询所有未关联的定区的分区,返回json
     * @return
     */
    public String listajax(){
        List<Subarea> list = subareaService.findListNotAssociation();
        java2Json(list,new String[]{"decidezone","region"});
        return NONE;
    }
}
