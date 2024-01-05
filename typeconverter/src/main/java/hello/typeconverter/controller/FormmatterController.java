package hello.typeconverter.controller;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class FormmatterController {

    @GetMapping("/formatter/edit")
    public String formatterForm() {
        Form form = new Form()
        return "formatter-form";
    }

    @Data
    public static class Member {
        @NumberFormat(pattern = "###,###")
        private Integer price;

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime dateTime;

    }
}
