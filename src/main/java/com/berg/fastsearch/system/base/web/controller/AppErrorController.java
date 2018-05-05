package com.berg.fastsearch.system.base.web.controller;

import com.berg.fastsearch.system.base.web.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>处理web错误, 全局错误</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Controller
public class AppErrorController implements ErrorController{
    /**
     * 错误时候返回的页面导向
     */
    private static final String WEB_ERROR_PATH = "/error";

    /**
     * 未知路径
     */
    private static final int NOT_FOUND_CODE = 404;
    private static final String NOT_FOUND_PATH = "error/404";

    /**
     * 拒绝访问
     */
    private static final int FORBIDDEN_CODE = 403;
    private static final String FORBIDDEN_PATH = "error/403";

    /**
     * 内部错误
     */
    private static final int ERROR_CODE = 500;
    private static final String ERROR_PATH = "error/500";

    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return WEB_ERROR_PATH;
    }

    @Autowired
    public AppErrorController(ErrorAttributes errorAttributes){
        this.errorAttributes = errorAttributes;
    }

    /**
     * web页面错误处理
     * @param response  响应域对象
     * @return          错误的页面
     */
    @RequestMapping(value = WEB_ERROR_PATH, produces = "text/html")
    public String errorPageHandler(HttpServletResponse response){
        Integer status = response.getStatus();

        switch (status){
            case NOT_FOUND_CODE:
                return String.valueOf(NOT_FOUND_CODE);
            case FORBIDDEN_CODE:
                return String.valueOf(FORBIDDEN_CODE);
            case ERROR_CODE:
                return String.valueOf(ERROR_CODE);
            default:
                return String.valueOf(ERROR_CODE);
        }
    }

    /**
     * 除Web页面外的错误处理，比如Json/XML等
     * @param request   请求域对象
     * @return          错误结果
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ResponseData errorApiHandler(HttpServletRequest request){
        RequestAttributes attributes = new ServletRequestAttributes(request);

        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(attributes, false);
        int status = getStatus(request);

        return ResponseData.ofMessage(status, String.valueOf(attr.getOrDefault("message", "error")));
    }

    /**
     * 访问受限页面
     * @return  访问受限页面
     */
    @GetMapping(value = "/403")
    public String accessError(){
        return FORBIDDEN_PATH;
    }

    /**
     * 未找到的页面
     * @return  访问受限页面
     */
    @GetMapping(value = "/404")
    public String notFound(){
        return NOT_FOUND_PATH;
    }

    /**
     * 服务器错误页面
     * @return  访问受限页面
     */
    @GetMapping(value = "/500")
    public String internalError(){
        return ERROR_PATH;
    }


    /**
     * 根据request对象获取状态码
     * @param request   请求域对象
     * @return          状态码
     */
    private int getStatus(HttpServletRequest request){
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status != null) {
            return status;
        }

        return 500;
    }
}
