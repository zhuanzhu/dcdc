package com.egeo.components.order.common;

import com.egeo.utils.StringUtils;
import com.egeo.utils.excel2.ExcelTmplConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/13 13:51
 * @Version V1.0
 **/
public class ExcelCheckHelper {

    public ExcelCheckHelper() {
    }

    public static String chechHeader(List<Map<String, Object>> list, int tmplType, boolean containCompany) {
        Map<String, Object> row0 = (Map)list.get(0);
        String tmplName;
        tmplName = row0.get("CELL1").toString();
        if (StringUtils.isBlank(tmplName)) {
            return "订单编号为空";
        }

        tmplName = row0.get("CELL2").toString();
        if (StringUtils.isBlank(tmplName)) {
            return "子订单编号为空";
        }
        tmplName = row0.get("CELL3").toString();
        if (StringUtils.isBlank(tmplName)) {
            return "快递单号";
        }

        tmplName = row0.get("CELL4").toString();
        if (StringUtils.isBlank(tmplName)) {
            return "物流公司";
        }
        //Map<String, Object> row1 = (Map)list.get(1);
        if (!columnValid(tmplType, row0, tmplName)) {
            return "导入文件字段与模板类型要求的字段不一致";
        }
        return null;
    }

    private static boolean columnValid(int tmplType, Map<String, Object> row1, String tmplName) {
        ExcelTemplateEnum t = ExcelTemplateEnum.translate(tmplType);
        /*if (!t.getTmplName().equals(tmplName)) {
            return false;
        } else {*/
            String[] carr = t.getColumnArr();

            for(int i = 0; i < carr.length; ++i) {
                Object tt = row1.get("CELL" + (i + 1));
                if (carr[i] != null && !carr[i].replace("*", "").equals(tt == null ? "" : tt.toString().replace("*", ""))) {
                    return false;
                }
            }

            return true;
        //}
    }

}
