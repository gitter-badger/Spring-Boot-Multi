package com.highcharts.common.excel;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>wx-qwwj/com.weixin.service</p>
 * Excel表格导出
 * @author Created by BruceZheng
 * @date 2017-12-12 17:34
 **/
@Service
public class ExcelOut<T> {
    /**
     *
     * @param response
     * @param list 需要转化的对象数据
     * @param tableHeader 表头一行
     * @return
     * @throws Exception
     */
    public HSSFWorkbook getCustomerExcel(HttpServletResponse response, List<T> list,String[] tableHeader) throws Exception {
       // new OutputStream();
        if (list.isEmpty()){
            HSSFWorkbook hssfWorkbook= null;
            return  hssfWorkbook;
        }
        T t;
        t = list.get(0);
        Class<?> aClass = t.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
//        for (int i = 0; i < declaredFields.length; i++) {
//            declaredFields[i].setAccessible(true);
//            Object o1 = declaredFields[i].get(t1);
//            String name = declaredFields[i].getName();
//            Class<?> type = declaredFields[i].getType();
//            String name1 = type.getName();
//            System.out.println(name + " = " + o1 + " 类型：" + type + "名字：" + name1);
//        }

        //List<Customer> customerList=customerService.getCustomersa();

        /*
         * 设置表头：对Excel每列取名(必须根据你取的数据编写)
         */
 //        String[] tableHeader = {"名字", "签到时间", "开会时间", "所属部门", "所属职位","是否迟到","是否请假","请假原由"};
        if (tableHeader.length == 0 || tableHeader == null){
             tableHeader = new String[declaredFields.length];
            for (int i = 0; i < declaredFields.length; i++) {
                tableHeader[i] = declaredFields[i].getName();
            }
        }
        /*
         * 下面的都可以拷贝不用编写
         */
        // 表的列数
        short cellNumber = (short) tableHeader.length;
        // 创建一个excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        // Excel的列
        HSSFCell cell = null;
        // Excel的行
        HSSFRow row = null;
        // 设置表头的类型
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置数据类型
        HSSFCellStyle style1 = workbook.createCellStyle();
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 创建一个sheet
        HSSFSheet sheet = workbook.createSheet("sheet1");
        // 设置sheet的头
        HSSFHeader header = sheet.getHeader();
        try {
            /**
             * 根据是否取出数据，设置header信息
             *
             */
            if (list.size() < 1) {
                header.setCenter("查无资料");
            } else {
                header.setCenter("公司签到表");
                row = sheet.createRow(0);
                row.setHeight((short) 400);
                for (int k = 0; k < cellNumber; k++) {
                    // 创建第0行第k列
                    cell = row.createCell(k);
                    // 设置第0行第k列的值
                    cell.setCellValue(tableHeader[k]);
                    // 设置列的宽度
                    sheet.setColumnWidth(k, 5000);
                    // 设置单元格字体的颜色.
                    font.setColor(HSSFFont.COLOR_NORMAL);
                    // 设置单元字体高度
                    font.setFontHeight((short) 200);
                    // 设置字体风格
                    style1.setFont(font);
                    cell.setCellStyle(style1);
                }

                /*
                 * // 给excel填充数据这里需要编写
                 */
                for (int i = 0; i < list.size(); i++) {
                    T tt;
                    tt = list.get(i);
                    Class<?> aClasss = tt.getClass();
                    Object t11 = tt;
                    // 创建第i+1行
                    row = sheet.createRow((short) (i + 1));
                    // 设置行高
                    row.setHeight((short) 400);
                    //获取对象所有属性
                    Field[] fields = aClasss.getDeclaredFields();
                    for (int j = 0; j < fields.length; j++) {
                        fields[j].setAccessible(true);
                        //获取对象
                        Object o1 = fields[j].get(t11);
                        //遍历设置一个对象中所有属性值
                        if (o1 != null) {
                            // 创建第i+1行第0列
                            cell = row.createCell(j);
                            // 设置第i+1行第0列的值
                            Class<?> type = fields[j].getType();
                            String name1 = type.getName();
                            if (name1.equals("java.lang.String")){
                                cell.setCellValue(((String) fields[j].get(t11)));
                            }
                            if (name1.equals("java.util.Date")){
                                SimpleDateFormat startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                cell.setCellValue(startDate.format(((Date) fields[j].get(t11))));
                            }
                            // 设置风格
                            cell.setCellStyle(style);
                        }
                    }

                }

/*                for (int i = 0; i < customerList.size(); i++) {
                    Customer lis = (Customer) customerList.get(i);// 获取company对象
                    row = sheet.createRow((short) (i + 1));// 创建第i+1行
                    row.setHeight((short) 400);// 设置行高

                    if (lis.getC_id() != 0) {
                        cell = row.createCell(0);// 创建第i+1行第0列
                        cell.setCellValue(lis.getC_id());// 设置第i+1行第0列的值
                        cell.setCellStyle(style);// 设置风格
                    }
                    if (lis.getC_companyname() != null) {
                        cell = row.createCell(1);
                        cell.setCellValue(lis.getC_companyname());
                        cell.setCellStyle(style);
                    }
                    if (lis.getC_linkman() != null) {//自己设置 的联系人不能为空
                        cell = row.createCell(2); // 创建第i+1行第1列

                        cell.setCellValue(lis.getC_linkman());// 设置第i+1行第1列的值

                        cell.setCellStyle(style); // 设置风格
                    }
                    // 由于下面的和上面的基本相同，就不加注释了

                    if (lis.getC_startdate() != null) {
                        cell = row.createCell(3);
                        SimpleDateFormat startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        cell.setCellValue(startDate.format(lis.getC_startdate()));
                        cell.setCellStyle(style);
                    }
                    if (lis.getC_phone() != null) {
                        cell = row.createCell(4);
                        cell.setCellValue(lis.getC_phone());
                        cell.setCellStyle(style);
                    }
                    if (lis.getC_mobilephone() != null) {
                        cell = row.createCell(5);
                        cell.setCellValue(lis.getC_mobilephone());
                        cell.setCellStyle(style);
                    }
                    if (lis.getC_address() != null) {
                        cell = row.createCell(6);
                        cell.setCellValue(lis.getC_address());
                        cell.setCellStyle(style);
                    }

                    if (lis.getC_intro() != null) {
                        cell = row.createCell(7);
                        cell.setCellValue(lis.getC_intro());
                        cell.setCellStyle(style);
                    }
                    if (lis.getC_comment() != null) {
                        cell = row.createCell(8);
                        cell.setCellValue(lis.getC_comment());
                        cell.setCellStyle(style);
                    }
                    if (lis.getC_update() != null) {
                        cell = row.createCell(9);
                        SimpleDateFormat update = new SimpleDateFormat("yyyy-MM-dd");
                        cell.setCellValue(update.format(lis.getC_update()));
                        cell.setCellStyle(style);
                    }
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
         * 下面的可以不用编写，直接拷贝
         */

//        OutputStream out = null;// 创建一个输出流对象
//        try {
//
//
//            out = response.getOutputStream();
//            response.setHeader("Content-disposition", "attachment; filename="
//                    + aClass.getName()+"_out.xls");
//            // filename是下载的xls的名，建议最好用英文
//            // 设置类型
//            response.setContentType("application/msexcel;charset=UTF-8");
//            // 设置头
//            response.setHeader("Pragma", "No-cache");
//            // 设置头
//            response.setHeader("Cache-Control", "no-cache");
//            // 设置日期头
//            response.setDateHeader("Expires", 0);
//            workbook.write(out);
//            out.flush();
//            workbook.write(out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return workbook;

    }

}
