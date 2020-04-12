package org.pzy.archetypesystem.base.support.spring.listener;

import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.module.comm.service.CommOnlineUserService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.session.Session;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * SessionExpiredEventListener
 *
 * @author pan
 * @date 2020/4/6 22:34
 */
@Component
@Slf4j
public class SessionExpiredEventListener {

    @Autowired
    private CommOnlineUserService onlineUserService;
    @Autowired
    private RedissonClient redissonClient;

    /**
     * session过期事件监听
     *
     * @param event
     */
    @EventListener(SessionExpiredEvent.class)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void onSessionExpiredEvent(SessionExpiredEvent event) {
        if (log.isDebugEnabled()) {
            log.debug("监听到:SessionExpiredEvent!");
        }
        Session session = event.getSession();
        this.clearRelationData(session);
    }

    /**
     * session删除事件监听
     *
     * @param event
     */
    @EventListener(SessionDeletedEvent.class)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void onSessionDeletedEvent(SessionDeletedEvent event) {
        if (log.isDebugEnabled()) {
            log.debug("监听到:SessionDeletedEvent!");
        }
        Session session = event.getSession();
        this.clearRelationData(session);
    }

    /**
     * session销毁事件监听
     *
     * @param event
     */
    @EventListener(SessionDestroyedEvent.class)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void onSessionDestroyedEvent(SessionDestroyedEvent event) {
        if (log.isDebugEnabled()) {
            log.debug("监听到:SessionDestroyedEvent!");
        }
        Session session = event.getSession();
        this.clearRelationData(session);
    }

    private void clearRelationData(Session session) {
        StringBuilder stringBuilder = new StringBuilder("监听到session销毁. ");
        if (null != session) {
            String sessionId = session.getId();
            stringBuilder.append("session id为:[" + sessionId + "]");
            onlineUserService.deleteBySessionIdAndClearCache(sessionId);
        }
        log.debug(stringBuilder.toString());
    }
}
