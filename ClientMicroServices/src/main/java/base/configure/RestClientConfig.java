package base.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        return RestClient
                .builder()
                .baseUrl("http://localhost:8889/api/employee")
                .build();
    }
}
