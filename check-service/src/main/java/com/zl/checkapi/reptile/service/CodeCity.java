package com.zl.checkapi.reptile.service;

import com.alibaba.fastjson.JSON;
import com.zl.checkapi.reptile.citypartition.CodeVillages;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeCity {
	public Document getDocument(String url) {
		try {
			return Jsoup.connect(url).timeout(50000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		CodeCity t=new CodeCity();
		Document province = t.getDocument("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2014/index.html");
		Elements provincetr = province.select("[class=provincetr]");
		List<String> provinces=getProvince(provincetr);//得到所有省份的URL
		int i=0;
		long start=System.currentTimeMillis();
		for(String pro:provinces){
//		String pro="11.html";
			String proNum=pro.substring(0,2);//得到省号
			Document city = t.getDocument("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2014/"+pro);
			Elements citytr = city.select("[class=citytr]");
			List<String> citys=getCity(citytr);//得到所有市的URL
			for(String ct:citys){
				Document county = t.getDocument("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2014/"+ct);
				Elements countytr = county.select("[class=countytr]");
				List<String> countys=getCounty(countytr);//得到所有的区县
				for(String cy:countys){
					String cityNum=cy.substring(0, 2);//得到城市号
					Document town = t.getDocument("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2014/"+proNum+"/"+cy);
					Elements towntr = town.select("[class=towntr]");
					List<CodeVillages> towns=getTown(towntr);//得到所有的街道
					for(CodeVillages tn:towns){
						Document village = t.getDocument("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2014/"+proNum+"/"+cityNum+"/"+tn.getC_code());
						Elements villagetr = village.select("[class=villagetr]");
						List<CodeVillages> villages=getVillage(villagetr);//得到所有的街道
						for(CodeVillages cvg:villages){
							cvg.setC_town_name(tn.getC_town_name());
						}
						System.out.println(JSON.toJSONString(villages));
						System.out.println(++i);
					}
				}
			}
		}
			
		long end=System.currentTimeMillis();
		System.out.println("用时："+(end-start)/1000+"秒");
		
		
		
//		Document doc15 = t.getDocument("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2014/44.html");
//		Elements elements = doc15.select("[class=citytr]");
//		System.out.println(JSON.toJSONString(getCity(elements)));
		
//		Document doc15 = t.getDocument("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2014/44/4403.html");
//		Elements elements = doc15.select("[class=countytr]");
//		System.out.println(JSON.toJSONString(getCounty(elements)));
		
//		Document doc15 = t.getDocument("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2014/44/03/440304.html");
//		Elements elements = doc15.select("[class=towntr]");
//		System.out.println(JSON.toJSONString(getTown(elements)));
		
//		Document doc15 = t.getDocument("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2014/44/03/04/440304002.html");
//		Elements elements = doc15.select("[class=villagetr]");
//		System.out.println(JSON.toJSONString(getVillage(elements)));
		
	}

	
	public static List<String> getProvince(Elements elements1){
		List<String> list=new ArrayList<String>();
		//解析目标代码
		for(int i=0;i<elements1.size();i++){
			Element elements2 = elements1.get(i);
			Elements td = elements2.select("td");
			for(int j=0;j<td.size();j++){
				Element element3=td.select("a").get(j);
				String url=element3.attr("href");
				list.add(url);
			}
		}
		return list;
	}
	
	public static List<String> getCity(Elements elements1){
		List<String> list=new ArrayList<String>();
		//解析目标代码
		for(int i=0;i<elements1.size();i++){
			Element elements2 = elements1.get(i);
			Elements td = elements2.select("td");
			Element element3=td.select("a").get(0);
			String url=element3.attr("href");
			list.add(url);
		}
		return list;
	}
	
	public static List<String> getCounty(Elements elements1){
		List<String> list=new ArrayList<String>();
		//解析目标代码
		for(int i=0;i<elements1.size();i++){
			Element elements2 = elements1.get(i);
			Elements td = elements2.select("td");
			Elements element3=td.select("a");
			if(element3==null||element3.isEmpty()){
				continue;
			}
			String url=element3.get(0).attr("href");
			list.add(url);
		}
		return list;
	}
	
	public static List<CodeVillages> getTown(Elements elements1){
		List<CodeVillages> list=new ArrayList<CodeVillages>();
		//解析目标代码
		for(int i=0;i<elements1.size();i++){
			Element elements2 = elements1.get(i);
			Elements td = elements2.select("td");
			Element element3=td.select("a").get(1);
			String url=element3.attr("href");
			CodeVillages cv=new CodeVillages();
			cv.setC_town_name(element3.text());
			cv.setC_code(url);
			list.add(cv);
		}
		return list;
	}
	
	public static List<CodeVillages> getVillage(Elements elements1){
		List<CodeVillages> list=new ArrayList<CodeVillages>();
		//解析目标代码
		for(int i=0;i<elements1.size();i++){
			Element elements2 = elements1.get(i);
			Elements td = elements2.select("td");
			CodeVillages cv=new CodeVillages();
			String c_code= td.get(0).text();
			cv.setC_code(c_code);
			cv.setC_county(c_code.substring(0, 6));
			cv.setC_town(c_code.substring(0, 9)+"000");
			cv.setC_type(td.get(1).text());
			cv.setC_name(td.get(2).text());
			list.add(cv);
		}
		return list;
	}
	
}
