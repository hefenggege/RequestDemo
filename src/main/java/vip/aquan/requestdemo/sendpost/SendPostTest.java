package vip.aquan.requestdemo.sendpost;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import vip.aquan.requestdemo.util.JsonMapper;

import java.util.HashMap;
import java.util.Map;


import static vip.aquan.requestdemo.util.HttpClientUtil.doPostJson;


public class SendPostTest {

    private static Logger logger=Logger.getLogger(SendPostTest.class);

    public static void main(String[] args) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("userAccount","admin");
        JSONObject jsonMap = new JSONObject(map);
        String json = jsonMap.toJSONString();

        String url="http://localhost:6666/jsaas/bpm/process/getMySolutions";
        String token_header="ndhimmjiodqtmdliny00yzm2lwe4ntctywuwy2y4mzzjnte0";
        //发送请求
        String result = doPostJson(url, json, token_header);
        //将Json 字符串转化为JSONObject对象（然后就可对该对象进行相应的操作）,此处的result 是键值对形式的 Json字符串
        JSONObject resultJson = JSONObject.parseObject(result);



        /**
         * 总结：
        *  "data": [   主要看key 后面value 是什么来点, 例如data后【 ,所以是数组,  如果{ 是对象！
         *
        */

        System.out.println("*****************************************************************************************");

        //获得Json 数组
        JSONArray dataArray = resultJson.getJSONArray("data");
        System.out.println("获得Json数组: "+dataArray);
        System.out.println("*****************************************************************************************");

        //根据Json数组  拿数组里面的第一个对象数组
        JSONObject jsonObject = dataArray.getJSONObject(0);
        System.out.println("根据Json数组  拿数组里面的对象: "+jsonObject);
        System.out.println("*****************************************************************************************");

        //根据对象 拿对象数组里的  rightJson对象
        JSONObject rightJson = jsonObject.getJSONObject("rightJson");
        //根据对象 拿对象里面的对象数组
        JSONArray task = rightJson.getJSONArray("task");
        // 拿对象数组里的  task对象
        JSONObject jsonObject1 = task.getJSONObject(0);
        //根据键值对拿值
        String name = jsonObject1.getString("name");
        System.out.println("重重包围拿值： " +name);


        /**
         * 这个是日志输出
         */
//        logger.debug("------------------------------------------------------------------------------");
//        logger.debug("返回的数据"+result);


    }
}
