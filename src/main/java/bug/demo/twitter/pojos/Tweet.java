package bug.demo.twitter.pojos;

public class Tweet {
    private String content;
    private String imageUrl;
    private Long id;

    public Tweet(String content, String imageUrl, Long id) {
        this.content = content;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public Tweet() {
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
