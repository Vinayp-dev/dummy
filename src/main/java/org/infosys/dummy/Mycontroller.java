package org.infosys.dummy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*
This controller uses 2 things which are very important
1.Logger: It is used to log messages at different places and levels
2.RepsonseEntity: It is used to return response with status code and body.It is mainly for customizing the response and handling different scenarios like success, error, etc.
logger.info shows in the console
logger.debug also shows in the console but only if the logging level is set to debug or lower
logger.warn shows in the console but only if the logging level is set to warn or lower
logger.error shows in the console but only if the logging level is set to error or lower
logger.trace shows in the console but only if the logging level is set to trace or lower
Here logging level is set to info by default,to set it to different level we can use application.properties file and set logging.level.root=debug or any other level
*/
@RestController
public class Mycontroller {

    // Create a logger for this class
    private static final Logger logger = LoggerFactory.getLogger(Mycontroller.class);

    @RequestMapping("m")
    public static String myMethod() {
        logger.info("INFO: myMethod() endpoint called");
        logger.debug("DEBUG: Returning static HTML content");
        return "<h1 style=\"color: blue;\">coding</h1>";
    }
    //How to write response entity is shown in the below method
    /* 
    First,we need to import ResponseEntity and HttpStatus from org.springframework.http package.
    return type of the method can be ResponseEntity<AnyType> where AnyType can be String, Object, List, etc.
    To return a response with a specific status code and body, we can use the static methods of ResponseEntity class like ok(), status(), etc.
    For example, to return a 200 OK response with a body, we can use:
    return ResponseEntity.ok("Your response body here");
    To return a response with a specific status code, we can use:
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error message here");
    if you wrote ResponseEntity<Integer> then you can return ResponseEntity.ok(123) or ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0) etc.
    but mostly we use ResponseEntity<String> to return string responses.
    */
    @GetMapping("greet/{name}")
    public ResponseEntity<String> greet(@PathVariable String name) {
        logger.trace("TRACE: Entered greet() with name={}", name);

        if (name.equalsIgnoreCase("admin")) {
            logger.warn("WARN: Attempt to greet admin user");
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN) // 403
                    .body("Access denied for admin!");
        } else if (name.isBlank()) {
            logger.error("ERROR: Blank name provided to greet()");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST) // 400
                    .body("Name cannot be empty!");
        } else {
            logger.info("INFO: Greeting user {}", name);
            return ResponseEntity
                    .ok("Hello, " + name + "! Welcome to Spring Boot.");
        }
    }

    @PostMapping("process-form")
    public static String getData(@RequestParam int num1, @RequestParam int num2) {
        logger.trace("TRACE: Entered getData() with num1={} and num2={}", num1, num2);

        int sum = num1 + num2;
        
        if (sum > 100) {
            logger.warn("WARN: Sum is unusually large: {}", sum);
        }

        logger.info("INFO: Returning sum result");
        return "The sum is: " + sum;
    }
}