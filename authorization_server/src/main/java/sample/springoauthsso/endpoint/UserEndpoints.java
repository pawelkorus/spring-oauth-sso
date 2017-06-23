package sample.springoauthsso.endpoint;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserEndpoints {

    public Principal fetchUser(Principal principal) {
        return principal;
    }

}
