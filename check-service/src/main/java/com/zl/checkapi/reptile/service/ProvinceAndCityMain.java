package com.zl.checkapi.reptile.service;

import com.zl.checkapi.reptile.citypartition.FinalSingleBean;
import com.zl.checkapi.reptile.ioutil.ConnectUtil;
import com.zl.checkapi.reptile.ioutil.WriteToText;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 */
public class ProvinceAndCityMain {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceAndCityMain.class);

    private static final String PREFIX_Url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/";

    private static final Long TIME = 60*1000L;

    private final List<String> list1 = new ArrayList<>();

    private final List<String> list2 = new ArrayList<>();

    private final List<String> list3 = new ArrayList<>();

    private final List<String> list4 = new ArrayList<>();

    private final List<String> list5 = new ArrayList<>();

    private final List<String> list6 = new ArrayList<>();

    private final List<String> list7 = new ArrayList<>();

    private final List<String> list8 = new ArrayList<>();

    public static void main(String[] args){

        Long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(8);

        ProvinceAndCityMain provinceAndCityMain = new ProvinceAndCityMain();

        provinceAndCityMain.getOrginalProvinces();

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.newIOPool("pcds");

        Future<Integer> future1 = threadPoolExecutor.submit(() ->
        {
            int result = provinceAndCityMain.getProvinceStruct(provinceAndCityMain.list1, "one");
            countDownLatch.countDown();
            return result;
        });

        Future<Integer> future2 = threadPoolExecutor.submit(() ->
        {
            int result = provinceAndCityMain.getProvinceStruct(provinceAndCityMain.list2, "two");
            countDownLatch.countDown();
            return result;
        });

        Future<Integer> future3 = threadPoolExecutor.submit(() ->
        {
            int result = provinceAndCityMain.getProvinceStruct(provinceAndCityMain.list3, "three");
            countDownLatch.countDown();
            return result;
        });

        Future<Integer> future4 = threadPoolExecutor.submit(() ->
        {
            int result = provinceAndCityMain.getProvinceStruct(provinceAndCityMain.list4, "four");
            countDownLatch.countDown();
            return result;
        });

        Future<Integer> future5 = threadPoolExecutor.submit(() ->
        {
            int result = provinceAndCityMain.getProvinceStruct(provinceAndCityMain.list5, "five");
            countDownLatch.countDown();
            return result;
        });

        Future<Integer> future6 = threadPoolExecutor.submit(() ->
        {
            int result = provinceAndCityMain.getProvinceStruct(provinceAndCityMain.list6, "six");
            countDownLatch.countDown();
            return result;
        });

        Future<Integer> future7 = threadPoolExecutor.submit(() ->
        {
            int result = provinceAndCityMain.getProvinceStruct(provinceAndCityMain.list7, "seven");
            countDownLatch.countDown();
            return result;
        });

        Future<Integer> future8 = threadPoolExecutor.submit(() ->
        {
            int result = provinceAndCityMain.getProvinceStruct(provinceAndCityMain.list8, "eight");
            countDownLatch.countDown();
            return result;
        });

        try{
            countDownLatch.await(1, TimeUnit.HOURS);
            int size1 = future1.get();
            int size2 = future2.get();
            int size3 = future3.get();
            int size4 = future4.get();
            int size5 = future5.get();
            int size6 = future6.get();
            int size7 = future7.get();
            int size8 = future8.get();
            int total = size1+size2+size3+size4+size5+size6+size7+size8;
            Long end = System.currentTimeMillis();
            long time = (end-start)/TIME;
            logger.info("总共{}条记录", total);
            logger.info("总共耗时{}minutes", time);
            threadPoolExecutor.shutdown();
        }catch (Exception e){
            logger.error("数据获取失败!", e);
        }


    }

    private void getOrginalProvinces(){
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
                    String finalStr = finalPath + "_" + province ;
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
        }
    }

    /**
     * 获取省级数据结构
     * @param provinceList
     * @return
     */
    public int getProvinceStruct(List<String> provinceList, String num){
        Map<String, List<String>> provinceMap = new HashMap<>(16);
        //保存所有的url
        List<String> urlList = new ArrayList<>();
        if(provinceList!=null && provinceList.size()>0){
            for(int i=0;i<provinceList.size();i++){
                String[] str = provinceList.get(i).split("_");
                String provinceUrl = str[0];
                String province = str[1];
                //保存url-data String类型的数据结构
                List<String> structList = new ArrayList<>();
                //获取城市相关信息
                Document cityDocument = ConnectUtil.getConnect(provinceUrl);
                Elements elements = null;
                if(cityDocument!=null){
                    elements = cityDocument.select(".citytable");
                }
                Elements cityTrElements ;
                try{
                    cityTrElements = elements.get(0).select(".citytr");
                }catch (Exception e){
                    logger.error("获取数据失败=>{},url=>{}", elements.get(0), provinceUrl, e);
                    continue;
                }

                for(int j=0;j<cityTrElements.size();j++) {
                    Element element = cityTrElements.get(j);
                    Elements tdElements = element.getElementsByTag("td");
                    //第二条数据才是最终结果
                    Element resultElement = tdElements.get(1).getElementsByTag("a").get(0);
                    String cityUrl = resultElement.attr("href");
                    String finalUrl = PREFIX_Url + cityUrl;
                    String city = resultElement.text();
                    String finalStr = finalUrl+"_"+city;
                    //市url保存
                    urlList.add(finalStr);
                    structList.add(finalStr);
                }
                //保存省级数据结构
                provinceMap.put(province, structList);
                logger.info("province=>{}, structList=>{}", province, structList);
            }
        }
        return getCityStruct(urlList, provinceMap, num);
    }

    /**
     * 获取市级数据结构
     * @param cityList
     * @return
     */
    public int getCityStruct(List<String> cityList, Map<String, List<String>> provinceMap, String num){
        Map<String, List<String>> cityMap = new HashMap<>(16);
        //保存所有的url
        List<String> urlList = new ArrayList<>();
        if(cityList!=null && cityList.size()>0){
            for(int i=0;i<cityList.size();i++){
                String[] str = cityList.get(i).split("_");
                String cityUrl = str[0];
                String city = str[1];
                //保存url-data String类型的数据结构
                List<String> structList = new ArrayList<>();
                //获取区相关信息
                Document countyDocument = ConnectUtil.getConnect(cityUrl);
                Elements districtElements = null;
                if(countyDocument!=null){
                    districtElements = countyDocument.select(".countytable");
                }
                Element countyElement ;
                try{
                    countyElement = districtElements.get(0);
                }catch (Exception e){
                    logger.error("获取数据失败=>{}, url=>{}", districtElements.get(0),cityUrl, e);
                    continue;
                }
                Elements countyTrElements = countyElement.select(".countytr");
                for(int m=0;m<countyTrElements.size();m++) {
                    Elements countyAElement = countyTrElements.get(m).getElementsByTag("td").get(1).getElementsByTag("a");
                    if (StringUtils.isEmpty(countyAElement.html())) {
                        continue;
                    }
                    String countyUrl = countyAElement.attr("href");
                    String county = countyAElement.text();
                    int endIndex = cityUrl.lastIndexOf("/");
                    String finalUrl = cityUrl.substring(0, endIndex + 1) + countyUrl;
                    String finalStr = finalUrl+"_"+county;
                    structList.add(finalStr);
                    urlList.add(finalStr);
                }
                cityMap.put(city, structList);
                logger.info("city=>{}, structList=>{}", city, structList);
            }
        }
        return getCountyStruct(urlList, provinceMap, cityMap, num);
    }

    /**
     * 获取县级相关数据
     * @param countyList
     * @return
     */
    public int getCountyStruct(List<String> countyList, Map<String, List<String>> provinceMap, Map<String, List<String>> cityMap, String num){
        Map<String, List<String>> countyMap = new HashMap<>(16);
        //保存所有的url
        List<String> urlList = new ArrayList<>();
        if(countyList!=null && countyList.size()>0){
            for(int i=0;i<countyList.size();i++){
                String[] str = countyList.get(i).split("_");
                String countyUrl = str[0];
                String county = str[1];
                //保存url-data String类型的数据结构
                List<String> structList = new ArrayList<>();
                Document streetDocument = ConnectUtil.getConnect(countyUrl);
                Elements streetElements = null;
                if(streetDocument!=null){
                    streetElements = streetDocument.select(".towntable");
                }
                Element streetElement ;
                try{
                    streetElement = streetElements.get(0);
                }catch (Exception e){
                    logger.error("获取数据失败=>{}, url=>{}", streetElements.get(0),countyUrl, e);
                    continue;
                }
                Elements streetTrElement = streetElement.select(".towntr");
                for(int x=0;x<streetTrElement.size();x++) {
                    Elements streetTdElemnts = streetTrElement.get(x).getElementsByTag("td").get(1).getElementsByTag("a");
                    if (StringUtils.isEmpty(streetTdElemnts.html())) {
                        continue;
                    }
                    String streetUrl = streetTdElemnts.attr("href");
                    String street = streetTdElemnts.text();
                    int endIndex = countyUrl.lastIndexOf("/");
                    String finalUrl = countyUrl.substring(0, endIndex + 1) + streetUrl;
                    String finalStr = finalUrl+"_"+street;
                    structList.add(finalStr);
                    urlList.add(finalStr);
                }
                countyMap.put(county, structList);
                logger.info("county=>{}, structList=>{}", county, structList);
            }
        }
        return getTownStruct(urlList, provinceMap, cityMap, countyMap, num);
    }

    /**
     * 获取居委会相关信息
     * @param townList
     */
    public int getTownStruct(List<String> townList, Map<String, List<String>> provinceMap, Map<String, List<String>> cityMap, Map<String, List<String>> countyMap, String num){
        Map<String, List<String>> townMap = new HashMap<>(16);
        if(townList!=null && townList.size()>0){
            for(int i=0;i<townList.size();i++){
                String[] str = townList.get(i).split("_");
                String townUrl = str[0];
                String town = str[1];
                //保存url-data String类型的数据结构
                List<String> structList = new ArrayList<>();
                Document townDocument = ConnectUtil.getConnect(townUrl);
                Elements townElements = null;
                if(townDocument!=null){
                    townElements = townDocument.select(".villagetable");
                }
                Element townElement ;
                try{
                    townElement = townElements.get(0);
                }catch (Exception e){
                    logger.error("获取数据失败=>{}, url=>{}", townElements.get(0),townUrl, e);
                    continue;
                }
                Elements townTrElement = townElement.select(".villagetr");
                for(int x=0;x<townTrElement.size();x++) {
                    Elements townTdElemnts = townTrElement.get(x).getElementsByTag("td");
                    if (StringUtils.isEmpty(townTdElemnts.html())) {
                        continue;
                    }
                    String staticsAreaPartitionCode = townTdElemnts.get(0).text();
                    String cityClassificationCode = townTdElemnts.get(1).text();
                    String village = townTdElemnts.get(2).text();
                    String finalStr = staticsAreaPartitionCode+"_"+cityClassificationCode+"_"+village;
                    structList.add(finalStr);
                }
                townMap.put(town, structList);
                logger.info("town=>{}, structList=>{}", town, structList);
            }
        }
        //统计所有的数据
        List<FinalSingleBean> resultList = new ArrayList<>();
        for(Map.Entry entry : townMap.entrySet()){
            String keyTown = entry.getKey().toString();
            List<String> villageList = townMap.get(keyTown);
            for(int j=0;j<villageList.size();j++){
                FinalSingleBean finalSingleBean = new FinalSingleBean();
                String[] str = villageList.get(j).split("_");
                String staticsAreaPartitionCode = str[0];
                String cityClassificationCode = str[1];
                String village = str[2];
                finalSingleBean.setStaticsAreaPartitionCode(staticsAreaPartitionCode);
                finalSingleBean.setCityClassificationCode(cityClassificationCode);
                finalSingleBean.setVillage(village);
                finalSingleBean.setTown(keyTown);

                //找到county
                String county = getUpperData(keyTown, countyMap);
                //找到city
                String city = getUpperData(county, cityMap);
                //找到province
                String province = getUpperData(city, provinceMap);
                finalSingleBean.setCounty(county);
                finalSingleBean.setCity(city);
                finalSingleBean.setProvince(province);
                resultList.add(finalSingleBean);
            }
        }
        //写数据到文件
        WriteToText.write(resultList, num+".txt");
        return resultList.size();
    }

    /**
     * 通过value找到相关map的key
     * @param str
     * @param goalMap
     * @return
     */
    public String getUpperData(String str, Map<String, List<String>> goalMap){
        for(Map.Entry entry : goalMap.entrySet()){
            String key = entry.getKey().toString();
            List<String> resultList = goalMap.get(key);
            for(String string : resultList){
                if(str.equals(string.split("_")[1])){
                    return key;
                }
            }
        }
        return null;
    }

}
