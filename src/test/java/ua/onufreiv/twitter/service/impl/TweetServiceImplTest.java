package ua.onufreiv.twitter.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.service.TweetService;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Yurii_Onufreiv on 19-Apr-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration("classpath:repos.xml"),
        @ContextConfiguration("classpath:services.xml"),
        @ContextConfiguration("file:src/main/webapp/WEB-INF/webContext.xml")
})
public class TweetServiceImplTest {
    @Autowired
    private TweetService tweetService;

    public TweetServiceImplTest() {
    }

    @Test
    public void testGetTweetById() throws Exception{
        Long id = 1L;
        Optional<Tweet> res = tweetService.findById(id);
        assertNotNull(res.orElse(null));
    }

}