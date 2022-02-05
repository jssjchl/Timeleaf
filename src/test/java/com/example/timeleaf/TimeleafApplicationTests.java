package com.example.timeleaf;

import com.example.timeleaf.domain.Board;
import com.example.timeleaf.domain.User;
import com.example.timeleaf.domain.enums.BoardType;
import com.example.timeleaf.repository.BoardRepository;
import com.example.timeleaf.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
//이 두가지의 역할이 뭘까


//@SpringBootTest
@DataJpaTest
//Jpa관련 테스트설정만 로드하는 어노테이션
//내장형 데이터베이스를 사용하여 실사용 데이터베이스를 사용하지 않고 테스트하는 장점이 있다.
@ExtendWith(SpringExtension.class)
class TimeleafApplicationTests {
    
    @Autowired
    protected UserRepository userRepository;
    
    @Autowired
    protected BoardRepository boardRepository;
    
    private final String title ="제목";
    private final String email ="jssjchl@naver.con";
    
    @BeforeEach// 궁금하다 
    //test를 실행할 때마다 실행해준다. test메서드 실행 이전에 수행(매번)
    //BeforeAll같은 경우는 모든 test메서드 실행하기 전에 한 번만 실행
    //AfterEach같은 경우는 모든 test메서드 실행 이후에 수행(매번)
    //AfterAll같은 경우는 모든 test메서드 실행 이후에 한 번만 수행
    public void init(){
        User user = userRepository.save(User.builder()
                .name("jaeseok")
                .password("1111")
                .email(email)
                .build());
        boardRepository.save(Board.builder()
                .title(title)
                .subTitle("부제목")
                .content("컨텐츠")
                .boardType(BoardType.notice)
                .user(user)
                .build());
    }
    @Test
    //기본적으로 test 메서드로 인식하고 테스트한다.
    void contextLoads() {
        User user = userRepository.findByEmail(email);
        assertThat(user.getName(), is("jaeseok"));
        assertThat(user.getPassword(), is("1111"));
        assertThat(user.getEmail(), is(email));
        
        Board board=boardRepository.findByUser(user);
        assertThat(board.getTitle(), is(title));
        assertThat(board.getSubTitle(), is("부제목"));
        assertThat(board.getContent(), is("컨텐츠"));
        assertThat(board.getBoardType(), is(BoardType.notice));

    }

}
