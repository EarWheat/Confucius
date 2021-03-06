package com.education.confucius.Service.DialogService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Constants.Constants;
import com.education.confucius.Entity.Dialog.Request;
import com.education.confucius.Entity.Dialog.DialogParam;
import com.pangu.Http.request.HttpClient;
import com.pangu.HttpSession.HttpSessionContext;
import com.pangu.Redis.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

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
        String answer = Constants.DIALOG_ANSWER_REVEAL;
        try {
            DialogParam dialogParam = getDefaultDialogParam(chatRequest);  // 获取默认聊天配置
            logger.info("dialog param :{}" ,dialogParam.toString());
            JSONObject params = JSONObject.parseObject(dialogParam.toString());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json");
            String requestUrl = Constants.BAIDU_DIALOG_URL + "?access_token=" + token;
            String response = HttpClient.doPostJsonHttp(requestUrl, params, httpHeaders, 2000,2000);
            answer = getAnswer(response, chatRequest.getUser_id());
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
        dialogParam.setLog_id(UUID.randomUUID().toString());
//        HttpSession session = HttpSessionContext.getHttpSession(sessionId);
        String requestSessionId = Optional.ofNullable(RedisUtil.get(Constants.DIALOG_REDIS_KEY.concat(chatRequest.getUser_id()))).orElse("");
        dialogParam.setSession_id(requestSessionId);
        logger.info("dialog param request session id:{}", dialogParam.getSession_id());
        dialogParam.setService_id(Constants.BAIDU_DIALOG_SERVICE_ID);
        dialogParam.setRequest(chatRequest);
        return dialogParam;
    }

    /**
     * 根据Response返回最好的回答
     * @param response
     * @return
     */
    private String getAnswer(String response, String userId){
        try {
            logger.info("dialog answer:{}", response);
            String result = JSONObject.parseObject(response).getString(Constants.BAIDU_DIALOG_ANSWER_RESULT);
            JSONObject resultObject = JSONObject.parseObject(result);
            String responseList = resultObject.getString(Constants.BAIDU_DIALOG_ANSWER_RESULT_LIST);
            String responseSessionId = resultObject.getString(Constants.BAIDU_DIALOG_SESSION_ID);
//            RedisUtil.setex(Constants.DIALOG_REDIS_KEY.concat(userId),600, responseSessionId);
            JSONArray answerArray = JSONArray.parseArray(responseList);
            if(answerArray.size() > 0){
                JSONObject bestAnswer = answerArray.getJSONObject(0);
                String actionList = bestAnswer.getString(Constants.BAIDU_DIALOG_ANSWER_ACTION_LIST);
                JSONArray actionArray = JSONArray.parseArray(actionList);
                if(actionArray.size() > 0){
                    JSONObject answer = actionArray.getJSONObject(0);
                    String answerResult = answer.getString(Constants.BAIDU_DIALOG_ANSWER_SAY);
                    String actionId = answer.getString(Constants.BAIDU_DIALOG_ANSWER_ACTION_ID);
                    // 兼容新闻输出
                    if(actionId.startsWith(Constants.BAIDU_DIALOG_ANSWER_NEWS)){
                        String customReply = answer.getString(Constants.BAIDU_DIALOG_ANSWER_CUSTOM_REPLAY);
                        JSONObject newsSummary = JSONObject.parseObject(customReply);
                        JSONArray newsList = JSONArray.parseArray(JSONObject.toJSONString(newsSummary.get(Constants.BAIDU_DIALOG_ANSWER_NEWS_LIST)));
                        List<String> newsTitles = new ArrayList<>(newsList.size());
                        for(Object object : newsList){
                            JSONObject temp = (JSONObject) object;
                            newsTitles.add(temp.getString(Constants.BAIDU_DIALOG_ANSWER_NEWS_TITLE));
                        }
                        StringBuilder stringBuilder = new StringBuilder(answerResult);
                        stringBuilder.append("</br>");
                        newsTitles.forEach(n -> stringBuilder.append(n).append("</br>"));
                        answerResult = stringBuilder.toString();
                    }
                    return answerResult;
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
        String response = "{\"result\":{\"version\":\"2.0\",\"timestamp\":\"2021-03-06 08:19:03.268\",\"service_id\":\"S46282\",\"log_id\":\"74926f5a-4f9e-4898-bde4-8d8adce46e41\",\"session_id\":\"service-session-id-1614989934229-88f5e3da-7e11-11eb-bbf5-51fd47d5e1a3\",\"interaction_id\":\"interaction-8e59260c-7e11-11eb-b199-bfe3e3778259\",\"response_list\":[{\"status\":0,\"msg\":\"ok\",\"origin\":\"1079256\",\"schema\":{\"intent_confidence\":100.0,\"slots\":[],\"domain_confidence\":0.0,\"slu_tags\":[],\"intent\":\"NEWS_GENERAL\"},\"action_list\":[{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"news_broadcast_satisfy\",\"confidence\":100.0,\"custom_reply\":\"{\\\"news_size\\\":40,\\\"path\\\":\\\"40\\\",\\\"news_list\\\":[{\\\"summary\\\":\\\"新浪娱乐讯，27日午间，一条疑似吴孟达好友“吴志雄”的朋友圈曝光，他发文称“我的老朋友达哥 已于一个小时前走了，达哥一路好走，永远怀念你”，引发了“吴孟达去世”的传言。27日，港媒曝吴孟达因病情恶化转入ICU，亲友已赶往医院。\\\",\\\"publishTime\\\":\\\"2021-02-27T14:59:00\\\",\\\"title\\\":\\\"好友辟谣吴孟达去世传言 但表示今天可能“会走”\\\",\\\"hotness\\\":113101,\\\"id\\\":\\\"1ade9c792dd124674a69fee906bfdacf\\\"},{\\\"summary\\\":\\\"2.27日吴孟达因肝癌意外去世的消息让人悲伤，2021年刚开始就有无数人去世，就在吴孟达去世之后，群星纷纷送上了自己的祝福，首先就是德华在得知#吴孟达去世# 后发声悼念：“达哥走了，达哥在那边无病无忧了，一路走好！”\\\",\\\"publishTime\\\":\\\"2021-02-27T19:02:00\\\",\\\"title\\\":\\\"群星悼念吴孟达：刘德华悼念，周星驰不舍得无法接受，林志颖痛哭\\\",\\\"hotness\\\":47720,\\\"id\\\":\\\"2df9b49ecf032becd8248a713c9ef29e\\\"},{\\\"summary\\\":\\\"据香港“东网”消息，香港影星吴孟达患上肝癌，现正在香港接受治疗。吴孟达今日情况转为危殆，已转入ICU留医，医生已通知他的家人到医院与他见面。好友田启文也赶往医院。21日，有港媒称吴孟达在20日重病入医院，据称目前已被安排在肿瘤科留医，情况未明。报道提到，吴孟达曾于2014年因为病毒感染导致心脏衰竭，一度被送入ICU抢救。\\\",\\\"publishTime\\\":\\\"2021-02-27T13:31:00\\\",\\\"title\\\":\\\"港媒：吴孟达病情危重转入ICU，医生通知亲友赶往医院与他见面\\\",\\\"hotness\\\":42942,\\\"id\\\":\\\"38198d084355570f2fdbbfdf05bbc2f1\\\"},{\\\"summary\\\":\\\"环球网消息，韩国首尔市政府3日表示，2020年韩籍居民登记人口为966.8465万人，外籍居民登记人口为24.2623万人，合计991.1088万人。这是首尔市人口时隔32年首次跌破千万大关。报道称，韩国人口危机加剧，出生率近年来不断下降，去年更是首次出现了死亡人口大于出生人口的现象。英国牛津大学人口学教授曾对韩国人口问题作出预警，称韩国或将成为“全球首个消失的国家”。\\\",\\\"publishTime\\\":\\\"2021-03-04T07:51:38\\\",\\\"title\\\":\\\"首尔人口跌回1987年！牛津教授警告：韩可能将成“首个消失的国家”\\\",\\\"hotness\\\":37531,\\\"id\\\":\\\"59447d662821f3603cc1d2b28f553b44\\\"},{\\\"summary\\\":\\\"3月3日，据台湾媒体报道，日本兵乓球选手福原爱与江宏杰结婚4年多，婚后育有一对儿女，近期她返回日本工作，上节目透露自己想带着孩子回日本上学，还被发现没有戴婚戒，引发了外界对两个人离婚的猜测，虽然早前江宏杰在某活动上否认了婚变的消息，但近日女方被日本八卦媒体曝光了与神秘男子一起返回酒店的照片，被怀疑是婚内出轨。\\\",\\\"publishTime\\\":\\\"2021-03-03T17:32:00\\\",\\\"title\\\":\\\"福原爱间接承认与江宏杰有离婚的想法，坚决否认自己婚内出轨\\\",\\\"hotness\\\":36755,\\\"id\\\":\\\"697c895e6c8353f321d35e01da924e1b\\\"},{\\\"summary\\\":\\\"同居不违法，但这个女孩子这种情况不算强奸？生孩子不是生命的全部！近日，两则有关女孩的新闻同时登上热搜，引起网友们的极大议论，20岁智障女孩和55岁老头结婚，父亲说饿不住就行，19岁女孩单枪匹马持刀抢金店，只因为没有经济来源。并且他们活得很勇敢！至少在抢金店女孩来看，这个来的猛烈来得快，即便抢金店不成被拘捕，也至少可以活命！\\\",\\\"publishTime\\\":\\\"2021-03-02T11:54:00\\\",\\\"title\\\":\\\"20岁智障女孩和19岁抢金店女孩上热搜：她们和你一样勇敢地活着！\\\",\\\"hotness\\\":31861,\\\"id\\\":\\\"6272c2951292bdd5e6f3a0859919f32f\\\"},{\\\"summary\\\":\\\"宋轶在微博发文为新剧《爱的理想生活》宣传，她晒出了一组在剧中的造型图，图片中写着“好嫁风穿搭”，宣传文案中也突出了“好嫁风”。然而“好嫁”这两个字却有些敏感，有些人能理解这是剧中人物设定，有些人却觉得这个词本身就有问题，不应该作为宣传文案。对此，宋轶昨晚对这条动态编辑了好几次，最后十分直接地在“好嫁风”三个字上打了叉，改成了没有争议的词语。\\\",\\\"publishTime\\\":\\\"2021-03-02T10:10:00\\\",\\\"title\\\":\\\"宋轶道歉因用“好嫁风”宣传新剧引争议\\\",\\\"hotness\\\":31746,\\\"id\\\":\\\"2ae9484c7f127d7c40999e4c22b4e5d5\\\"},{\\\"summary\\\":\\\"3月3日，就社会关注的“货拉拉女乘客坠车死亡事件”，长沙市公安局高新区分局发布通报，披露更多案件细节。货车司机、犯罪嫌疑人周某春以涉嫌过失致人死亡罪被检察机关批准逮捕。悲剧起因于被害人车某某拒绝犯罪嫌疑人周某春的搬运服务和额外收费，而犯罪嫌疑人出于不满，抢接下一单业务，更改了行车路线。从警方通报看，以过失致人死亡罪对其立案追诉是准确的。\\\",\\\"publishTime\\\":\\\"2021-03-04T16:22:26\\\",\\\"title\\\":\\\"央视评货拉拉女用户坠亡案货拉拉涉事司机被批捕警方通报案件更多细节\\\",\\\"hotness\\\":30619,\\\"id\\\":\\\"cbcfc8a13a959012d43b5641c4f7ebb5\\\"},{\\\"summary\\\":\\\"2月25日天津肖某某女教师一段录音曝光，录音中内容尽是贬低歧视穷人家的孩子，推崇高官富商，以家长收入衡量孩子的素质。肖某某教师称，以往送到她班级里来的学生家里都是当官的，要不就是家里情况特别好的，事业单位。该女教师疑曾获得“最美教师”“工会骨干委员”等称号。天津市教育局通报该女教师歧视穷学生：立即成立检查组，对该情况进行核查。目前，该教师已经被停职。\\\",\\\"publishTime\\\":\\\"2021-02-27T11:00:00\\\",\\\"title\\\":\\\"天津“最美教师”当众歧视穷学生，以家长收入衡量学生\\\",\\\"hotness\\\":28399,\\\"id\\\":\\\"4e82d6d91a9a357fdaf2368a6a4f2ae2\\\"},{\\\"summary\\\":\\\"2月13日开始，中国中央电视台开始播放日本动画作品《工作细胞》第一季，在中国国内网络上引发热烈讨论。对于这样的举动，日媒称，这是时隔14年后，央视播放日本动画作品。上世纪80年代，中国各电视台都曾热播过日本的动画作品。《工作细胞》是日本漫画家清水茜的漫画作品，2018年改编为动画。\\\",\\\"publishTime\\\":\\\"2021-03-01T09:02:41\\\",\\\"title\\\":\\\"央视时隔14年后再播日本动画引热议\\\",\\\"hotness\\\":24452,\\\"id\\\":\\\"e48d2da34c67779a8c7a7304301662b5\\\"},{\\\"summary\\\":\\\"海外网2月28日电近日，印度特伦甘纳邦的一只公鸡引发外媒关注。由于不愿参加一场斗鸡比赛，它在试图逃跑时身上绑着的刀刚好刺中其主人，最终导致对方因失血过多死亡。据今日俄罗斯网站27日报道，这起有些匪夷所思的事件于2月23日发生在印度特伦甘纳邦的贾格尔地区。在被送往医院的途中，萨蒂什因失血过多身亡。自案发以来，拉贾一直被当地警察局拘留。\\\",\\\"publishTime\\\":\\\"2021-02-28T09:29:47\\\",\\\"title\\\":\\\"印度公鸡逃离斗鸡比赛时用刀刺死主人 将现身法庭\\\",\\\"hotness\\\":22532,\\\"id\\\":\\\"9f15173c03a0d4cf484c8ef6a873da91\\\"},{\\\"summary\\\":\\\"风波发酵后，福原爱在4日两度道歉，承认行为轻率需要反省，抱歉让老公、孩子及家人感到不安和担心，她会和江宏杰共同面对问题。福原爱公开亲笔信道歉后，江宏杰正式回应，对妻子的爱没有改变。\\\",\\\"publishTime\\\":\\\"2021-03-04T20:01:00\\\",\\\"title\\\":\\\"福原爱诚恳道歉获老公力挺，江宏杰发声表白：我对小爱的爱不曾改变\\\",\\\"hotness\\\":21134,\\\"id\\\":\\\"f0c1e95c3252e9c4bf420d7ec24bc523\\\"},{\\\"summary\\\":\\\"当地时间2月25日，美国国防部证实，美军当天空袭了叙利亚境内伊朗支持的民兵武装，以报复近期美国在伊拉克设施遭到的袭击。这是拜登上任后发动的首次军事袭击，拜登政府宣称，发动空袭是为了“给地区局势降温”。目前已知的是17人死亡，我们来看一下美国的理由：说是“给地区局势降温”——用战争来达成降温？这理由也太奇葩了，拜登连一个较好听的借口都不想找，就这么直接来几发导弹。\\\",\\\"publishTime\\\":\\\"2021-02-27T00:00:00\\\",\\\"title\\\":\\\"拜登为什么下令空袭伊朗境外民兵，17人死亡\\\",\\\"hotness\\\":20354,\\\"id\\\":\\\"cfda96073c07b05f80500d5e0b5e49c2\\\"},{\\\"summary\\\":\\\"近日，人力资源和社会保障部就延迟退休作出回应：目前正会同相关部门在研究具体改革方案。有关延迟退休的议题再次引起公众普遍关注。延迟退休年龄，本质上是人们生命周期结构变化的必然结果。此外，还需注意防止群体之间的非公平现象，不同群体、不同个体的工作特点、待遇不同，相关举措要想得全面一些，灵活一些，取得最大公约数。\\\",\\\"publishTime\\\":\\\"2021-02-28T17:14:00\\\",\\\"title\\\":\\\"央视评延迟退休年龄：取得最大公约数要周密施策\\\",\\\"hotness\\\":20171,\\\"id\\\":\\\"017812c58788051a06d69f042f705c07\\\"},{\\\"summary\\\":\\\"而在今年夏天到来之前，又一股新的热潮开始在社交平台上风靡起来，那就是——成人去优衣库试穿童装（晒照片）。晚报菌在优衣库的官方店铺看到，虽然是童装款式，但是尺码已经到160cm，其实完全可以满足身材比较矮小的成人穿着。工作人员称，目前优衣库并没有明确禁止成人试穿童装，这种现象也比较多，而对于“被撑坏的衣服该如何处理”的问题，工作人员也直言会“特价处理掉”。\\\",\\\"publishTime\\\":\\\"2021-03-04T09:16:15\\\",\\\"title\\\":\\\"优衣库回应成人试穿童装自拍引发跟风，这是什么样的审美观？\\\",\\\"hotness\\\":19605,\\\"id\\\":\\\"2d04a3db30cdf4729d937cb5812a5e79\\\"},{\\\"summary\\\":\\\"近日，有消费者反映部分海底捞门店下架了牛肉粒，换成了与牛肉粒外观相似的“味伴侣”。蓝鲸记者致电海底捞总部，客服表示，海底捞目前确实已在上海部分门店试点将牛肉粒替换成一种大豆素肉制品，主要是因为之前很多顾客将牛肉粒小料当作菜品无限制使用，造成了浪费，换成“味伴侣”以减少浪费。\\\",\\\"publishTime\\\":\\\"2021-03-04T23:30:00\\\",\\\"title\\\":\\\"“海底捞牛肉粒变素了”冲上微博热搜\\\",\\\"hotness\\\":19170,\\\"id\\\":\\\"01e67a6130954e6c07764e950b333092\\\"},{\\\"summary\\\":\\\"2月27日上午，美空军一架RC-135S导弹监视机从冲绳起飞，穿越东海防空识别区，抵近黄海、东海交界区域进行往返侦察。\\\",\\\"publishTime\\\":\\\"2021-02-27T14:14:00\\\",\\\"title\\\":\\\"南海战略态势感知：美空军一架导弹监视机在黄、东海侦察\\\",\\\"hotness\\\":18469,\\\"id\\\":\\\"bb07626625339fe1ba1411db8773101a\\\"},{\\\"summary\\\":\\\"据路透社报道，日本内阁官房长官加藤胜信当地时间周一表示，日本政府已要求中国方面停止对日本赴华入境人员采取肛拭子的检测方式，加藤胜信称这一检测过程会造成检测者极大的心理负担。日本政府尚未收到中方将改变检测程序的回应，因此日本政府将继续敦促中方免除日本公民接受这种检验方法。\\\",\\\"publishTime\\\":\\\"2021-03-02T08:33:00\\\",\\\"title\\\":\\\"日本：请中方不要对日本赴华入境人员检测肛拭子\\\",\\\"hotness\\\":18187,\\\"id\\\":\\\"45b42e157a8aeda3149a05c0235daa09\\\"},{\\\"summary\\\":\\\"央广网吉安县3月1日消息 1日下午15时19分，江西省气象局租用的北大荒通用航空公司B－10GD型号飞机，准备在江西吉安县上空执行人工增雨任务时，飞机突然坠落，造成机上5人遇难。晚上11点半记者了解到，事故调查组正在成立，官方回应记者目前能确定飞机在起飞前曾有备案。对于这次突发意外，江西省气象局局长詹丰兴承诺将会举一反三，进一步规范管理，一定会把事故原因查清楚。\\\",\\\"publishTime\\\":\\\"2021-03-01T23:55:00\\\",\\\"title\\\":\\\"江西一飞机坠毁造成5人死亡后续：起飞前有备案 民航监管局已介入\\\",\\\"hotness\\\":16791,\\\"id\\\":\\\"9cd369814326e9a2af14b1176bad226c\\\"},{\\\"summary\\\":\\\"近期，全国连续有多名发布侮辱诋毁卫国戍边英雄官兵言论的网民，被执法机关果断采取行动予以处理。特别引起关注的是，2月19日，南京市公安局发布警方通报，对在新浪微博发布恶意歪曲事实真相、诋毁贬损卫国戍边英雄官兵违法言论的仇某某依法予以刑事拘留。从立法到执法，从线上到线下，依法保护英烈荣光，江苏一直在行动。\\\",\\\"publishTime\\\":\\\"2021-03-01T07:03:00\\\",\\\"title\\\":\\\"护英烈荣光，江苏在行动 南京警方及时出手处理诋毁贬损英雄官兵网民引发广泛关注\\\",\\\"hotness\\\":16559,\\\"id\\\":\\\"812716762325cd4f16ac4ff2b9c3e8a7\\\"},{\\\"summary\\\":\\\"海外网3月2日电海外华裔群体遭遇种族歧视的案件愈发引人关注。日前，一名37岁的中国教师在英国慢跑时遭4人无端袭击，致面部受伤严重。当地时间3月1日，他在接受采访时称，“不再觉得这里安全。”综合英国广播公司、ITV news消息，这名37岁的中国男子名叫王鹏（音译），是英国南安普顿大学的一名讲师。近日，他在英格兰东南部南安普顿的郊区伍尔斯顿慢跑时，在车内遭到4名男子殴打。\\\",\\\"publishTime\\\":\\\"2021-03-02T09:43:00\\\",\\\"title\\\":\\\"\u200B中国教师在英国遭4人围殴：\\\\\\\"不再觉得这里安全\\\\\\\"\\\",\\\"hotness\\\":16426,\\\"id\\\":\\\"6aa3b938a10f41a6814c7dd34c973f08\\\"},{\\\"summary\\\":\\\"2月28日0—24时，31个省和新疆生产建设兵团报告新增确诊病例19例，均为境外输入病例。四川9例，广东6例，北京2例，上海2例。无新增死亡病例，无新增疑似病例。累计报告确诊病例89912例，现有疑似病例1例，累计追踪到密切接触者978836人。累计收到港澳台地区通报确诊病例12008例，香港特别行政区11005例，出院10536例，死亡199例。台湾地区955例，出院919例，死亡9例。\\\",\\\"publishTime\\\":\\\"2021-03-01T09:15:00\\\",\\\"title\\\":\\\"本土零新增！31省区市29日新增确诊病例19例，均为境外输入\\\",\\\"hotness\\\":15966,\\\"id\\\":\\\"b5481d3d24a5794e4b20eb05e4832c4c\\\"},{\\\"summary\\\":\\\"新浪娱乐讯 2月26日，王一博[微博]回应邓亚萍喊他参加奥运会街舞比赛：“谢谢亚萍姐对我的鼓励！开心！激动！我有奥运梦！我想参加街舞、滑雪、滑板！但是我暂时还没有这个能力…我还是先吃苦练习吧，我能吃苦…”继滑板后，街舞也将入选2024年的巴黎奥运会新项目。王一博不愧是我们喜欢的明星。\\\",\\\"publishTime\\\":\\\"2021-02-27T00:54:00\\\",\\\"title\\\":\\\"王一博回应邓亚萍参与2024奥运会街舞比赛：我想参加滑板比赛\\\",\\\"hotness\\\":15919,\\\"id\\\":\\\"112f3c812b96b1248fc47da20dc54dac\\\"},{\\\"summary\\\":\\\"国家卫健委高级别专家组组长钟南山表示，今年6月，中国新冠疫苗接种率计划达到40%。钟南山表示，为了能够让经济复苏、让学校复学、让社会活动逐渐恢复，不能长期把整个国家封闭起来，因此需要形成群体免疫。钟南山表示，从全球来看，以色列接种疫苗人数占总人口的比例最高，为92.46%，阿联酋超过60%，英国超过30%，美国为22%，但中国仅为3.56%。\\\",\\\"publishTime\\\":\\\"2021-03-02T17:33:23\\\",\\\"title\\\":\\\"钟南山称全球群体免疫需两至三年\\\",\\\"hotness\\\":15578,\\\"id\\\":\\\"89129a5c628c94ca9587dfc83460c99d\\\"},{\\\"summary\\\":\\\"近日，湖南城市学院肖同学返校，展开卷了2个月的被子，发现5只刚出生不久的黄色猫仔。肖同学介绍，大猫目前还未找到，5只小猫在医院过得很好。3月1日，一名在校的学姐得知情况后，领养了2只猫仔。\\\",\\\"publishTime\\\":\\\"2021-03-02T16:32:00\\\",\\\"title\\\":\\\"湖南一大学生返校发现床铺成了猫窝！被子里5只小猫仔，猫妈妈尚未找到，给找了个“奶妈”\\\",\\\"hotness\\\":15352,\\\"id\\\":\\\"412119d9fcd1ddb4227078165f959546\\\"},{\\\"summary\\\":\\\"3月2日，针对网友建议3月14日加班进行婚姻登记，安徽省民政厅社会事务处工作人员回应称，建议各地自行安排是否加班，有需求当事人可提前咨询户籍地婚姻登记处，“我们认为只要感情深，哪一天都是好日子”。安徽怀宁县民政局此前公开回复称，该县婚姻登记处不予加班办理婚姻登记。\\\",\\\"publishTime\\\":\\\"2021-03-02T19:10:00\\\",\\\"title\\\":\\\"民政厅回应拒绝3月14日加班建议：只要感情深，哪天都是好日子！\\\",\\\"hotness\\\":14670,\\\"id\\\":\\\"7a0fef961b8d3749679ae64b11105eb6\\\"},{\\\"summary\\\":\\\"近日，河南有位少年在路上扶起摔倒的老人，还把老人送到了医院，却被老人及其家属索要了5000元作为赔偿款。事后，经交警认定，老人承担本起事件的全部责任。少年王某的父母在弄清事情的原委后，请求老人退钱，双方通过协商，老人退还了4000元，但仍有1000元不肯退还，并称即使去法院打官司也不会退。\\\",\\\"publishTime\\\":\\\"2021-02-28T11:00:00\\\",\\\"title\\\":\\\"近日，河南有位少年在路上扶起摔倒的老人送到了医院后被索要五千\\\",\\\"hotness\\\":14339,\\\"id\\\":\\\"fb2bdd7c187242fd8285c69c172dde20\\\"},{\\\"summary\\\":\\\"华语影坛泰斗、黄金配角吴孟达本月27日因肝癌辞世，震惊演艺圈之馀，也牵动亿万观众的心。几乎在同一时间，港媒曝光达叔遗产分配方案，据马来西亚《光明日报》透露，达叔资产逾千万，现任太太侯珊燕分一半，馀下分给5名子女。TVB专辑中曝光达叔昔日照片，第一任太太麦莉莉和双胞胎女儿罕见曝光。\\\",\\\"publishTime\\\":\\\"2021-03-01T12:15:58\\\",\\\"title\\\":\\\"吴孟达遗产分配方案曝光 港媒:吴孟达遗产现任妻子独占一半\\\",\\\"hotness\\\":13447,\\\"id\\\":\\\"4d9ab148e08c7a52ab3b5052a4b06637\\\"},{\\\"summary\\\":\\\"齐鲁网·闪电新闻2月27日讯 当地时间2月26日，俄罗斯外长拉夫罗夫在莫斯科会见来访的阿富汗外长穆罕默德·哈尼夫·阿特马尔。拉夫罗夫在随后举行的发布会上指出，美军2月25日在空袭叙利亚前5分钟才通报驻叙利亚俄军，这毫无意义。\\\",\\\"publishTime\\\":\\\"2021-02-27T10:51:00\\\",\\\"title\\\":\\\"81秒丨俄罗斯谴责美军空袭叙利亚前5分钟才通报俄军：毫无意义\\\",\\\"hotness\\\":13441,\\\"id\\\":\\\"b36da6f22062c8b1acbad797320954ec\\\"},{\\\"summary\\\":\\\"近日，曾经上过热搜的“电视剧女主睡在我家床案”，相关法院作出一审判决：判令两部电视剧的出品方赔偿房屋业主财产损失、房屋占有费等40馀万元。当原告即业主方队该判决结果并不认可，表示会继续提起上诉。随后，女业主将电视剧出品方、物业公司、播放平台等诉至法院，要求赔偿近300万元。\\\",\\\"publishTime\\\":\\\"2021-03-04T09:15:00\\\",\\\"title\\\":\\\"“电视剧女主睡在我家床案”出品方赔业主40万，业主不服继续上诉\\\",\\\"hotness\\\":13144,\\\"id\\\":\\\"44933efda1482cb9433b8df66f5fed96\\\"},{\\\"summary\\\":\\\"吴孟达真的可以说是很多80后和90后青年的集体童年回忆了，虽然达叔的离去，让很多人都感到非常惋惜，但斯人已去，如今我们所能做的也只有为天国的达叔送去祝福了，而在3月3日当天，吴孟达的治丧委员会也正式发布了达叔的讣告。吴孟达治丧会3月3日正式发讣告，周星驰等老友却未必出席。\\\",\\\"publishTime\\\":\\\"2021-03-03T14:26:00\\\",\\\"title\\\":\\\"心疼！吴孟达治丧会3月3日正式发讣告，周星驰等老友却未必出席\\\",\\\"hotness\\\":11842,\\\"id\\\":\\\"310ca98bacd39c96c9147511c51cd088\\\"},{\\\"summary\\\":\\\"今天，国家航天局发布3幅由我国首次火星探测任务天问一号探测器拍摄的高清火星影像图，包括2幅黑白图像和1幅彩色图像。2月26日起，天问一号在停泊轨道开展科学探测，环绕器高分辨率相机、中分辨率相机、矿物光谱仪等科学载荷陆续开机，获取科学数据。中分辨率相机具备自动曝光和遥控调节曝光功能，能够绘制火星全球遥感影像图，进行火星地形地貌及其变化的探测。\\\",\\\"publishTime\\\":\\\"2021-03-04T09:00:00\\\",\\\"title\\\":\\\"国家航天局发布天问一号探测器拍摄高清火星影像\\\",\\\"hotness\\\":11804,\\\"id\\\":\\\"31cc65a95743b5ab69876e5f2dfd1899\\\"},{\\\"summary\\\":\\\"新华社堪培拉3月1日电 位于堪培拉的澳大利亚国立大学1日发布的数据显示，受澳政府对外国投资、特别是中国投资进行越来越多审查所带来的影响，2020年中国对澳投资比上一年下降61%。据澳国立大学介绍，东亚经济研究所负责“中国在澳大利亚投资数据库”项目，持续统计2014年至2020年该领域投资额。\\\",\\\"publishTime\\\":\\\"2021-03-01T20:40:00\\\",\\\"title\\\":\\\"澳大学：中国对澳投资大幅下降 澳政府审查限制是重要原因\\\",\\\"hotness\\\":11674,\\\"id\\\":\\\"92c3d58f19b047e57021e981caf6c346\\\"},{\\\"summary\\\":\\\"外界对黄晓明与baby的婚姻一直非常关注，两人一直被传婚变，而黄晓明与baby始终未回应过两人的现状。虽说这次黄晓明为baby庆生，但是在网友眼中黄晓明的生日祝福略显得有点卑微了，两人从去年的婚姻状况就受到网友的关注，不管怎样，两人的婚姻关系还是让网友雾里看花。为了打破婚变传言，2月28日晚，黄晓明与baby会参加活动，不知现场两人会不会一同亮相。\\\",\\\"publishTime\\\":\\\"2021-02-28T13:12:00\\\",\\\"title\\\":\\\"黄晓明为baby庆生不再称呼媳妇，两人无互动，婚姻再次引猜测\\\",\\\"hotness\\\":11623,\\\"id\\\":\\\"5256c15a4f293850d0eb7a8e8b35a8bd\\\"},{\\\"summary\\\":\\\"】大陆海关总署日前宣布从3月1日起暂停台湾地区菠萝输入大陆。3月1日，台湾主持人陈挥文就此事连线大陆网友，提问称“暂停进口台湾凤梨是否会对大陆造成影响”。这名山东网友表示，广东湛江市的徐闻县每年生产菠萝70万吨，大陆一年生产200万吨，不进口台湾凤梨反而有好处。\\\",\\\"publishTime\\\":\\\"2021-03-02T13:33:00\\\",\\\"title\\\":\\\"视频|台名嘴惊讶大陆年产菠萝200万吨：你们自己就有凤梨啊\\\",\\\"hotness\\\":11385,\\\"id\\\":\\\"537d8c1111f1dba254987fec14bccc98\\\"},{\\\"summary\\\":\\\"近日，“错换人生28年”事件“错换”被指系“偷换”引关注。此案有多个疑点有待查证，其中网友质疑姚策生母杜女士发表的“阻断针错打在郭威身上”一说。26日，杜女士回应称，不是阻断针，是乙肝疫苗，并说是医院未能及时为郭威接种首针疫苗，郭威满月和半岁时均接种了疫苗。经调查，姚策和郭威在淮河医院出生住院期间均没有接种乙肝疫苗的第一针，原因不详。\\\",\\\"publishTime\\\":\\\"2021-02-27T07:26:00\\\",\\\"title\\\":\\\"姚策生母回应“错给郭威打阻断针”质疑：打的是乙肝疫苗\\\",\\\"hotness\\\":11381,\\\"id\\\":\\\"b39f0a3e982f8111b108f251761e02c9\\\"},{\\\"summary\\\":\\\"当地时间3月1日，世卫组织举行新冠肺炎例行发布会，世卫组织卫生紧急项目负责人迈克尔·瑞安表示，年底前结束疫情的想法是不现实的，现在还为时尚早，聪明的做法是尽可能减少住院病例数量，这是新冠肺炎大流行导致的悲剧。目前世卫组织的关注焦点是尽可能控制病毒传播，以避免病毒出现变异，同时减少需要就医的病例。\\\",\\\"publishTime\\\":\\\"2021-03-02T09:38:12\\\",\\\"title\\\":\\\"世卫组织迈克尔·瑞安：今年年底前结束疫情的想法不现实 现在还为时尚早\\\",\\\"hotness\\\":11307,\\\"id\\\":\\\"e08cca35a4cdb5e65ce2539165c833a8\\\"},{\\\"summary\\\":\\\"人民网堪培拉3月5日电 北京时间4日晚至5日凌晨的数小时内，新西兰北部海域连发3次7级以上强震，最大震级为8.1级。新西兰国家紧急事务管理局称，东海岸预计将面临海啸威胁，呼吁民众撤离。据美国地质勘探局网站消息，北京时间4日晚21时27分，新西兰北岛吉斯伯恩东北部约175公里海域发生7.3级地震。据悉，目前暂无地震造成财产损失或人员伤亡的报告。\\\",\\\"publishTime\\\":\\\"2021-03-05T15:21:55\\\",\\\"title\\\":\\\"数小时内连发3次7级以上强震新西兰海岸民众忙撤离\\\",\\\"hotness\\\":10892,\\\"id\\\":\\\"1d9c2ab473015885dd031ab57e2c68fb\\\"},{\\\"summary\\\":\\\"2月27日，张小斐全国粉丝后援会解散的消息登上热搜，以往这种现象只会发生在明星个人形象崩塌的情况下，然而张小斐近日凭借电影新作《你好，李焕英》，一跃成为40多亿的票房女王，一时间风头正劲，全国粉丝后援会此举显然也有点让人摸不足着脑。不得不说，张小斐后援会解散这件事，无疑也是揭开了行业内的一块遮羞布，那就是经纪公司、艺人以及粉丝三方的关系已经不再纯粹。\\\",\\\"publishTime\\\":\\\"2021-02-27T21:31:00\\\",\\\"title\\\":\\\"张小斐全国粉丝后援会解散上热搜，真是揭开了行业内的一块遮羞布\\\",\\\"hotness\\\":10736,\\\"id\\\":\\\"7f640481b30c441707dd49294e2f9fc0\\\"},{\\\"summary\\\":\\\"美国发布报告，指责沙特太子萨勒曼下令杀死了著名记者卡舒吉。沙特阿拉伯外交部在一份声明中称沙特阿拉伯王国政府完全不接受这样的负面、虚假和不可接受的评估结果，并指责该报告中包含不正确的信息和结论。沙特重申了先前的立场，认为这是一种可憎的罪行，是对沙特阿拉伯法律和价值观的公然违反，这项罪行是由一群人犯下的，这些人损害了权威。\\\",\\\"publishTime\\\":\\\"2021-02-27T00:00:00\\\",\\\"title\\\":\\\"沙特外交部：杀卡舒吉是罪行，反对美国干涉主权，维护80年友谊\\\",\\\"hotness\\\":10547,\\\"id\\\":\\\"408adf3bc1e8fa9247f3e415d34868e9\\\"}]}\",\"say\":\"相关新闻如下\",\"type\":\"satisfy\"}],\"qu_res\":{\"qu_res_chosen\":\"\",\"candidates\":[],\"sentiment_analysis\":{\"pval\":0.0,\"label\":\"\"},\"lexical_analysis\":[],\"raw_query\":\"\",\"status\":0,\"timestamp\":0}},{\"status\":0,\"msg\":\"ok\",\"origin\":\"1079258\",\"schema\":{\"intent_confidence\":100.0,\"slots\":[],\"domain_confidence\":0.0,\"slu_tags\":[],\"intent\":\"BUILT_IAQ\"},\"action_list\":[{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"build_iaq_satisfy\",\"confidence\":100.0,\"custom_reply\":\"\",\"say\":\"新闻，也叫消息，是指通过报纸、电台、广播、电视台等媒体途径所传播信息的一种称谓。是记录社会、传播信息、反映时代的一种文体。新闻概念有广义与狭义之分，就其广义而言，除了发表于报刊、广播、互联网、电视上的评论与专文外的常用文本都属于新闻之列，包括消息、通讯、特写、速写(有的将速写纳入特写之列)等等，狭义的新闻则专指消息，消息是用概括的叙述方式，以较简明扼要的文字，迅速及时地报道国内外新近发生的、有价值的事实，让别人了解。每则新闻一般包括标题、导语、主体、背景和结语五部分。前三者是主要部分，后二者是辅助部分。写法上主要是叙述，有时兼有议论、描写、评论等。新闻是包含海量资讯的新闻服务平台，真实反映每时每刻的重要事件。可以通过查看新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展。\",\"type\":\"satisfy\"}],\"qu_res\":{\"qu_res_chosen\":\"\",\"candidates\":[],\"sentiment_analysis\":{\"pval\":0.0,\"label\":\"\"},\"lexical_analysis\":[],\"raw_query\":\"\",\"status\":0,\"timestamp\":0}},{\"status\":0,\"msg\":\"ok\",\"origin\":\"1079260\",\"schema\":{\"intent_confidence\":1.0,\"slots\":[],\"domain_confidence\":0.0,\"slu_tags\":[],\"intent\":\"BUILT_CHAT\"},\"action_list\":[{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"chat_satisfy\",\"confidence\":-1.9570262432,\"custom_reply\":\"\",\"say\":\"我想知道,你是怎么知道的\",\"type\":\"chat\"},{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"chat_satisfy\",\"confidence\":-2.2510545254,\"custom_reply\":\"\",\"say\":\"这是啥?你在哪儿看的?\",\"type\":\"chat\"},{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"chat_satisfy\",\"confidence\":-2.4598817825,\"custom_reply\":\"\",\"say\":\"这是啥?你在哪儿呢?\",\"type\":\"chat\"}],\"qu_res\":{\"qu_res_chosen\":\"\",\"candidates\":[],\"sentiment_analysis\":{\"pval\":0.0,\"label\":\"\"},\"lexical_analysis\":[],\"raw_query\":\"\",\"status\":0,\"timestamp\":0}},{\"status\":0,\"msg\":\"ok\",\"origin\":\"1079259\",\"schema\":{\"intent_confidence\":0.0,\"slots\":[{\"word_type\":\"\",\"fuzzy_matches\":[],\"confidence\":100.0,\"name\":\"user_loc\",\"length\":2,\"original_word\":\"北京\",\"sub_slots\":[],\"session_offset\":1,\"begin\":0,\"normalized_word\":\"北京\",\"merge_method\":\"add\"}],\"domain_confidence\":0.0,\"slu_tags\":[],\"intent\":\"\"},\"action_list\":[{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"fail_action\",\"confidence\":100.0,\"custom_reply\":\"\",\"say\":\"我不知道应该怎么答复您。\",\"type\":\"failure\"}],\"qu_res\":{\"qu_res_chosen\":\"\",\"candidates\":[],\"sentiment_analysis\":{\"pval\":0.998,\"label\":\"1\"},\"lexical_analysis\":[{\"etypes\":[],\"basic_word\":[\"新闻\"],\"weight\":1.0,\"term\":\"新闻\",\"type\":\"21\"}],\"raw_query\":\"新闻\",\"status\":0,\"timestamp\":0}},{\"status\":0,\"msg\":\"ok\",\"origin\":\"1079257\",\"schema\":{\"intent_confidence\":0.0,\"slots\":[],\"domain_confidence\":0.0,\"slu_tags\":[],\"intent\":\"\"},\"action_list\":[{\"refine_detail\":{\"option_list\":[],\"interact\":\"\",\"clarify_reason\":\"\"},\"action_id\":\"fail_action\",\"confidence\":100.0,\"custom_reply\":\"\",\"say\":\"我不知道应该怎么答复您。\",\"type\":\"failure\"}],\"qu_res\":{\"qu_res_chosen\":\"\",\"candidates\":[],\"sentiment_analysis\":{\"pval\":0.0,\"label\":\"\"},\"lexical_analysis\":[],\"raw_query\":\"\",\"status\":0,\"timestamp\":0}}],\"dialog_state\":{\"contexts\":{\"SYS_PRESUMED_HIST\":[\"新闻\",\"我想知道,你是怎么知道的\"]},\"skill_states\":{}}},\"error_code\":0}";
        System.out.println(dialogService.getAnswer(response, "liuzhaolu"));
    }
}
