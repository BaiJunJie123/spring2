package serverCloudApi.servers.fallbackFactorys;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import serverCloudApi.entitys.User;
import serverCloudApi.servers.Userservice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白俊杰
 * @Date 2019/11/18
 * @Description
 **/
@Component
public class UserServiceFactory implements FallbackFactory<Userservice> {
    @Override
    public Userservice create(Throwable throwable) {
        return new Userservice() {
            @Override
            public String datasource() {
                return null;
            }

            @Override
            public List<User> findList() {
                List<User>  all = new ArrayList<>();
                User uu = new User();
                uu.setDbSource("服务降级");
                uu.setName("服务熔断");
                uu.setPass("服务熔断");
                uu.setId(0);
                all.add(uu);
                return all;
            }
        };
    }
}
