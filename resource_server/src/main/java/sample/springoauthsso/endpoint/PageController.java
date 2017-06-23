package sample.springoauthsso.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/home")
    @ResponseBody
    public String homePage() {
        return "Home page";
    }

    @RequestMapping("/samplePage")
    @ResponseBody
    public String samplePage() {
        return "Sample page";
    }

}
