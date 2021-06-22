package com.cg;

import com.cg.apollo.AnnotatedBean;
import com.cg.apollo.DubboBean;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class CgGenApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(CgGenApplication.class, args);
//    }

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.cg.apollo");
//        AnnotatedBean annotatedBean = context.getBean(AnnotatedBean.class);

        DubboBean dubboBean = context.getBean(DubboBean.class);

        System.out.println("AnnotationApplication Demo. Input any key except quit to print the values. Input quit to exit.");
        while (true) {
            System.out.print("> ");
            String input = new BufferedReader(new InputStreamReader(System.in, Charsets.UTF_8)).readLine();
            if (!Strings.isNullOrEmpty(input) && input.trim().equalsIgnoreCase("quit")) {
                System.exit(0);
            }

//            System.out.println(annotatedBean.toString());
            System.out.println(dubboBean.toString());
        }
    }
}
