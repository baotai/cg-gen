package com.cg.apollo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("technology.school")
public class DubboBean {

    private static final Logger logger = LoggerFactory.getLogger(AnnotatedBean.class);

    private String name;

    @Value("${name:200}")
    public void setName(String name) {
        logger.info("updating name, old value: {}, new value: {}", this.name, name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "DubboBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
