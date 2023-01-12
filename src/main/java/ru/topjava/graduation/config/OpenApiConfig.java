package ru.topjava.graduation.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//https://sabljakovich.medium.com/adding-basic-auth-authorization-option-to-openapi-swagger-documentation-java-spring-95abbede27e9
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@OpenAPIDefinition(
        info = @Info(
                title = "REST API documentation",
                version = "1.0",
                description = """
                        <H3>Voting system for deciding where to have lunch.</H3><br>
                        <p>2 types of users: admin and regular users<br>
                        Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)<br>
                        Menu changes each day (admins do the updates)<br>
                        Users can vote for a restaurant they want to have lunch at today<br>
                        Only one vote counted per user<br>
                        If user votes again the same day:<br>
                        If it is before 11:00 we assume that he changed his mind.<br>
                        If it is after 11:00 then it is too late, vote can't be changed<br>
                        Each restaurant provides a new menu each day.</p><br>
                        <p><b>Тестовые данные user / password:</b><br>
                        - user@yandex.ru / user<br>
                        - admin@gmail.com / admin<br>
                        - user2@yandex.ru / user2<br>
                        - user3@yandex.ru / user3<br>
                        - user4@yandex.ru / user4<br>
                        - user5@yandex.ru / user5</p>
                        """,
                contact = @Contact(url = "https://github.com/Pugachev85/Topjava-graduation", name = "Ivan Pugachev", email = "workpost2009@mail.ru")
        ),
        security = @SecurityRequirement(name = "basicAuth")
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("REST API")
                .pathsToMatch("/api/**")
                .build();
    }
}
