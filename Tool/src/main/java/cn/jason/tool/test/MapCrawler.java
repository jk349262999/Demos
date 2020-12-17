package cn.jason.tool.test;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.File;

/**
 * 地图坐标爬虫
 *
 * @author jinkai
 * @description
 * @since 2020/11/5
 */
public class MapCrawler {

    public static void main(String[] args) {
        // https://datav.aliyun.com/tools/atlas
        JSONArray counties = new JSONArray();
        JSONObject jo = JSONUtil.parseObj(HttpUtil.get("https://geo.datav.aliyun.com/areas_v2/bound/630000_full.json"));
        for (Object feature : jo.getJSONArray("features")) {
            String code = JSONUtil.parseObj(feature).getJSONObject("properties").getStr("adcode");
            String county = HttpUtil.get("https://geo.datav.aliyun.com/areas_v2/bound/" + code + "_full.json");
            counties.addAll(JSONUtil.parseObj(county).getJSONArray("features"));
        }
        JSONObject provinceJo = new JSONObject();
        provinceJo.set("type", "FeatureCollection");
        provinceJo.set("features", counties);

        FileWriter writer = new FileWriter(new File("files/area/counties.json"));
        writer.write(provinceJo.toString());
        System.out.println(writer.getFile().getAbsolutePath());
    }
}
