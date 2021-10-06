package controllers;

import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2020-06-07 21:45
 */
@With(value = {controllers.mycontroller.MyInterceptor.class})
public class MyInterceptor extends Controller {

    /**
     * 拦截器方法不能是public，必须有static
     */
    @Before
    static void log(){
        System.out.println("我是自定义的拦截器，执行了log方法");
    }

}
