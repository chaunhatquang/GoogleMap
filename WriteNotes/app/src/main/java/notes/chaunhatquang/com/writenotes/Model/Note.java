package notes.chaunhatquang.com.writenotes.Model;

/**
 * Created by Quang Nhat on 11/9/2016.
 */

public class Note {
    private long id;
    private String title;
    private String content;

    public Note(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public Note setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Note setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Note setContent(String content) {
        this.content = content;
        return this;
    }
}
