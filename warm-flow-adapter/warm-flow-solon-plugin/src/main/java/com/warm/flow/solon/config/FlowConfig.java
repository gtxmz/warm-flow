package com.warm.flow.solon.config;

import com.warm.flow.core.FlowFactory;
import com.warm.flow.core.handler.DataFillHandlerImpl;
import com.warm.flow.core.mapper.*;
import com.warm.flow.core.service.*;
import com.warm.flow.core.service.impl.*;
import com.warm.mybatis.core.handler.DataFillHandlerFactory;
import com.warm.mybatis.core.invoker.MapperInvoker;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * @author minliuhua
 * @description: 工作流bean注册配置
 * @date: 2023/6/5 23:01
 */
@Configuration
public class FlowConfig {

    @Bean
    public DefService definitionService() {
        return new DefServiceImpl();
    }

    @Bean
    public NodeService nodeService() {
        return new NodeServiceImpl();
    }

    @Bean
    public SkipService skipService() {
        return new SkipServiceImpl();
    }

    @Bean
    public InsService instanceService() {
        return new InsServiceImpl();
    }

    @Bean
    public TaskService taskService() {
        return new TaskServiceImpl();
    }

    @Bean
    public HisTaskService hisTaskService() {
        return new HisTaskServiceImpl();
    }

    @Bean
    public FlowFactory initFlowServer(DefService definitionService, HisTaskService hisTaskService
            , InsService instanceService, NodeService nodeService, SkipService skipService
            , TaskService taskService, @Db("db1") org.apache.ibatis.session.Configuration db1Cfg) {

        DataFillHandlerFactory.set(new DataFillHandlerImpl());

        // List<String> mapperList = Arrays.asList("warm/flow/FlowDefinitionMapper.xml", "warm/flow/FlowHisTaskMapper.xml"
        //         , "warm/flow/FlowInstanceMapper.xml", "warm/flow/FlowNodeMapper.xml"
        //         , "warm/flow/FlowSkipMapper.xml", "warm/flow/FlowTaskMapper.xml");
        // db1Cfg.addMapper(FlowDefinitionMapper.class);
        // db1Cfg.addMapper(FlowHisTaskMapper.class);
        // db1Cfg.addMapper(FlowInstanceMapper.class);
        // db1Cfg.addMapper(FlowNodeMapper.class);
        // db1Cfg.addMapper(FlowSkipMapper.class);
        // db1Cfg.addMapper(FlowTaskMapper.class);
        // try {
        //     for (String mapper : mapperList) {
        //         XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(Resources.getResourceAsStream(mapper),
        //                 db1Cfg, getClass().getResource("/") + mapper, db1Cfg.getSqlFragments());
        //         xmlMapperBuilder.parse();
        //     }
        //
        // } catch (Exception e) {
        //     throw new RuntimeException(e);
        // }

        MapperInvoker.setMapperFunction(Solon.context()::getBean);
        return new FlowFactory(definitionService, hisTaskService, instanceService
                , nodeService, skipService, taskService);
    }

}
