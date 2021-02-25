package com.education.confucius.Controller;

import com.education.confucius.Entity.Media.Media;
import com.education.confucius.Service.MediaServicee.MediaService;
import com.pangu.Http.response.RestResult;
import com.pangu.Http.response.ResultEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/25 下午4:30
 * @desc 设备控制类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@RestController
@RequestMapping("/media")
public class MediaController {

    @Resource
    public MediaService mediaService;
    /**
     * 上传视频媒体
     * @param media
     * @return
     */
    @RequestMapping("/publicMedia")
    public RestResult publicMedia(@RequestBody Media media){
        if(StringUtils.isBlank(media.getResource())){
            return RestResult.failResult(ResultEnum.PARAM_EMPTY);
        }
        return mediaService.publicMedia(media) ? RestResult.successResult() : RestResult.failResult("public media error");
    }

    @RequestMapping("/getMediaList")
    public RestResult getMediaList(){
        return RestResult.successResult(mediaService.getMediaList());
    }

}
