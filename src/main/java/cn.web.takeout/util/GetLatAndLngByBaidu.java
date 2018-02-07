package cn.web.takeout.util;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import net.sf.json.JSONObject;

/**
 *
 * 1.通过具体地址,获取经纬度
 * 2.计算两地的距离
 * @author Sunny
 * 密钥:F454f8a5efe5e577997931cc01de3974
 */
public class GetLatAndLngByBaidu {
    public static Map<String,Double> getLngAndLat(String address){
        Map<String,Double> map=new HashMap<String,Double>();
        String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=F454f8a5efe5e577997931cc01de3974";
        String json = loadJSON(url);
        JSONObject obj = JSONObject.fromObject(json);
        if(obj.get("status").toString().equals("0")){
            Double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
            Double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
            map.put("lng", lng);
            map.put("lat", lat);
            //System.out.println("经度："+lng+"---纬度："+lat);
        }else{
            //System.out.println("未找到相匹配的经纬度！");
        }
        return map;
    }

    public static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }


    //地球平均半径
    private static final double EARTH_RADIUS = 6378137;
    //把经纬度转为度（°）
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(
                Math.sqrt(
                        Math.pow(Math.sin(a/2),2)
                                + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)
                )
        );
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }


//    public static void main(String[] args){
//        /*把代码中的ak值（红色字部分）更改为你自己的ak值，在百度地图API中注册一下就有。
//        调用方式：*/
//        Map<String,Double> map=GetLatAndLngByBaidu.getLngAndLat("广东省广州市天河区员村一横路3号");
//        Map<String,Double> map1=GetLatAndLngByBaidu.getLngAndLat("广东省广州市白云区钟落潭镇广新路388号");
//        System.out.println(getDistance(map.get("lng"),map.get("lat"),map1.get("lng"),map1.get("lat")));
//        System.out.println("经度："+map.get("lng")+"---纬度："+map.get("lat"));
//    }

}