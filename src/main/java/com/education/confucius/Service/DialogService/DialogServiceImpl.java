package com.education.confucius.Service.DialogService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Constants.Constants;
import com.education.confucius.Entity.Dialog.Request;
import com.education.confucius.Entity.Dialog.DialogParam;
import com.pangu.Http.request.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/4 上午11:20
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Service
public class DialogServiceImpl implements DialogService {

    private static final Logger logger = LoggerFactory.getLogger(DialogServiceImpl.class);

    @Override
    public JSONObject askQuestion(DialogParam dialogParam, String token) {
        JSONObject result = new JSONObject();
        try {
            logger.info("params info:{}", dialogParam.toString());
            JSONObject params = JSONObject.parseObject(dialogParam.toString());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json");
            String requestUrl = Constants.BAIDU_DIALOG_URL + "?access_token=" + token;
            String response = HttpClient.doPostJsonHttp(requestUrl,params, httpHeaders, 2000,2000);
            result = JSONObject.parseObject(response);
        } catch (Exception e){
            logger.error("get dialog answer error:{}", e.toString());
        }
        return result;
    }

    /**
     * 聊天
     * @param chatRequest
     * @return
     */
    @Override
    public String chat(Request chatRequest, String token){
        String answer = null;
        try {
            DialogParam dialogParam = getDefaultDialogParam(chatRequest);  // 获取默认聊天配置
            logger.info("dialog param :{}" ,dialogParam.toString());
            JSONObject params = JSONObject.parseObject(dialogParam.toString());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json");
            String requestUrl = Constants.BAIDU_DIALOG_URL + "?access_token=" + token;
            String response = HttpClient.doPostJsonHttp(requestUrl,params, httpHeaders, 2000,2000);
            answer = getAnswer(response);
        } catch (Exception e){
            logger.error("get dialog answer error:{}", e.toString());
        }
        return answer;
    }

    /**
     * 获取默认聊天配置
     * @return
     */
    private DialogParam getDefaultDialogParam(Request chatRequest){
        DialogParam dialogParam = new DialogParam();
        // TODO:后期接入唯一标识
        dialogParam.setLog_id(UUID.randomUUID().toString());
        dialogParam.setSession_id("3rsadasdasd");
        dialogParam.setService_id(Constants.BAIDU_DIALOG_SERVICE_ID);
        dialogParam.setRequest(chatRequest);
        return dialogParam;
    }

    /**
     * 根据Response返回最好的回答
     * @param response
     * @return
     */
    private String getAnswer(String response){
        try {
            String result = JSONObject.parseObject(response).getString(Constants.BAIDU_DIALOG_ANSWER_RESULT);
            JSONObject resultObject = JSONObject.parseObject(result);
            String responseList = resultObject.getString(Constants.BAIDU_DIALOG_ANSWER_RESULT_LIST);
            JSONArray answerArray = JSONArray.parseArray(responseList);
            if(answerArray.size() > 0){
                JSONObject bestAnswer = answerArray.getJSONObject(0);
                String actionList = bestAnswer.getString(Constants.BAIDU_DIALOG_ANSWER_ACTION_LIST);
                JSONArray actionArray = JSONArray.parseArray(actionList);
                if(actionArray.size() > 0){
                    JSONObject answer = actionArray.getJSONObject(0);
                    return answer.getString(Constants.BAIDU_DIALOG_ANSWER_SAY);
                }
            }
            return Constants.DIALOG_ANSWER_REVEAL;
        } catch (Exception e){
            logger.error("get answer error:{}", e.toString());
        }
        return Constants.DIALOG_ANSWER_REVEAL;
    }

    public static void main(String[] args) {
        DialogServiceImpl dialogService = new DialogServiceImpl();
        String response = "{\"result\":{\"version\":\"2.0\",\"timestamp\":\"2021-02-04 15:19:10.488\",\"service_id\":\"S46282\",\"log_id\":\"UNITTEST_10000\",\"session_id\":\"service-session-id-1612423150488-46a12ab8-66b9-11eb-84f3-dbd28aafa5b9\",\"interaction_id\":\"interaction-46a12752-66b9-11eb-84f3-dbd28aafa5b9\",\"response_list\":[{\"status\":0,\"msg\":\"ok\",\"origin\":\"1079259\",\"schema\":{\"intent_confidence\":100,\"slots\":[{\"word_type\":\"\",\"fuzzy_matches\":[],\"confidence\":100,\"name\":\"user_loc\",\"length\":2,\"original_word\":\"北京\",\"sub_slots\":[],\"session_offset\":0,\"begin\":2,\"normalized_word\":\"北京\",\"merge_method\":\"add\"},{\"word_type\":\"\",\"fuzzy_matches\":[],\"confidence\":100,\"name\":\"user_time\",\"length\":2,\"original_word\":\"今天\",\"sub_slots\":[],\"session_offset\":0,\"begin\":0,\"normalized_word\":\"2021-02-04\",\"merge_method\":\"add\"}],\"domain_confidence\":0,\"slu_tags\":[],\"intent\":\"USER_WEATHER\"},\"action_list\":[{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"build_weather_satisfy\",\"confidence\":100,\"custom_reply\":\"\",\"say\":\"北京今天晴转多云气温-5到8度~\",\"type\":\"satisfy\"}],\"qu_res\":{\"qu_res_chosen\":\"{\\\"confidence\\\":100.0,\\\"domain_confidence\\\":0.0,\\\"extra_info\\\":{\\\"group_id\\\":\\\"51\\\",\\\"real_threshold\\\":\\\"1\\\",\\\"threshold\\\":\\\"0.8\\\"},\\\"from_who\\\":\\\"pow-slu-lev1\\\",\\\"intent\\\":\\\"USER_WEATHER\\\",\\\"intent_confidence\\\":100.0,\\\"intent_need_clarify\\\":false,\\\"match_info\\\":\\\"{\\\\\\\"group_id\\\\\\\":\\\\\\\"51\\\\\\\",\\\\\\\"id\\\\\\\":\\\\\\\"2090302\\\\\\\",\\\\\\\"informal_word\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"match_keywords\\\\\\\":\\\\\\\"   kw_weatherstatus:天气如何\\\\\\\",\\\\\\\"match_pattern\\\\\\\":\\\\\\\"[D:user_time]\\\\\\\\t[D:user_loc]\\\\\\\\t[D:kw_weatherstatus]\\\\\\\",\\\\\\\"ori_pattern\\\\\\\":\\\\\\\"[D:user_time]\\\\\\\\t[D:user_loc]\\\\\\\\t[D:kw_weatherstatus]\\\\\\\",\\\\\\\"ori_slots\\\\\\\":{\\\\\\\"confidence\\\\\\\":100.0,\\\\\\\"domain_confidence\\\\\\\":0.0,\\\\\\\"extra_info\\\\\\\":{},\\\\\\\"from_who\\\\\\\":\\\\\\\"smart_qu\\\\\\\",\\\\\\\"intent\\\\\\\":\\\\\\\"USER_WEATHER\\\\\\\",\\\\\\\"intent_confidence\\\\\\\":100.0,\\\\\\\"intent_need_clarify\\\\\\\":false,\\\\\\\"match_info\\\\\\\":\\\\\\\"[D:user_time] \\\\\\\\t[D:user_loc] \\\\\\\\t[D:kw_weatherstatus] kw_weatherstatus:天气如何\\\\\\\",\\\\\\\"slots\\\\\\\":[{\\\\\\\"begin\\\\\\\":0,\\\\\\\"confidence\\\\\\\":100.0,\\\\\\\"father_idx\\\\\\\":-1,\\\\\\\"fuzzy_matches\\\\\\\":[],\\\\\\\"length\\\\\\\":4,\\\\\\\"name\\\\\\\":\\\\\\\"user_time\\\\\\\",\\\\\\\"need_clarify\\\\\\\":false,\\\\\\\"normalized_word\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"original_word\\\\\\\":\\\\\\\"今天\\\\\\\",\\\\\\\"word_type\\\\\\\":\\\\\\\"\\\\\\\"},{\\\\\\\"begin\\\\\\\":4,\\\\\\\"confidence\\\\\\\":100.0,\\\\\\\"father_idx\\\\\\\":-1,\\\\\\\"fuzzy_matches\\\\\\\":[],\\\\\\\"length\\\\\\\":4,\\\\\\\"name\\\\\\\":\\\\\\\"user_loc\\\\\\\",\\\\\\\"need_clarify\\\\\\\":false,\\\\\\\"normalized_word\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"original_word\\\\\\\":\\\\\\\"北京\\\\\\\",\\\\\\\"word_type\\\\\\\":\\\\\\\"\\\\\\\"},{\\\\\\\"begin\\\\\\\":8,\\\\\\\"confidence\\\\\\\":100.0,\\\\\\\"father_idx\\\\\\\":-1,\\\\\\\"fuzzy_matches\\\\\\\":[],\\\\\\\"length\\\\\\\":8,\\\\\\\"name\\\\\\\":\\\\\\\"kw_weatherstatus\\\\\\\",\\\\\\\"need_clarify\\\\\\\":false,\\\\\\\"normalized_word\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"original_word\\\\\\\":\\\\\\\"天气如何\\\\\\\",\\\\\\\"word_type\\\\\\\":\\\\\\\"\\\\\\\"}]},\\\\\\\"real_threshold\\\\\\\":1.0,\\\\\\\"threshold\\\\\\\":0.8000000119209290}\\\",\\\"slots\\\":[{\\\"begin\\\":0,\\\"confidence\\\":100.0,\\\"father_idx\\\":-1,\\\"fuzzy_matches\\\":[],\\\"length\\\":4,\\\"name\\\":\\\"user_time\\\",\\\"need_clarify\\\":false,\\\"normalized_word\\\":\\\"2021-02-04\\\",\\\"original_word\\\":\\\"今天\\\",\\\"word_type\\\":\\\"\\\"},{\\\"begin\\\":4,\\\"confidence\\\":100.0,\\\"father_idx\\\":-1,\\\"fuzzy_matches\\\":[],\\\"length\\\":4,\\\"name\\\":\\\"user_loc\\\",\\\"need_clarify\\\":false,\\\"normalized_word\\\":\\\"北京\\\",\\\"original_word\\\":\\\"北京\\\",\\\"word_type\\\":\\\"\\\"}]}\\n\",\"candidates\":[{\"intent_confidence\":100,\"match_info\":\"{\\\"group_id\\\":\\\"51\\\",\\\"id\\\":\\\"2090302\\\",\\\"informal_word\\\":\\\"\\\",\\\"match_keywords\\\":\\\"   kw_weatherstatus:天气如何\\\",\\\"match_pattern\\\":\\\"[D:user_time]\\\\t[D:user_loc]\\\\t[D:kw_weatherstatus]\\\",\\\"ori_pattern\\\":\\\"[D:user_time]\\\\t[D:user_loc]\\\\t[D:kw_weatherstatus]\\\",\\\"ori_slots\\\":{\\\"confidence\\\":100.0,\\\"domain_confidence\\\":0.0,\\\"extra_info\\\":{},\\\"from_who\\\":\\\"smart_qu\\\",\\\"intent\\\":\\\"USER_WEATHER\\\",\\\"intent_confidence\\\":100.0,\\\"intent_need_clarify\\\":false,\\\"match_info\\\":\\\"[D:user_time] \\\\t[D:user_loc] \\\\t[D:kw_weatherstatus] kw_weatherstatus:天气如何\\\",\\\"slots\\\":[{\\\"begin\\\":0,\\\"confidence\\\":100.0,\\\"father_idx\\\":-1,\\\"fuzzy_matches\\\":[],\\\"length\\\":4,\\\"name\\\":\\\"user_time\\\",\\\"need_clarify\\\":false,\\\"normalized_word\\\":\\\"\\\",\\\"original_word\\\":\\\"今天\\\",\\\"word_type\\\":\\\"\\\"},{\\\"begin\\\":4,\\\"confidence\\\":100.0,\\\"father_idx\\\":-1,\\\"fuzzy_matches\\\":[],\\\"length\\\":4,\\\"name\\\":\\\"user_loc\\\",\\\"need_clarify\\\":false,\\\"normalized_word\\\":\\\"\\\",\\\"original_word\\\":\\\"北京\\\",\\\"word_type\\\":\\\"\\\"},{\\\"begin\\\":8,\\\"confidence\\\":100.0,\\\"father_idx\\\":-1,\\\"fuzzy_matches\\\":[],\\\"length\\\":8,\\\"name\\\":\\\"kw_weatherstatus\\\",\\\"need_clarify\\\":false,\\\"normalized_word\\\":\\\"\\\",\\\"original_word\\\":\\\"天气如何\\\",\\\"word_type\\\":\\\"\\\"}]},\\\"real_threshold\\\":1.0,\\\"threshold\\\":0.8000000119209290}\",\"slots\":[{\"word_type\":\"\",\"father_idx\":-1,\"fuzzy_matches\":[],\"confidence\":100,\"name\":\"user_time\",\"length\":2,\"original_word\":\"今天\",\"begin\":0,\"need_clarify\":false,\"normalized_word\":\"2021-02-04\"},{\"word_type\":\"\",\"father_idx\":-1,\"fuzzy_matches\":[],\"confidence\":100,\"name\":\"user_loc\",\"length\":2,\"original_word\":\"北京\",\"begin\":2,\"need_clarify\":false,\"normalized_word\":\"北京\"}],\"extra_info\":{\"group_id\":\"51\",\"real_threshold\":\"1\",\"threshold\":\"0.8\"},\"confidence\":100,\"domain_confidence\":0,\"from_who\":\"pow-slu-lev1\",\"intent_need_clarify\":false,\"intent\":\"USER_WEATHER\"}],\"sentiment_analysis\":{\"pval\":0.999,\"label\":\"1\"},\"lexical_analysis\":[{\"etypes\":[\"sys_time\",\"sys_time_generic\",\"user_time\"],\"basic_word\":[\"今天\"],\"weight\":0.245,\"term\":\"今天\",\"type\":\"sys_time\"},{\"etypes\":[\"user_loc\",\"sys_loc_city\",\"sys_loc\"],\"basic_word\":[\"北京\"],\"weight\":0.245,\"term\":\"北京\",\"type\":\"user_loc\"},{\"etypes\":[],\"basic_word\":[\"天气\"],\"weight\":0.371,\"term\":\"天气\",\"type\":\"21\"},{\"etypes\":[],\"basic_word\":[\"如何\"],\"weight\":0.136,\"term\":\"如何\",\"type\":\"30\"}],\"raw_query\":\"今天北京天气如何\",\"status\":0,\"timestamp\":0}},{\"status\":0,\"msg\":\"ok\",\"origin\":\"1079260\",\"schema\":{\"intent_confidence\":1,\"slots\":[],\"domain_confidence\":0,\"slu_tags\":[],\"intent\":\"BUILT_CHAT\"},\"action_list\":[{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"chat_satisfy\",\"confidence\":-1.3588641882,\"custom_reply\":\"\",\"say\":\"很晴朗,风和日丽\",\"type\":\"chat\"},{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"chat_satisfy\",\"confidence\":-1.4944730997,\"custom_reply\":\"\",\"say\":\"挺好,晴空万里,阳光普照,你那呢?\",\"type\":\"chat\"},{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"chat_satisfy\",\"confidence\":-1.5635391474,\"custom_reply\":\"\",\"say\":\"还不错,有点凉\",\"type\":\"chat\"}],\"qu_res\":{\"qu_res_chosen\":\"\",\"candidates\":[],\"sentiment_analysis\":{\"pval\":0,\"label\":\"\"},\"lexical_analysis\":[],\"raw_query\":\"\",\"status\":0,\"timestamp\":0}},{\"status\":0,\"msg\":\"ok\",\"origin\":\"1079257\",\"schema\":{\"intent_confidence\":0,\"slots\":[],\"domain_confidence\":0,\"slu_tags\":[],\"intent\":\"\"},\"action_list\":[{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"fail_action\",\"confidence\":100,\"custom_reply\":\"\",\"say\":\"我不知道应该怎么答复您。\",\"type\":\"failure\"}],\"qu_res\":{\"qu_res_chosen\":\"\",\"candidates\":[],\"sentiment_analysis\":{\"pval\":0,\"label\":\"\"},\"lexical_analysis\":[],\"raw_query\":\"\",\"status\":0,\"timestamp\":0}},{\"status\":0,\"msg\":\"ok\",\"origin\":\"1079256\",\"schema\":{\"intent_confidence\":0,\"slots\":[],\"domain_confidence\":0,\"slu_tags\":[],\"intent\":\"\"},\"action_list\":[{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"fail_action\",\"confidence\":100,\"custom_reply\":\"\",\"say\":\"我不知道该怎么答复您。\",\"type\":\"failure\"}],\"qu_res\":{\"qu_res_chosen\":\"\",\"candidates\":[],\"sentiment_analysis\":{\"pval\":0,\"label\":\"\"},\"lexical_analysis\":[],\"raw_query\":\"\",\"status\":0,\"timestamp\":0}},{\"status\":0,\"msg\":\"ok\",\"origin\":\"1079258\",\"schema\":{\"intent_confidence\":100,\"slots\":[],\"domain_confidence\":0,\"slu_tags\":[],\"intent\":\"\"},\"action_list\":[{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"fail_action\",\"confidence\":100,\"custom_reply\":\"\",\"say\":\"不好意思，我没有理解您的问题，可以换个方式再问下呢。\",\"type\":\"failure\"}],\"qu_res\":{\"qu_res_chosen\":\"\",\"candidates\":[],\"sentiment_analysis\":{\"pval\":0,\"label\":\"\"},\"lexical_analysis\":[],\"raw_query\":\"\",\"status\":0,\"timestamp\":0}}],\"dialog_state\":{\"contexts\":{\"SYS_PRESUMED_HIST\":[\"今天北京天气如何\",\"很晴朗,风和日丽\"]},\"skill_states\":{}}},\"error_code\":0}";
        System.out.println(dialogService.getAnswer(response));
    }
}
