package okooo;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageTest {

    private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws ParseException {

        String url = "http://www.okooo.com/zucai/19111/";
        Document document = OkoooConnecUtil.postConnect(url);

        // 最终结果
        List<SuperAmbition> resultList = new ArrayList<>();

        if(document != null){
            Element bodyElement = document.body();
            Elements periodElements = bodyElement.select(".qihao_infor");
            // 期数
            String periods = periodElements.get(0).html().split("期")[0];

            Elements salesElements = bodyElement.select(".history_info");
            Element salesElement = salesElements.get(0);
            Elements salesSpanElements = salesElement.select("span");

            Element salesSpanElementOne = salesSpanElements.get(0);
            // 总销售额
            String totalSales = salesSpanElementOne.getElementById("SaleInfoSale").html();

            Element salesSpanElementTwo = salesSpanElements.get(1);
            // 一等奖注数
            String firstPrizeNum = salesSpanElementTwo.getElementById("SaleInfoFirstHitNum").html();
            // 一等奖金额
            String firstPrize = salesSpanElementTwo.getElementById("SaleInfoFirstWager").html();

            Element salesSpanElementThree = salesSpanElements.get(2);
            // 二等奖注数
            String secondPrizeNum = salesSpanElementThree.getElementById("SaleInfoFirstHitNum").html();
            // 二等奖金额
            String secondPrize = salesSpanElementThree.getElementById("SaleInfoFirstWager").html();

            Elements nextPondElements = bodyElement.select(".sqgc");
            // 滚存到下期
            String nextPond = nextPondElements.get(0).select("em").html();
            nextPond = nextPond.replace(",", "");

            Element tbodyElement = bodyElement.select("tbody").last();
            Elements trElements = tbodyElement.select("tr");
            for (Element trElement : trElements) {
                Elements tdElements = trElement.select("td");
                // 该赛事在本期次里面的顺序
                String gameOrder = tdElements.get(0).html();

                Element aElement = tdElements.get(1).select("a").get(0);
                // 比赛类型
                String gameType = aElement.html();
                // 比赛时间
                String gameTime = tdElements.get(2).attr("title").substring(5);

                Element thirdElement = tdElements.get(3);
                Elements aElements = thirdElement.select("a");
                Element firstAelement = aElements.get(0);
                Element secondAelement = aElements.get(1);
                Element thirdAelement = aElements.get(2);
                // 主场球队名称
                String homeName = firstAelement.select("span").html();
                // 客场球队名称
                String guestName = thirdAelement.select("span").html();
                // 主胜赔率
                String homeOdds = firstAelement.select(".pltxt").first().html();
                // 平赔
                String dogfallOdds = secondAelement.select(".pltxt").first().html();
                // 客胜赔率
                String guestOdds = thirdAelement.select(".pltxt").first().html();
                // 主场球队过往排名表现
                String homeLastPerformance = firstAelement.select("i").first().html();
                // 客场球队过往排名表现
                String guestLastPerformance = thirdAelement.select("i").first().html();

                Element sixthElement = tdElements.get(6);
                // 比赛的最终比分
                String gameScore = sixthElement.html();
                int homeScore = Integer.valueOf(gameScore.split(":")[0].trim());
                int guestScore = Integer.valueOf(gameScore.split(":")[1].trim());
                // 比赛最终的结果
                int gameResult = 1;
                if (homeScore > guestScore) {
                    gameResult = 3;
                } else if (homeScore < guestScore) {
                    gameResult = 0;
                }
                SuperAmbition superAmbition = new SuperAmbition();
                superAmbition.setPeriods(periods);
                superAmbition.setTotalSales(new BigDecimal(totalSales));
                superAmbition.setFirstPrizeNum(Integer.valueOf(firstPrizeNum));
                superAmbition.setFirstPrize(new BigDecimal(firstPrize));
                superAmbition.setSecondPrizeNum(Integer.valueOf(secondPrizeNum));
                superAmbition.setSecondPrize(new BigDecimal(secondPrize));
                superAmbition.setNextPond(new BigDecimal(nextPond));
                superAmbition.setGameOrder(Integer.valueOf(gameOrder));
                superAmbition.setGameType(gameType);
                superAmbition.setGameTime(SIMPLE_DATE_FORMAT.parse(gameTime));
                superAmbition.setHomeName(homeName + "[" + homeOdds + "]");
                superAmbition.setGuestName(guestName + "[" + guestOdds + "]");
                superAmbition.setHomeLastPerformance(homeLastPerformance);
                superAmbition.setGuestLastPerformance(guestLastPerformance);
                superAmbition.setHomeOdds(new BigDecimal(homeOdds));
                superAmbition.setGuestOdds(new BigDecimal(guestOdds));
                superAmbition.setDogfallOdds(new BigDecimal(dogfallOdds));
                superAmbition.setGameScore(gameScore);
                superAmbition.setGameResult(gameResult);
                superAmbition.setCreateTime(new Date());
                resultList.add(superAmbition);
            }
        }
        System.out.println(JSONObject.toJSONString(resultList));
    }

}
