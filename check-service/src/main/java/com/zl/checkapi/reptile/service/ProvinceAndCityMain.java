package com.zl.checkapi.reptile.service;

import com.alibaba.fastjson.JSONObject;
import com.zl.checkapi.reptile.ioutil.ConnectUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.CORBA.MARSHAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhouliang
 */
public class ProvinceAndCityMain {

    private final Logger logger = LoggerFactory.getLogger(ProvinceAndCityMain.class);

    private static final String PREFIX_Url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/";

    private final List<String> list1 = new ArrayList<>();

    private final List<String> list2 = new ArrayList<>();

    private final List<String> list3 = new ArrayList<>();

    private final List<String> list4 = new ArrayList<>();

    private final List<String> list5 = new ArrayList<>();

    private final List<String> list6 = new ArrayList<>();

    private final List<String> list7 = new ArrayList<>();

    private final List<String> list8 = new ArrayList<>();

    public static void main(String[] args){

        ProvinceAndCityMain provinceAndCityMain = new ProvinceAndCityMain();

        provinceAndCityMain.getProvinces();

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.newIOPool("pcds");

        provinceAndCityMain.getCities();
    }

    private void getProvinces(){
        List<String> provinceList = new ArrayList(31);
        String mainUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/index.html";
        Document mainDocument = ConnectUtil.getConnect(mainUrl);

        Element bodyElement = null;
        if(mainDocument!=null){
            bodyElement  = mainDocument.body();
        }else{
            logger.error("文档内容为空! 正退出");
            System.exit(0);
        }
        //class属性provincetable只有一个
        Elements mainElements = bodyElement.select(".provincetable");

        if(mainElements!=null && mainElements.size()>0){
            Element element = mainElements.get(0);
            //class属性provincetr共有四个
            Elements trElements = element.select(".provincetr");

            for (Element trElement : trElements) {
                Elements tdElement = trElement.children();
                for (Element aTdElement : tdElement) {
                    String province = aTdElement.select("a").get(0).html().replace("<br>", "");
                    String orgPath = aTdElement.select("a").attr("href");
                    String finalPath = PREFIX_Url + orgPath;
                    String finalStr = province +"_"+ finalPath;
                    provinceList.add(finalStr);
                }
            }
            //数据分成8组
            for(int i=0;i<provinceList.size();i++){
                if(i<4){
                    list1.add(provinceList.get(i));
                }
                if(i>=4 && i< 8){
                    list2.add(provinceList.get(i));
                }
                if(i>=8 && i< 12){
                    list3.add(provinceList.get(i));
                }
                if(i>=12 && i< 16){
                    list4.add(provinceList.get(i));
                }
                if(i>=16 && i< 20){
                    list5.add(provinceList.get(i));
                }
                if(i>=20 && i< 24){
                    list6.add(provinceList.get(i));
                }
                if(i>=24 && i< 28){
                    list7.add(provinceList.get(i));
                }
                if(i>=28){
                    list8.add(provinceList.get(i));
                }
            }

            System.out.println(JSONObject.toJSONString(provinceList));
        }
    }

    private void getCities(){
        List<String> provinceList = list1;
        //最终结果保存
        Map<String, List<Map<String, List<Map<String, List<Map<String, List<String>>>>>>>> resultMap = new HashMap<>();
        for(int i= 0;i< provinceList.size();i++){
            //定义保存市的集合
            List<Map<String, List<Map<String, List<Map<String, List<String>>>>>>> cityList = new ArrayList<>();
            //市url保存
            List<String> cityUrlList = new ArrayList<>();
            String[] resultStr = provinceList.get(i).split("_");
            String province = resultStr[0];
            String provinceUrl = resultStr[1];
            //将市保存至省
            resultMap.put(province, cityList);
            //获取城市相关信息
            Document cityDocument = ConnectUtil.getConnect(provinceUrl);
            Elements elements = null;
            if(cityDocument!=null){
                elements = cityDocument.select(".citytable");
            }
            Elements cityTrElements = elements.get(0).select(".citytr");
            for(int j=0;j<cityTrElements.size();j++){
                Element element = cityTrElements.get(j);
                Elements tdElements = element.getElementsByTag("td");
                //第二条数据才是最终结果
                Element resultElement = tdElements.get(1).getElementsByTag("a").get(0);
                String cityUrl = resultElement.attr("href");
                String finalUrl = PREFIX_Url+cityUrl;
                String city = resultElement.text();
                //市url保存
                cityUrlList.add(finalUrl);
                //定义保存市的map
                Map<String, List<Map<String, List<Map<String, List<String>>>>>> cityMap = new HashMap<>();
                //定义保存区的集合
                List<Map<String, List<Map<String, List<String>>>>> districtList = new ArrayList<>();
                cityMap.put(city, districtList);
                cityList.add(cityMap);
                //获取区相关信息
                for(int k=0;k<cityUrlList.size();k++){
                    //当前市的url
                    String currentCityUrl = cityUrlList.get(k);
                    //区url保存
                    List<String> countyUrlList = new ArrayList<>();
                    Document districtDocument = ConnectUtil.getConnect(currentCityUrl);
                    Elements districtElements = null;
                    if(districtDocument!=null){
                        districtElements = districtDocument.select(".countytable");
                    }
                    Element countyElement = districtElements.select(".countytable").get(0);
                    Elements countyTrElements = countyElement.select(".countytr");
                    for(int m=0;m<countyTrElements.size();m++){
                        Elements countyAElement = countyTrElements.get(m).getElementsByTag("td").get(1).getElementsByTag("a");
                        if(StringUtils.isEmpty(countyAElement.html())){
                            continue;
                        }
                        String countyUrl = countyAElement.attr("href");
                        String county = countyAElement.text();
                        //定义保存区的map
                        Map<String, List<Map<String, List<String>>>> districtMap = new HashMap<>();
                        //定义保存街道的集合
                        List<Map<String, List<String>>> streetList = new ArrayList<>();
                        districtMap.put(county, streetList);
                        districtList.add(districtMap);

                        int endIndex = currentCityUrl.lastIndexOf("/");
                        String finalStrictUrl = currentCityUrl.substring(0, endIndex+1)+countyUrl;
                        System.out.println(finalStrictUrl);
                        countyUrlList.add(finalStrictUrl);

                        //获取街道相关信息
                        for(int n=0;n<countyUrlList.size();n++){
                            //当前街道的url
                            String currentDistrictUrl = countyUrlList.get(n);
                            //街道url保存
                            List<String> streetUrlList = new ArrayList<>();
                            Document streetDocument = ConnectUtil.getConnect(currentDistrictUrl);
                            Elements streetElements = null;
                            if(streetDocument!=null){
                                streetElements = streetDocument.select(".towntable");
                            }
                            Element streetElement = streetElements.select(".towntable").get(0);
                            Elements streetTrElement = streetElement.select(".towntr");
                            for(int x=0;x<streetTrElement.size();x++){
                                Elements streetTdElemnts = streetTrElement.get(x).getElementsByTag("td").get(1).getElementsByTag("a");
                                if(StringUtils.isEmpty(streetTdElemnts.html())){
                                    continue;
                                }
                                String streetUrl = streetTdElemnts.attr("href");
                                String street = streetTdElemnts.text();
                                //定义保存街道的map
                                Map<String, List<String>> streetMap = new HashMap<>();
                                //定义保存居委会或村委会的集合
                                List<String> rvList = new ArrayList<>();
                                streetMap.put(street, rvList);
                                streetList.add(streetMap);
                            }
                        }
                    }

                }
            }
        }
        System.out.println(JSONObject.toJSONString(resultMap));
    }
}
