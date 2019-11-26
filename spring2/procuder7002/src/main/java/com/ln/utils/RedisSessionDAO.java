package com.ln.utils;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 白俊杰
 * @Date 2019/11/23
 * @Description
 **/
@Component
public class RedisSessionDAO extends AbstractSessionDAO {
    @Autowired
    private RedisManagers redisManager; //说白了这个就是redis工具类 用来对redis的增删改查
    private String keyPrefix = "shiro_redis_session:";

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    /**
     * 获得byte[]型的key
     * @param key
     * @return
     */
    private byte[] getByteKey(Serializable sessionId){
        String preKey = this.keyPrefix + sessionId;
        return preKey.getBytes();
    }

    private void saveSession(Session session){
        if(session ==null || session.getId() == null){
            System.out.println("session or sessionID is null");
            return;
        }
        byte[] key = getByteKey(session.getId());
        byte[] value = SerializeUtils.serialize(session);
        session.setTimeout(2000*2);
        this.redisManager.set(key, value, 2000*2);

    }
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        if(serializable == null){
            System.out.println("session id is null");
            return null;
        }
        Session s = (Session)SerializeUtils.unserialize(redisManager.get(this.getByteKey(serializable)));

        return s;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
            this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if(session == null || session.getId() == null){
            System.out.println("session or session id is null");
            return;
        }
        redisManager.del(this.getByteKey(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();
        Set<byte[]> keys = redisManager.keys((this.keyPrefix + "*").getBytes());
        if(keys != null && keys.size()>0){
            for(byte[] key:keys){
                Session s = (Session)SerializeUtils.unserialize(redisManager.get(key));
                sessions.add(s);
            }
        }

        return sessions;
    }
    public RedisManagers getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManagers redisManager) {
        this.redisManager = redisManager;

        /**
         * 初始化redisManager
         */
        this.redisManager.init();
    }
}
