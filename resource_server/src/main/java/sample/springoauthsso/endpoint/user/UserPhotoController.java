package sample.springoauthsso.endpoint.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/photo")
public class UserPhotoController {

    @RequestMapping
    @ResponseBody
    public String fetchPhotos() {
        return "List of photos";
    }

}
