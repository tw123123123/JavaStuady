package com.qdc.demoeurekazuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class IPFilter extends ZuulFilter {
    private String[] whitelist;
    @Value("${yxwfilter.ip.whitelist}")
    private String strIPWhitelist;
    @Value("${yxwfilter.ip.whitelistenabled}")
    private String WhitelistEnable;

    //Logger Logger = LoggerFactory.getLogger(getClass());


    @Override
    public Object run() throws ZuulException {
        System.out.println(strIPWhitelist);
        whitelist = strIPWhitelist.split("\\,");

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String ipAddr = this.getIpAddr(req);
        System.out.println("请求IP地址为:[" + ipAddr + "]"); //配置本地IP白名单 生产环境可放入数据库或Redis
        List<String> ips = new ArrayList<>();
        for (int i =0; i < whitelist.length; ++i) {
            System.out.println(whitelist[i]); //输出a b c
            ips.add(whitelist[1]);
        }

        System.out.println("whitelist:" + ips.toString()); //配置本地IP白名单 生产环境可放入数据库或Redis
        if(!ips.contains(ipAddr)) {
            System.out.println("未通过IP地址校验.[" + ipAddr +"]");
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
            ctx.getResponse().setContentType("application/json;charset-UTF-8");
            ctx.setResponseBody("{\"errocode\":\"00001\",\"errmsg\":\"IpAddr is forbidden![" + ipAddr + "]\"}");
        }
        return null;
    }



    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }
//获取IP地址

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Fprwarded-For");
        if (ip == null || ip.length() ==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Prpxy-Client-IP");
        }
        if (ip == null || ip.length() ==0|| "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Prpxy-Client-IP");
        }
        if (ip == null || ip.length() ==0|| "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Http_Client_IP");
        }
        if (ip == null || ip.length() ==0|| "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_X_Prpxy-Client-IP");
        }
        if (ip == null || ip.length() ==0|| "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
