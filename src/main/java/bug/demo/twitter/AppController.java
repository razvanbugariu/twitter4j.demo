package bug.demo.twitter;

import bug.demo.twitter.controllers.TweetService;
import bug.demo.twitter.pojos.Tweet;
import bug.demo.twitter.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter4j.Status;

import java.net.MalformedURLException;

@RestController
public class AppController {

    private final TweetService tweetService;
    private final FileDownloadService fileDownloadService;

    @Autowired
    public AppController(TweetService tweetService, FileDownloadService fileDownloadService) {
        this.tweetService = tweetService;
        this.fileDownloadService = fileDownloadService;
    }

    @PostMapping(value = "/tweet")
    public Long postTweet(@RequestBody Tweet tweet) {
        return tweetService.postSimpleTweet(tweet.getContent()).map(Status::getId).orElse(-1L);
    }

    @PostMapping(value = "/tweetImage")
    public Long image() {
        return tweetService.postPorcTvLogoFromResources().map(Status::getId).orElse(-1L);
    }

    @PostMapping(value = "/downloadAndTweet")
    public Long image(@RequestBody Tweet tweet) throws MalformedURLException {
        return tweetService.postTweetWithImageUrl(tweet).map(Status::getId).orElse(-1L);
    }

    @DeleteMapping(value = "/tweet/{tweetId}")
    public void deleteTweet(@PathVariable Long tweetId) {
        tweetService.deleteTweet(tweetId);
    }
}
