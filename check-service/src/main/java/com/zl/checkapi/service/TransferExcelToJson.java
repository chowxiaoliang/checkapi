package com.zl.checkapi.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zl.checkapi.util.ExcelUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouliang on 2017/9/18.
 */
public class TransferExcelToJson {

    private String getPath(){
        try{
            File file = new File("E:\\checkapi\\check-service\\src\\main\\resources\\files\\芝麻返回详细数据20171027.xlsx");
            return file.getAbsolutePath();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对json字符串格式化输出
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }
        return sb.toString();
    }

    /**
     * 添加space
     * @param sb
     * @param indent
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

    public static void main(String[] args){

        TransferExcelToJson transferExcelToJson = new TransferExcelToJson();
        try{
            String path = transferExcelToJson.getPath();
            Map<String, List<Map<String, String>>> result = ExcelUtils.readExcel(path);
            List<Map<String, String>> list = result.get("Sheet1");

            JSONObject recordsJson = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
            for(int i=1;i<list.size();i++){
                JSONObject innerJson = new JSONObject();
                String biz_date = list.get(i).get("COLUMN_0")!=null?simpleDateFormat.format(new Date(Long.valueOf(list.get(i).get("COLUMN_0")))):"";
                String linked_merchant_id = "2088621838967215";
                String user_credentials_type = list.get(i).get("COLUMN_2")!=null?list.get(i).get("COLUMN_2"):"";
                String user_credentials_no = list.get(i).get("COLUMN_3")!=null?list.get(i).get("COLUMN_3"):"";
                String user_name = list.get(i).get("COLUMN_4")!=null?list.get(i).get("COLUMN_4"):"";
                String order_no = list.get(i).get("COLUMN_5")!=null?list.get(i).get("COLUMN_5"):"";
                String scene_type = list.get(i).get("COLUMN_6")!=null?list.get(i).get("COLUMN_6"):"";
                String scene_desc = list.get(i).get("COLUMN_7")!=null?list.get(i).get("COLUMN_7"):"";
                String scene_status = list.get(i).get("COLUMN_8")!=null?list.get(i).get("COLUMN_8"):"";
                String create_amt = list.get(i).get("COLUMN_9")!=null?new DecimalFormat("#.00").format(Double.valueOf(list.get(i).get("COLUMN_9"))):"";
                String installment_due_date = list.get(i).get("COLUMN_10")!=null?simpleDateFormat.format(new Date(Long.valueOf(list.get(i).get("COLUMN_10")))):"";
                String overdue_amt = list.get(i).get("COLUMN_11")!=null?(new DecimalFormat("#.00")).format(Double.valueOf(list.get(i).get("COLUMN_11"))):"";
                String gmt_ovd_date = list.get(i).get("COLUMN_12")!=null?simpleDateFormat.format(new Date(Long.valueOf(list.get(i).get("COLUMN_12")))):"";
                String rectify_flag = list.get(i).get("COLUMN_13")!=null?list.get(i).get("COLUMN_13"):"";
                String memo = "";
                //将数据做成json格式
                innerJson.put("biz_date", biz_date);
                innerJson.put("linked_merchant_id", linked_merchant_id);
                innerJson.put("user_credentials_type", user_credentials_type);
                innerJson.put("user_credentials_no", user_credentials_no);
                innerJson.put("user_name", user_name);
                innerJson.put("order_no", order_no);
                innerJson.put("scene_type", scene_type);
                innerJson.put("scene_desc", scene_desc);
                innerJson.put("scene_status", scene_status);
                innerJson.put("create_amt", create_amt);
                innerJson.put("installment_due_date", installment_due_date);
                innerJson.put("overdue_amt", overdue_amt);
                innerJson.put("gmt_ovd_date", gmt_ovd_date);
                innerJson.put("rectify_flag", rectify_flag);
                innerJson.put("memo", memo);
                jsonArray.add(innerJson);
            }
            recordsJson.put("records", jsonArray);
            String resultJson = recordsJson.toJSONString();
            String finalStr = formatJson(resultJson);
            System.out.println(finalStr);
            FileWriter fileWriter = new FileWriter("E:\\checkapi\\check-service\\src\\main\\resources\\files\\fileNew.json");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(finalStr);
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
