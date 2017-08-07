package com.phincon.talents.app.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalValue {

    public static String PATH_UPLOAD;
    public static String TALENTS_PROTOCOL;

    @Value("${path.upload}")
    public void setDatabase(String path) {
    	PATH_UPLOAD = path;
    }
    
    @Value("${talents.protocol}")
    public void setTalentsProtocol(String protocol) {
    	TALENTS_PROTOCOL = protocol;
    }

}