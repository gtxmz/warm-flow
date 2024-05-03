package com.warm.flow.orm.mapper;

import com.warm.tools.utils.page.Page;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * BaseMapper接口
 *
 * @author warm
 * @date 2023-03-17
 */
public interface WarmMapper<T> {

    /**
     * 根据id查询
     *
     * @param id 主键
     * @param entity 查询实体列表
     * @return 实体
     */
    T selectById(@Param("id") Serializable id, @Param("entity") T entity);

    /**
     * 根据ids查询
     *
     * @param ids 主键
     * @param entity 查询实体列表
     * @return 实体
     */
    List<T> selectByIds(@Param("ids") Collection<? extends Serializable> ids, @Param("entity") T entity);

    /**
     * 分页查询
     *
     * @param entity 实体列表
     * @return 集合
     */
    List<T> selectList(@Param("entity") T entity, @Param("page") Page<T> page, @Param("order") String order);

    /**
     * 查询数量
     *
     * @param entity 实体列表
     * @return 集合
     */
    long selectCount(@Param("entity") T entity);

    /**
     * 新增
     *
     * @param entity 实体
     * @return 结果
     */
    int insert(T entity);

    /**
     * 根据id修改
     *
     * @param entity 实体
     * @return 结果
     */
    int updateById(T entity);

    /**
     * 根据entity删除
     *
     * @param entity 实体
     * @return 结果
     */
    int delete(T entity);

    /**
     * 逻辑删除
     *
     * @param entity 实体
     * @return 结果
     */
    int updateLogic(@Param("entity") T entity, @Param("logicDeleteValue") String logicDeleteValue
            , @Param("logicNotDeleteValue") String logicNotDeleteValue);

    /**
     * 根据id删除
     *
     * @param id 主键
     * @param entity 查询实体列表
     * @return 结果
     */
    int deleteById(@Param("id") Serializable id, @Param("entity") T entity);

    /**
     * 逻辑删除
     *
     * @param id 主键
     * @return 结果
     */
    int updateByIdLogic(@Param("id") Serializable id, @Param("entity") T entity, @Param("logicDeleteValue") String logicDeleteValue
            , @Param("logicNotDeleteValue") String logicNotDeleteValue);

    /**
     * 根据ids批量删除
     *
     * @param ids 需要删除的数据主键集合
     * @param entity 查询实体列表
     * @return 结果
     */
    int deleteByIds(@Param("ids") Collection<? extends Serializable> ids, @Param("entity") T entity);

    /**
     * 逻辑删除
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int updateByIdsLogic(@Param("ids") Collection<? extends Serializable> ids, @Param("entity") T entity
            , @Param("logicDeleteValue") String logicDeleteValue
            , @Param("logicNotDeleteValue") String logicNotDeleteValue);

}
