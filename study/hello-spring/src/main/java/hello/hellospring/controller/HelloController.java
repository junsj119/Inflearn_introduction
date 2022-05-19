package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model)
    {
        model.addAttribute("data", "hello!!");
        return "hello"; //templates 안에 있는 hello.html로 가라
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = false)String name, Model model)  // required = false가 기본값. 안써줘도 됌
    {
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody//밑에 내용을 Body부에 직접 넣어주겠다. 전과의 차이 -> view가 필요없다. html 태그가 없음
    public String helloString(@RequestParam("name")String name)
    {
        return "hello" + name;
    }

    //키 값이 왜 name?? name을 다 바꿔줘도 name으로 고정되던데 왜??? -> getter setter 단축키로 했던거 첨부터 다시하면 바뀜
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name)
    {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
