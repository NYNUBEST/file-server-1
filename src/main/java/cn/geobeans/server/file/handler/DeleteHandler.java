package cn.geobeans.server.file.handler;

import cn.geobeans.server.file.common.JSONResponse;
import cn.geobeans.server.file.service.FileDataService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author LiYuFei
 * @create 2018-08-16 10:49
 * @desc
 **/
public class DeleteHandler extends BaseHandler {
    public DeleteHandler(){
        super(DELETE);
    }
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        super.handle(httpExchange);
        JSONResponse rs=null;
        try {
            String uri = httpExchange.getRequestURI().getRawPath();
            String[] arr = uri.split("/");
            String md5 = arr[arr.length - 1];
            if (!(md5 == null || md5.trim().equals(""))) {
                Boolean result=FileDataService.deleteByMd5(md5);
                if(result){
                    rs=new JSONResponse(true,"删除成功","删除成功");
                }else {
                    rs=new JSONResponse(true,"删除失败","删除失败");
                }
            }else{
                rs=new JSONResponse(false,"md5不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rs = new JSONResponse(false,e.getMessage());
        }
        super.json(httpExchange, rs);
    }
}
