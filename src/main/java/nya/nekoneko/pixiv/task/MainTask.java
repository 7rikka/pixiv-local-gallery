package nya.nekoneko.pixiv.task;

import lombok.extern.slf4j.Slf4j;
import nya.nekoneko.pixiv.client.PixivClient;
import nya.nekoneko.pixiv.mapper.IllustMapper;
import nya.nekoneko.pixiv.mapper.TagMapper;
import nya.nekoneko.pixiv.model.*;
import nya.nekoneko.pixiv.service.*;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.extend.schedule.IJob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ho
 */
@Slf4j
@Component
public class MainTask implements IJob {
    @Inject
    private PixivClient client;
    @Db
    private TagMapper tagMapper;
    @Inject
    private TagService tagService;
    @Inject
    private IllustService illustService;
    @Inject
    private ToolService toolService;
    @Db
    private IllustMapper illustMapper;
    @Inject
    private TagRelationService tagRelationService;
    @Inject
    private ImageUrlDetailService imageUrlDetailService;
    @Inject
    private UserService userService;
    @Inject
    private SeriesService seriesService;

    @Override
    public int getInterval() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void exec() {
        log.info("获取Tag信息");
        Map<String, Tag> tagMap = new HashMap<>();
        List<Tag> tags = tagMapper.selectList(null);
        for (Tag tag : tags) {
            tagMap.put(tag.getName(), tag);
        }
        log.info("获取到Tag信息共 {} 条", tagMap.size());
        for (int i = 100000000; i >= 99999000; i--) {
            try {
                log.info("获取插画信息, id: {}", i);
                Illust illustDetail1 = client.getIllustDetail(i);
                Illust illustDetail2 = client.getIllustDetailWeb(i);
                List<Illust> illustList = List.of(illustDetail1, illustDetail2);
                for (Illust illustDetail : illustList) {
                    log.info("state: {}", illustDetail.getState());
                    if (0 == illustDetail.getState()) {
                        //正常 直接保存
                        illustService.save(illustDetail);
                    } else {
                        //当前失效
                        Illust illust = illustMapper.selectById(i);
                        if (null == illust) {
                            //新增保存
                            illustService.save(illustDetail);
                        } else {
                            if (illust.getState() == 0) {
                                //原来是正常的
                                //更新保存
                                Illust build = Illust.builder().id(i).state(-2).build();
                                illustMapper.updateById(build);
                            } else {
                                //原来是非正常
                                Illust build = Illust.builder().id(i).build();
                                illustMapper.updateById(build);
                            }
                        }
                    }
                    //处理其他信息
//                    if (0 == illustDetail.getState()) {
                    //处理tool
                    List<String> tools = illustDetail.getTools();
                    if (null != tools) {
                        for (String tool : tools) {
                            toolService.save(Tool.builder().illustId(i).name(tool).build());
                        }
                    }

                    //图片信息
                    List<ImageUrl> urls = illustDetail.getUrls();
                    if (null != urls) {
                        for (ImageUrl url : urls) {
                            imageUrlDetailService.save(url.getThumbMini());
//                        imageUrlDetailService.save(url.getThumb());
//                        imageUrlDetailService.save(url.getSmall());
                            imageUrlDetailService.save(url.getRegular());
                            imageUrlDetailService.save(url.getSquareMedium());
                            imageUrlDetailService.save(url.getMedium());
                            imageUrlDetailService.save(url.getLarge());
                            imageUrlDetailService.save(url.getOriginal());
                        }
                    }

                    //Tag
                    List<Tag> tags1 = illustDetail.getTags();
                    if (null != tags1) {
                        for (Tag tag : tags1) {
                            //保存Tag
                            if (!tagMap.containsKey(tag.getName())) {
                                tagService.save(tag);
                                Tag tag1 = tagService.get(tag.getName());
                                log.info("获取Tag: {}", tag1);
                                tagMap.put(tag1.getName(), tag1);
                            }
                            //保存Tag关联
                            Tag tag1 = tagMap.get(tag.getName());
                            if (null == tag1) {
                                log.info("手动获取Tag: {}", tag.getName());
                                tag1 = tagService.get(tag.getName());
                            }
                            tagRelationService.save(TagRelation.builder().illustId(i).tagId(tag1.getId()).build());
                        }
                    }
                    //用户
                    userService.save(illustDetail.getUser());
                    //系列
                    seriesService.save(illustDetail.getSeries());
//                    }
                }

            } catch (Exception e) {
                log.error("错误：", e);
                System.exit(0);
            }
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                log.error("错误：", e);
            }
        }
    }
}
