package com.springmvc.util;

import org.apache.log4j.Logger;

/**
 * 功能概要：消费接收
 * @author zhangyunye
 *
 */
public class Consumor {
    private Logger logger=Logger.getLogger(Consumor.class);

    public void onMessage(String message){
        logger.info("--------------消费一条信息为:" + message+"--------------------");
    }
}
