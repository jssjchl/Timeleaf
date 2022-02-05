package com.example.timeleaf;

import com.example.timeleaf.domain.Board;
import com.example.timeleaf.domain.User;
import com.example.timeleaf.domain.enums.BoardType;
import com.example.timeleaf.repository.BoardRepository;
import com.example.timeleaf.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.stream.IntStream;

@EnableJpaAuditing // ??
@SpringBootApplication
public class TimeleafApplication {
    /*메인함수
     *
     * @param args
     * */
    public static void main(String[] args) {
        SpringApplication.run(TimeleafApplication.class, args);
    }

    /*
     * 어플리케이션 구동시 테스트 데이터 생성
     *
     *
     * @param userRepository
     * @param boardRepository
     * @return
     * @throws Exection
     *
     * */
    @Bean
    public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) throws Exception{
        return(args) -> {
            User user = userRepository.save(User.builder()
                    .name("jaeseok")
                    .password("1111")
                    .email("jssjchl@naver.com")
                    .build());

            IntStream.rangeClosed(1,200).forEach(index ->
                    boardRepository.save(Board.builder()
                            .title(String.format("제목 %s", index))
                            .subTitle(String.format("부제목 %s", index))
                            .content(String.format("내용 %s", index))
                            .boardType(BoardType.notice)
                            .user(user)
                            .build())
            );
        };
    }

}

