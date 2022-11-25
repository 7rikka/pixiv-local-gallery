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
        System.out.println("tag map size:");
        System.out.println(map.size());

        for (int i = 10000000; i > 0; i--) {
            try {
                Illust illustIdDetail = client.getIllustIdDetail(i);
                Integer id = illustIdDetail.getId();
                log.info("{} - {}", i, illustIdDetail);
//                illustMapper.insert(illustIdDetail);
                if (0 != illustIdDetail.getState()) {
                    continue;
                }
                //处理tool
                List<String> tools = illustIdDetail.getTools();
                for (String tool : tools) {
                    Tool tool1 = Tool.builder()
                            .illustId(id)
                            .name(tool)
                            .build();
                    System.out.println("tool:");
                    System.out.println(tool1);
//                            toolMapper.insert(tool1);
                }
                //图片信息
                if (1 == illustIdDetail.getPageCount()) {
                    //单页
                    ImageUrl imageUrls = illustIdDetail.getImageUrls();
                    imageUrls.setId(id);
                    imageUrls.setIndex(0);
                    imageUrls.setOriginal(illustIdDetail.getMetaSinglePage().getOriginalImageUrl());
                    System.out.println("保存单页：==========");
                    System.out.println(imageUrls);
//                    imageUrlMapper.insert(imageUrls);
                } else {
                    List<MetaPage> metaPages = illustIdDetail.getMetaPages();
                    int index = 0;
                    System.out.println("保存多页：==========");
                    for (MetaPage metaPage : metaPages) {
                        ImageUrl imageUrls = metaPage.getImageUrls();
                        imageUrls.setId(id);
                        imageUrls.setIndex(index);
                        System.out.println(imageUrls);
//                        imageUrlMapper.insert(imageUrls);
                        index++;
                    }
                }
                //保存tag
                List<Tag> tags = illustIdDetail.getTags();
                System.out.println("tag:===========");
                for (Tag tag : tags) {
                    String name = tag.getName();
                    if (!map.containsKey(name)) {
                        System.out.println(tag);
//                        tagMapper.insert(tag);
                        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
                        wrapper.eq(Tag::getName, name);
                        Tag tag1 = tagMapper.selectOne(wrapper);
                        map.put(tag1.getName(), tag1);
                    }
                    //保存tag关联
                    TagRelation tagRelation = TagRelation.builder()
                            .tag_id(map.get(name).getId())
                            .illust_id(id)
                            .build();
                    tagRelationMapper.insert(tagRelation);
                }

            } catch (Exception e) {
                log.error("错误：", e);
            }
            System.exit(0);
            try {
                Thread.sleep(5 * 1000);
            } catch (Exception e) {
                log.error("错误：", e);
            }

        }
    }
}
