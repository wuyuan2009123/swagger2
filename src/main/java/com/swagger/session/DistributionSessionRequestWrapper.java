package com.swagger.session;

import com.swagger.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wuy on 2017/9/6.
 */

@Deprecated
public class DistributionSessionRequestWrapper extends HttpServletRequestWrapper {
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public DistributionSessionRequestWrapper(HttpServletRequest request){
        super(request);
    }

    public DistributionSessionRequestWrapper(HttpServletRequest request,HttpServletResponse response){
        super(request);
        this.request=request;
        this.response=response;
    }


    @Override
    public HttpSession getSession(boolean create) {
        if(create){
            this.session=new DistributionSessionImpl(request,response);
            CookieUtil.addCookie("pcxSessionId", session.getId(),"localhost",60*60,response);
            return this.session;
        }
        else{
            return null;
        }
    }

    @Override
    public HttpSession getSession() {
        return this.getSession(true);
    }
}
