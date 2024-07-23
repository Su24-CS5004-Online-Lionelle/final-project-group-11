package student.model;
import java.util.Objects;

public class FreeGameItem {
    private final int id;
    private final String title;
    private final String thumbnail;
    private final String shortDescription;
    private final String gameUrl;
    private final String genre;
    private final String platform;
    private final String publisher;
    private final String developer;
    private final String releaseDate;
    private final String freetogameProfileUrl;

    public FreeGameItem(int id, String title, String thumbnail, String shortDescription, String gameUrl,
                        String genre, String platform, String publisher, String developer, String releaseDate,
                        String freetogameProfileUrl) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.shortDescription = shortDescription;
        this.gameUrl = gameUrl;
        this.genre = genre;
        this.platform = platform;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.freetogameProfileUrl = freetogameProfileUrl;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getThumbnail() { return thumbnail; }
    public String getShortDescription() { return shortDescription; }
    public String getGameUrl() { return gameUrl; }
    public String getGenre() { return genre; }
    public String getPlatform() { return platform; }
    public String getPublisher() { return publisher; }
    public String getDeveloper() { return developer; }
    public String getReleaseDate() { return releaseDate; }
    public String getFreetogameProfileUrl() { return freetogameProfileUrl; }

    @Override
    public String toString() {
        return "FreeGameItem{" + "id=" + id + ", title='" + title + '\'' + ", thumbnail='" + thumbnail + '\''
                + ", shortDescription='" + shortDescription + '\'' + ", gameUrl='" + gameUrl + '\''
                + ", genre='" + genre + '\'' + ", platform='" + platform + '\'' + ", publisher='"
                + publisher + '\'' + ", developer='" + developer + '\'' + ", releaseDate='"
                + releaseDate + '\'' + ", freetogameProfileUrl='" + freetogameProfileUrl + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeGameItem that = (FreeGameItem) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(thumbnail, that.thumbnail)
                && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(gameUrl, that.gameUrl)
                && Objects.equals(genre, that.genre) && Objects.equals(platform, that.platform)
                && Objects.equals(publisher, that.publisher) && Objects.equals(developer, that.developer)
                && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(freetogameProfileUrl, that.freetogameProfileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, thumbnail, shortDescription, gameUrl, genre, platform, publisher, developer, releaseDate, freetogameProfileUrl);
    }
}
