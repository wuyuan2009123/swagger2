package com.swagger.session;

import com.swagger.CookieUtil;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wuy on 2017/9/6.
 */

@Deprecated
public class DistributionSessionImpl implements HttpSession {

    private HttpSession session;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private Map<String,Object> sessionMap=null;


    public DistributionSessionImpl(){

    }

    public DistributionSessionImpl(HttpSession session){
        this.session=session;
    }

    public DistributionSessionImpl(HttpServletRequest request,HttpServletResponse response){
        this.request=request;
        this.response=response;
    }


    public long getCreationTime() {
        return this.session.getCreationTime();
    }

    public String getId() {
        String uuid=CookieUtil.getCookie(request,"pcxSessionId");
        if(!StringUtils.isEmpty(uuid)){
            uuid= UUID.randomUUID().toString();
        }
        return uuid;
    }

    public long getLastAccessedTime() {
        return this.session.getLastAccessedTime();
    }

    public ServletContext getServletContext() {
        return this.session.getServletContext();
    }

    public void setMaxInactiveInterval(int i) {
        this.session.setMaxInactiveInterval(i);
    }

    public int getMaxInactiveInterval() {
        return session.getMaxInactiveInterval();
    }

    public HttpSessionContext getSessionContext() {
        return session.getSessionContext();
    }

    public Object getAttribute(String s) {
        if(null==sessionMap){
            sessionMap= RedisDao.getSession(this.getId());
        }
        return sessionMap.get(s);
    }

    public Object getValue(String s) {
        return session.getValue(s);
    }

    public Enumeration<String> getAttributeNames() {
        return session.getAttributeNames();
    }

    public String[] getValueNames() {
        return session.getValueNames();
    }

    public void setAttribute(String s, Object o) {
        if(null==sessionMap){
            sessionMap=RedisDao.getSession(this.getId());
        }
        this.sessionMap.put(s,o);
        RedisDao.saveSession(this.getId(),sessionMap);
    }

    public void putValue(String s, Object o) {
        this.session.putValue(s,o);
    }

    public void removeAttribute(String s) {
        if(null==sessionMap){
            sessionMap=RedisDao.getSession(this.getId());
        }
        sessionMap.remove(s);
        RedisDao.saveSession(this.getId(),sessionMap);
    }

    public void removeValue(String s) {
        session.removeValue(s);
    }

    public void invalidate() {
        sessionMap.clear();
        RedisDao.removeSession(this.getId());
        CookieUtil.removeCookie("pcxSessionId",request,response);
    }

    public boolean isNew() {
        return this.session.isNew();
    }
}
