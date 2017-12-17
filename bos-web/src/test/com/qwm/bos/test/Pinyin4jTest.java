package com.qwm.bos.test;

import com.qwm.bos.utils.PinYin4jUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 下午1:57
 * @className: Pinyin4jTest
 * @description:
 */
public class Pinyin4jTest {

    /**
     * Pinyin4j测试
     */
   // @Test
    public void test(){
        //河北省	石家庄市	长安区
        String province = "河北省";
        String city = "石家庄市";
        String district = "长安区";

        //去掉省市区
        province = province.substring(0,province.length()-1);
        city = city.substring(0,city.length()-1);
        district = district.substring(0,district.length()-1);

        String info = province + city + district;

        //简码---> HBSJZCA
        String[] headByString = PinYin4jUtils.getHeadByString(info);
        //获取简码
        String shortcode = StringUtils.join(headByString);
        System.out.println(shortcode);

        //城市编码--->shijiazhuang
        String citycode = PinYin4jUtils.hanziToPinyin(city,"");
        System.out.println(citycode);
    }
}
