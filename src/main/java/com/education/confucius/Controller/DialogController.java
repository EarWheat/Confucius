package com.education.confucius.Controller;

import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Entity.Auth.AuthEnum;
import com.education.confucius.Entity.Dialog.DialogRequest;
import com.education.confucius.Entity.Dialog.Request;
import com.education.confucius.Entity.Dialog.DialogParam;
import com.education.confucius.Service.AuthService.AuthService;
import com.education.confucius.Service.DialogService.DialogService;
import com.pangu.Http.response.RestResult;
import com.pangu.Http.response.ResultEnum;
import com.pangu.HttpSession.HttpSessionContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/4 上午11:17
 * @desc 对话机器人
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@RequestMapping("/dialog")
@RestController
public class DialogController {

    private static final Logger logger = LoggerFactory.getLogger(DialogController.class);

    @Resource
    private AuthService authService;

    @Resource
    private DialogService dialogService;

    /**
     * 带详细参数，问谁什么问题
     * @param dialogParam
     * @return
     */
    @RequestMapping("/ask")
    public RestResult<JSONObject> askQuestion(@RequestBody DialogParam dialogParam){
        JSONObject token = authService.getAuthToken(AuthEnum.Baidu);
        String accessToken = token.getString("access_token");
        JSONObject answer = dialogService.askQuestion(dialogParam, accessToken);
        return RestResult.successResult(answer);
    }

    /**
     * 聊天问答
     * @param dialogRequest
     * @return
     */
    @RequestMapping("/chat")
    public RestResult<String> chat(HttpServletRequest httpServletRequest, @RequestBody @Validated DialogRequest dialogRequest){
        HttpSession httpSession = HttpSessionContext.getHttpSession(httpServletRequest.getSession().getId());
        logger.info("httpSession:{}",JSONObject.toJSONString(httpSession));
        System.out.println("=========HttpSessionId:" + httpSession.getId());
        Request request = dialogRequest.getRequest();
        if(StringUtils.isBlank(request.getQuery()) || StringUtils.isBlank(request.getUser_id())){
            return RestResult.failResult(ResultEnum.PARAM_EMPTY);
        }
        String answer = dialogService.chat(request, dialogRequest.getToken(), httpSession.getId());
        return RestResult.successResult(answer);
    }
}
