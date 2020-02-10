package com.accountmicroservice.demoaccount.controllers;

import com.accountmicroservice.demoaccount.services.OperationService;
import com.accountmicroservice.demoaccount.services.UserNotLoggedException;
import com.accountmicroservice.demoaccount.models.Operation;
import com.accountmicroservice.demoaccount.models.User;
import com.accountmicroservice.demoaccount.services.LoginService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@AllArgsConstructor
class JsonResponseBody {
    @Getter
    @Setter
    private int status;

    @Getter @Setter
    private Object response;
}

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private static final Logger log = LoggerFactory.getLogger(RestController.class);

    @Autowired
    LoginService loginService;

    @Autowired
    OperationService operationService;

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<JsonResponseBody> loginUser(@RequestParam String id,
                                                      @RequestParam(value="password") String pwd) {
        try {
            Optional<User> userOpt = loginService.getUser(id, pwd);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                String jwt = loginService.createJwt(user.getId(), user.getUsername(), user.getPermission());
                return ResponseEntity.status(HttpStatus.OK)
                        .header("jwt", jwt)
                        .body(new JsonResponseBody(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
            }
        } catch (UserNotLoggedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase()));
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @RequestMapping(value = "/operations/account/{accountId}")
    public ResponseEntity<JsonResponseBody> fetchAllOperationsPerAccount(HttpServletRequest request,
                                                                         @PathVariable String accountId) {
        try {
            loginService.verifyJwtAndGetData(request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new JsonResponseBody(HttpStatus.OK.value(), operationService.getAllOperationsPerAccount(accountId)));
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
        } catch (UserNotLoggedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase()));
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JsonResponseBody(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()));
        }
    }

    @RequestMapping(value = "/accounts/user")
    public ResponseEntity<JsonResponseBody> fetchAllAccountsPerUser(HttpServletRequest request) {
        try {
            Map<String, Object> userData = loginService.verifyJwtAndGetData(request);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new JsonResponseBody(HttpStatus.OK.value(),
                            operationService.getAllAccountsPerUser(userData.get("subject").toString())));
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
        } catch (UserNotLoggedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase()));
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JsonResponseBody(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()));
        }
    }

    @RequestMapping(value = "/operations", method = POST)
    public ResponseEntity<JsonResponseBody> addOperation(HttpServletRequest request,
                                                         @Valid Operation operation,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }

        try {
            loginService.verifyJwtAndGetData(request);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new JsonResponseBody(HttpStatus.OK.value(),
                            operationService.saveOperation(operation)));
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
        } catch (UserNotLoggedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase()));
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JsonResponseBody(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()));
        }
    }
}
