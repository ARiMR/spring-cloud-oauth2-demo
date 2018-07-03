package pl.arimr.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class ApiController {

    private final OAuth2RestTemplate restTemplate;

    public ApiController(OAuth2RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/principal")
    public Principal getPrincipal(@AuthenticationPrincipal Principal principal) {
        return principal;
    }

    @GetMapping("/deep")
    public String callDeepApi() {
        String response = restTemplate.getForEntity("http://deep-api-service/deep/api/call", String.class).getBody();
        return "Response from deep api: \"" + response + "\"";
    }

}
