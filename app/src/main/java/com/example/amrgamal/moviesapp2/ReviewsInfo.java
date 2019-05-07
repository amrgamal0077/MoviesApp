package com.example.amrgamal.moviesapp2;

/**
 * Created by AmrGamal on 17/02/2019.
 */

@SuppressWarnings("ALL")
class ReviewsInfo {
    private String author;
    private String content;
    public ReviewsInfo(String author,String content)
    {
        this.author=author;
        this.content=content;

    }

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//    public void setAuthor(String author) {
//        this.author = author;
//    }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//    public void setContent(String content) {
//        this.content = content;
//    }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
