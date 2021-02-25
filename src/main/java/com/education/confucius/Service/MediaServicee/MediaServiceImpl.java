package com.education.confucius.Service.MediaServicee;

import com.education.confucius.Dao.MediaMapper;
import com.education.confucius.Entity.Media.Media;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/25 下午4:34
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Service
public class MediaServiceImpl implements MediaService {
    @Resource
    public MediaMapper mediaMapper;

    @Override
    public Boolean publicMedia(Media media) {
        media.setMediaId(UUID.randomUUID().toString().replace("-", ""));
        return mediaMapper.publicMedia(media);
    }

    @Override
    public List<Media> getMediaList() {
        return mediaMapper.getMediaList();
    }
}
