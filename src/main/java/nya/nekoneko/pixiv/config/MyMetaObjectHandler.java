package nya.nekoneko.pixiv.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * Mybatis-plus字段自动填充
 *
 * @author Ho
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 通用字段：创建时间
     */
    private static final String CREATE_TIME = "createTime";
    /**
     * 通用字段：更新时间
     */
    private static final String UPDATE_TIME = "updateTime";

    /**
     * 新增时填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        strictInsertFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * 更新时填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }
}