package controllers.mycontroller;

import controllers.Application;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2020-06-07 22:04
 */

public class MyInterceptor extends Controller {

    @Before
    static void log(){
        System.out.println("拦截器log()方法被调用");
    }
}
