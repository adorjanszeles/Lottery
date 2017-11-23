package com.lottery.gateway.controller;

import com.lottery.gateway.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/auth", description = "Lottery Auth Gateway")
@RequestMapping(value = "/auth")
@PropertySource("classpath:lottery-web.properties")
public class AuthGatewayController {

    private AuthService authService;
    private Environment env;

    @Autowired
    public AuthGatewayController(AuthService authService, Environment env) {
        this.authService = authService;
        this.env = env;
    }

    // ####################### GET ACCESS TOKEN ##############################

    @ApiOperation(value = "POST new draw", notes = "adding new weekly draw results")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    @PostMapping(value = "/get-token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object getAccessToken(@RequestHeader("Authorization") String access_token,
                                 @RequestBody MultiValueMap requestBody) {
        String url = this.env.getProperty("lottery-auth.get-token");
        return this.authService.getAccessToken(url, access_token, requestBody);
    }

}
