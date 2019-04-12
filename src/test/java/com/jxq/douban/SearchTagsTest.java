package com.jxq.douban;

import com.alibaba.fastjson.JSONObject;
import com.jxq.common.PostRequest;
import com.jxq.common.Request;
import com.jxq.douban.domain.MovieResponseVO;
import com.jxq.tools.JsonSchemaUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import okhttp3.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import retrofit2.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: jx
 * @Date: 2018/7/5 10:48
 * @Description: 豆瓣首页接口测试
 */
public class SearchTagsTest {
    private static Properties properties;
    private static HttpSearch implSearch;
    private static String SCHEMA_PATH = "parameters/search/schema/SearchTagsMovie.json";

    @BeforeSuite
    public void beforeSuite() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("env.properties");
        properties = new Properties();
        properties.load(stream);
//        String host = properties.getProperty("douban.host");
//        String host = properties.getProperty("baidu.host");
//        String host = properties.getProperty("wenhai.host");
        String host = properties.getProperty("rmt.host");
//        String host = properties.getProperty("wenhaiDs.host");
//        String host = properties.getProperty("qq.host");
        implSearch = new HttpSearch(host);
        stream = this.getClass().getClassLoader().getResourceAsStream("parameters/search/SearchTagsParams.properties");
        properties.load(stream);
        stream = this.getClass().getClassLoader().getResourceAsStream("");
        stream.close();
    }

//    @Test(description = "电影首页。类别:type=movie source=index")
//    public void testcase1() throws IOException {
//        String type = properties.getProperty("testcase1.req.type");
//        String source = properties.getProperty("testcase1.req.source");
//
//        Response<MovieResponseVO> response = implSearch.searchTags(type, source);
//        MovieResponseVO body = response.body();
//        Assert.assertNotNull(body, "response.body()");
////        响应返回内容想通过schema标准校验
//        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH, JSONObject.toJSONString(body));
////        再Json化成对象
//        Assert.assertNotNull(body.getTags(), "tags");
//    }
//
//    @Test(description = "Tv首页。类别:type=tv source=index")
//    public void testcase2() throws IOException {
//        String type = properties.getProperty("testcase2.req.type");
//        String source = properties.getProperty("testcase2.req.source");
//        Response<MovieResponseVO> response = implSearch.searchTags(type, source);
//        MovieResponseVO body = response.body();
//        Assert.assertNotNull(body, "response.body()");
//        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH, JSONObject.toJSONString(body));
//        Assert.assertNotNull(body.getTags(), "tags");
//    }
//    
//    @Test(description = "百度关键词搜索页面。类别:wd=java")
//    public void testcase3() throws IOException {
//        String wd = properties.getProperty("testcase3.req.wd");
////        String source = properties.getProperty("testcase3.req.source");
//        Response<ResponseBody> response = implSearch.searchKeywords(wd);
//        ResponseBody body = response.body();
//        System.out.println(body.string());
//        Assert.assertNotNull(body, "response.body()");
////        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH, JSONObject.toJSONString(body));
////        Assert.assertNotNull(body.getTags(), "tags");
//    }
//    
//    @Test(description = "闻海登录页面。username: data&&password: data")
//    public void testcase4() throws IOException {
//        String username = properties.getProperty("testcase4.req.username");
//        String password = properties.getProperty("testcase4.req.password");
//        Response<ResponseBody> response = implSearch.login(username,password);
//        ResponseBody body = response.body();
//        System.out.println("--------"+body.toString());
//        Assert.assertNotNull(body, "response.body()");
////        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH, JSONObject.toJSONString(body));
////        Assert.assertNotNull(body.getTags(), "tags");
//    }
//    
//    @Test(description = "qq id=12&page=0&plat=android&version=9724")
//    public void testcase5() throws IOException {
//        String id = properties.getProperty("testcase5.req.id");
//        String page = properties.getProperty("testcase5.req.page");
//        String plat = properties.getProperty("testcase5.req.plat");
//        String version = properties.getProperty("testcase5.req.version");
//        Response<ResponseBody> response = implSearch.sousuo(id,page,plat,version);
//        ResponseBody body = response.body();
//        System.out.println("--------"+body.toString());
//        Assert.assertNotNull(body, "response.body()");
////        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH, JSONObject.toJSONString(body));
////        Assert.assertNotNull(body.getTags(), "tags");
//    }
//    
//    @Test(description = "闻海 微博 4.1-4.8号的数据量")
//    public void testcase6() throws IOException {
//    	
////    	String username = properties.getProperty("testcase4.req.username");
////        String password = properties.getProperty("testcase4.req.password");
////        Response<ResponseBody> response1 = implSearch.login(username,password);
//        List<String> list = new ArrayList<String>();
//    	String language = properties.getProperty("testcase6.req.language");
//        String region = properties.getProperty("testcase6.req.region");
//        String emotion = properties.getProperty("testcase6.req.emotion");
//        String keywordsPosion = properties.getProperty("testcase6.req.keywordsPosion");
//        String category = properties.getProperty("testcase6.req.category");
//        String datasourceId = properties.getProperty("testcase6.req.datasourceId");
//        String citys = properties.getProperty("testcase6.req.citys");
//        String keywords = properties.getProperty("testcase6.req.keywords");
//        String isMarkTitle = properties.getProperty("testcase6.req.isMarkTitle");
//        String dataSource = properties.getProperty("testcase6.req.dataSource");
//        String sid = properties.getProperty("testcase6.req.sid");
//        String startTime = properties.getProperty("testcase6.req.startTime");
//        String endTime = properties.getProperty("testcase6.req.endTime");
//        String now = properties.getProperty("testcase6.req.now");
//        list.add(language);
//        list.add(region);
//        list.add(emotion);
//        list.add(keywordsPosion);
//        list.add(category);
//        list.add(datasourceId);
//        list.add(citys);
//        list.add(keywords);
//        list.add(isMarkTitle);
//        list.add(dataSource);
//        list.add(sid);
//        list.add(startTime);
//        list.add(endTime);
//        list.add(now);
//        Response<ResponseBody> response = null;
//        for(int i=0;i<6;i++) {
//             response = implSearch.wenhaiCount(list,sid);
//        if(response!=null) {
//        	break;
//        }
//        }
//        ResponseBody body = response.body();
//        System.out.println("--------"+body);
//        Assert.assertNotNull(body, "response.body()");
////        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH, JSONObject.toJSONString(body));
////        Assert.assertNotNull(body.getTags(), "tags");
//    }
//    
//    @Test(description = "闻海登录 httpclient方式 username: data&&password: data")
//    public void testcase7() throws IOException {
//    	Map<String, String> param = new HashMap<String,String>();
//    	param.put("username", "data");
//    	param.put("password", "data");
//    	Map<String, String> head = new HashMap<String,String>();
//    	head.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
////    	head.put("Cookie", "JSESSIONID=3655EE5A7A81F5078BAB9929DE1EF485");
////    	String json = new String(PostRequest.post("http://wxservice.wengetech.com/wenhaiFt/login/mod1/login", param, head,null));
//    	String json = new String(Request.get("http://qtx.wengetech.com:28086/common_al/common/getNewsColumnData?page=0&size=10&columnId=5c10cfcaa310abf163b45664",head));
//        System.out.println("json="+json);
//        Assert.assertNotNull(json, "json");
////        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH, JSONObject.toJSONString(body));
////        Assert.assertNotNull(body.getTags(), "tags");
//    }
//    
   
    //测试用例编号
    @TmsLink("01")
    //bug编号
    @Issue("GH-56")
    //用例描述
    @Description("测试allure report")
//    用例步骤
    @Step("打开百度新闻首页获取页面title")
    @Test(description = "融媒体列表。page=0&size=10&columnId=5c10cfcaa310abf163b45664")
    public void testcase8() throws IOException {
        String page = properties.getProperty("testcase7.req.page");
        String size = properties.getProperty("testcase7.req.size");
        String columnId = properties.getProperty("testcase7.req.columnId");
        Response<ResponseBody> response = implSearch.rmtList(page,size,columnId);
//        ResponseBody body = response.body();
//        Assert.assertNotNull(body, "response.body()");
//        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH, JSONObject.toJSONString(body));
//        Assert.assertNotNull(body.getTags(), "tags");
    }
    
    @Test(description = "融媒体内容。type=2&data=26753")
    public void testcase9() throws IOException {
        String type = properties.getProperty("testcase8.req.type");
        String data = properties.getProperty("testcase8.req.data");
        Response<ResponseBody> response = implSearch.rmtContent(type,data);
//        ResponseBody body = response.body();
//        Assert.assertNotNull(body, "response.body()");
//        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH, JSONObject.toJSONString(body));
//        Assert.assertNotNull(body.getTags(), "tags");
    }
}
