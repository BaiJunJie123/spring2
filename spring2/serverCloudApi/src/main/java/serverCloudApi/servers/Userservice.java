package serverCloudApi.servers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import serverCloudApi.entitys.User;
import serverCloudApi.servers.fallbackFactorys.UserServiceFactory;

import java.util.List;

/**
 * @author 白俊杰
 * @Date 2019/11/18
 * @Description
 **/
@FeignClient(value = "server-provider-user",fallbackFactory = UserServiceFactory.class)
public interface Userservice {
    //阿里链接池界面
    @RequestMapping("/druid/datasource")
    public String datasource();

    @RequestMapping("/findList")
    public List<User> findList();
}
