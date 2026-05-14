package org.infosys.dummy;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Mycontroller {
    @RequestMapping("m")
    public static String myMethod(){
        return "<h1 style=\"color: blue;\">coding</h1>";
    }
    @GetMapping("greet")
    public ResponseEntity<String> greet(@Param("name") String greet){
        return new ResponseEntity<>("Hello, "+greet+"!", HttpStatus.OK);
    }
    @PostMapping("process-form")
    public static String getData(@RequestParam int num1, @RequestParam int num2){
        int ans = num1 + num2;
        return "Result:"+ans;
    }
}
