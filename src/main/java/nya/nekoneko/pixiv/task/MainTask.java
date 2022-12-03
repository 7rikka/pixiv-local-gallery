package nya.nekoneko.pixiv.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import nya.nekoneko.pixiv.client.PixivClient;
import nya.nekoneko.pixiv.mapper.*;
import nya.nekoneko.pixiv.model.illust.*;
import nya.nekoneko.pixiv.util.Call;
import nya.nekoneko.pixiv.util.PixivRequestFactory;
import okhttp3.Request;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.snack.ONode;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.extend.schedule.IJob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
//@Component
public class MainTask implements IJob {
    @Inject
    private PixivClient client;
    @Db
    private IllustMapper illustMapper;
    @Db
    private ToolMapper toolMapper;
    @Db
    private ImageUrlMapper imageUrlMapper;
    @Db
    private TagMapper tagMapper;
    @Db
    private TagRelationMapper tagRelationMapper;
    @Db
    private UserMapper userMapper;

    @Override
    public int getInterval() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void exec() {
        Call.setProxyProvider(() -> {
            Request request = PixivRequestFactory
                    .getPixivRequest()
                    .url("http://localhost:5010/get?type=http")
                    .get()
                    .buildRequest();
            String s = Call.doCallGetString(request);
            return ONode.loadStr(s).get("proxy").getString();
            //
        });
        Map<String, Tag> map = new HashMap<>();
        //读取tag
        List<Tag> tags1 = tagMapper.selectList(null);
        for (Tag tag : tags1) {
            map.put(tag.getName(), tag);
        }
        for (int i = 9950509; i >= 0; i--) {
            try {
                Illust illustIdDetail = client.getIllustDetail(i);
                Integer illustId = illustIdDetail.getId();
                log.info("{} - {}", i, illustIdDetail);
                //查询插画是否存在
                LambdaQueryWrapper<Illust> wrapper2 = new LambdaQueryWrapper<>();
                wrapper2.eq(Illust::getId, illustId);
                Illust illust = illustMapper.selectOne(wrapper2);
                if (null == illust) {
                    //新增
                    illustMapper.insert(illustIdDetail);
                } else {
                    //更新
                    if (illustIdDetail.getState() == 0) {
                        //当前是正常的，正常更新
                        illustMapper.updateById(illustIdDetail);
                    } else {
                        if (illust.getState() == 0) {
                            //原来是正常的
                            Illust build = Illust.builder()
                                    .id(illustId)
                                    .state(-2)
                                    .build();
                            illustMapper.updateById(build);
                        } else {
                            //原来不正常
                            illustMapper.updateById(illustIdDetail);
                        }
                    }
                }
                if (illustIdDetail.getState() != 0) {
                    continue;
                }
//                处理tool
                List<String> tools = illustIdDetail.getTools();
                for (String tool : tools) {
                    Tool tool1 = Tool.builder()
                            .illustId(illustId)
                            .name(tool)
                            .build();
                    log.info("tool:");
                    log.info(tool1.toString());
//                            toolMapper.insert(tool1);
                    LambdaQueryWrapper<Tool> wrapper = new LambdaQueryWrapper<>();
                    wrapper.eq(Tool::getIllustId, illustId);
                    wrapper.eq(Tool::getName, tool);
                    Long aLong = toolMapper.selectCount(wrapper);
                    if (0 == aLong) {
                        //新增
                        toolMapper.insert(tool1);
                    }

                }
                /**/
                //图片信息
                if (1 == illustIdDetail.getPageCount()) {
                    //单页
                    ImageUrl imageUrl = illustIdDetail.getImageUrls();
                    imageUrl.setIllustId(illustId);
                    imageUrl.setIndex(0);
                    imageUrl.setOriginal(illustIdDetail.getMetaSinglePage().getOriginalImageUrl());
                    System.out.println("保存单页：==========");
                    System.out.println(imageUrl);
//                    imageUrlMapper.insert(imageUrls);
                    LambdaQueryWrapper<ImageUrl> wrapper = new LambdaQueryWrapper<>();
                    wrapper.eq(ImageUrl::getIllustId, illustId);
                    wrapper.eq(ImageUrl::getIndex, 0);
                    if (0 == imageUrlMapper.selectCount(wrapper)) {
                        //新增
                        imageUrlMapper.insert(imageUrl);
                    } else {
                        //更新
                        imageUrlMapper.update(imageUrl, wrapper);
                    }
                } else {
                    List<MetaPage> metaPages = illustIdDetail.getMetaPages();
                    int index = 0;
                    System.out.println("保存多页：==========");
                    for (MetaPage metaPage : metaPages) {
                        ImageUrl imageUrl = metaPage.getImageUrls();
                        imageUrl.setIllustId(illustId);
                        imageUrl.setIndex(index);
                        System.out.println(imageUrl);
//                        imageUrlMapper.insert(imageUrls);
                        LambdaQueryWrapper<ImageUrl> wrapper = new LambdaQueryWrapper<>();
                        wrapper.eq(ImageUrl::getIllustId, illustId);
                        wrapper.eq(ImageUrl::getIndex, index);
                        if (0 == imageUrlMapper.selectCount(wrapper)) {
                            //新增
                            imageUrlMapper.insert(imageUrl);
                        } else {
                            //更新
                            imageUrlMapper.update(imageUrl, wrapper);
                        }
                        index++;
                    }
                }
                //保存tag
                List<Tag> tags = illustIdDetail.getTags();
                System.out.println("tag:===========");
                for (Tag tag : tags) {
                    //保存tag
                    String name = tag.getName();
                    if (!map.containsKey(name)) {
                        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
                        wrapper.eq(Tag::getName, name);
                        if (0 == tagMapper.selectCount(wrapper)) {
                            //新增
                            log.info("新增tag: {}", name);
                            int insert = tagMapper.insert(tag);
                            log.info("新增tag 结果: {}", insert);
                            Tag tag1 = tagMapper.selectOne(wrapper);
                            log.info("返回tag信息: {}", insert);
                            map.put(tag1.getName(), tag1);
                        }
                    }
                    //保存tag关联
                    log.info("查找tag: {}", name);
//                    log.info("map:{}", map);
                    Integer tagId = map.get(name).getId();
                    LambdaQueryWrapper<TagRelation> wrapper = new LambdaQueryWrapper<>();
                    wrapper.eq(TagRelation::getTag_id, tagId);
                    wrapper.eq(TagRelation::getIllust_id, illustId);
                    if (0 == tagRelationMapper.selectCount(wrapper)) {
                        TagRelation tagRelation = TagRelation.builder()
                                .tag_id(tagId)
                                .illust_id(illustId)
                                .build();
                        tagRelationMapper.insert(tagRelation);
                    }

                }
                //保存用户
                User user = illustIdDetail.getUser();
                LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(User::getId, user.getId());
                User user1 = userMapper.selectOne(wrapper);
                if (null == user1) {
                    //新增
                    userMapper.insert(user);
                } else {
                    userMapper.updateById(user);
                }

            } catch (Exception e) {
                log.error("错误：", e);
                System.exit(0);
            }
            try {
                Thread.sleep(5 * 1000);
            } catch (Exception e) {
                log.error("错误：", e);
            }
        }
    }
}
