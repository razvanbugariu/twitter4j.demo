package bug.demo.twitter.controllers;

import bug.demo.twitter.pojos.Tweet;
import bug.demo.twitter.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Optional;

@Service
public class TweetService {

    private final Twitter twitter;
    private final FileDownloadService fileDownloadService;

    @Autowired
    public TweetService(Twitter twitter, FileDownloadService fileDownloadService) {
        this.twitter = twitter;
        this.fileDownloadService = fileDownloadService;
    }

    public Optional<Status> postSimpleTweet(String content) {
        try {
            Status status = twitter.updateStatus(content);
            return Optional.of(status);
        } catch (TwitterException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        return Optional.empty();
    }

    public Optional<Status> postPorcTvLogoFromResources() {
        try {
            StatusUpdate statusUpdate = new StatusUpdate("This is tweet with photo");
            File file = ResourceUtils.getFile("classpath:porc.png");
            statusUpdate.setMedia(file);
            return Optional.of(twitter.updateStatus(statusUpdate));
        } catch (TwitterException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Status> postTweetWithImageUrl(Tweet tweet) {
        try {
            String fileName = fileDownloadService.donwloadFile(tweet.getImageUrl());
            StatusUpdate statusUpdate = new StatusUpdate(tweet.getContent());
            File file = new File(fileName);
            statusUpdate.setMedia(file);
            Optional<Status> status = Optional.of(twitter.updateStatus(statusUpdate));
            boolean deleted = fileDownloadService.moveFileToClassPath(fileName);
            if(deleted) {
                return status;
            }
        } catch (MalformedURLException | TwitterException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void deleteTweet(Long id) {
        try {
            twitter.destroyStatus(id);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
