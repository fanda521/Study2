package controllers;

import models.Person;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Finally;
import play.mvc.With;

import java.util.Date;

/**
 * 拦截器的方法都是类级别的，如果想使用到其他的类就是用@with注解
 * 而且拦截器的调用顺序，由小到大，由近到远
 */
@With(value = {MyInterceptor.class})
public class Application extends Controller {

    /**
     * 拦截器方法不能是public，必须有static，定义在非本类中无效
     * unless:排除不用拦截的action方法
     * only:指定只用哪些方法要尽心拦截{action1，action2,...}
     */
    @Before(unless = "paramsPersonRender2")
    static void log2(){
        System.out.println("我是自定义的拦截器，执行了log2方法");
    }

    @Finally
    static void finallyTest(){
        System.out.println("finallyTest  方法被调用！");
    }


    public static void index() {
        System.out.println("index()  render()");
        render();
    }

    public static void bye(){
        System.out.println("bye()  render()");
        render();
    }

    public static void client(){
        System.out.println("client()  render()");
        render();
    }


    public static void params1(){
        String s = params.get("id");
        System.out.println("id="+s);
        render();
    }

    public static void params2(){
        Long id = params.get("id", Long.class);
        System.out.println("id="+id);
        render();
    }


    public static void params3(String id){

        System.out.println("id="+id);
        render();
    }

    public static void paramsDate(Date time){

        System.out.println("id="+time);
        render();
    }

    public static void paramsPerson(Person person){

        System.out.println(person.toString());
        render();
    }

    public static void paramsPersonArgs(Person person){

        System.out.println(person.toString());
        renderArgs.put("person",person);
        render();
    }

    public static void paramsPersonRender(Person person){

        System.out.println(person.toString());
        String id="331224";
        //renderArgs.put("person",person);
        render(id,person);
    }

    public static void paramsPersonRender2(Person person){

        System.out.println(person.toString());
        String id="331224";
        //renderArgs.put("person",person);
        render("Application/temp.html",id,person);
    }

}