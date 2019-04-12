package com.jxq.douban;

import com.jxq.common.HttpBase;
import com.jxq.douban.ISearch;
import com.jxq.douban.domain.KeywordResponse;
import com.jxq.douban.domain.MovieResponseVO;
import com.jxq.douban.domain.WHResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: jx
 * @Date: 2018/7/13 17:47
 * @Description:
 */
public class HttpSearch extends HttpBase {

    private ISearch iSearch;

    public HttpSearch(String host) {
        super(host);
        iSearch = super.create(ISearch.class);
    }

    public Response<MovieResponseVO> searchTags(String type, String source) throws IOException {
        Call<MovieResponseVO> call = iSearch.searchTags(type, source);
        return call.execute();
    }
    
    public Response<ResponseBody> searchKeywords(String wd) throws IOException {
//    	 iSearch.searchKeywords(wd,source);
        Call<ResponseBody> call = iSearch.searchKeywords(wd);
        return call.execute();
//    	 return null;
    }
    
    public Response<ResponseBody> login(String username,String password) throws IOException {
       Call<ResponseBody> call = iSearch.login(username,password);
       return call.execute();
   }
    
    public Response<ResponseBody> sousuo(String id,String page,String plat,String version) throws IOException {
        Call<ResponseBody> call = iSearch.sousuo(id,page,plat,version);
        return call.execute();
    }
    
    public Response<ResponseBody> wenhaiCount(List<String> params,String sid) throws IOException {
        Call<ResponseBody> call = iSearch.wenhaiCount(params,sid);
        System.out.println("call="+call);
        return call.execute();
    }
    
    public Response<ResponseBody> rmtList(String page,String size,String columnId) throws IOException {
        Call<ResponseBody> call = iSearch.rmtList(page,size,columnId);
//        System.out.println("call="+call);
        return call.execute();
    }
    
    public Response<ResponseBody> rmtContent(String type,String data) throws IOException {
        Call<ResponseBody> call = iSearch.rmtContent(type,data);
//        System.out.println("call="+call);
        return call.execute();
    }

}
