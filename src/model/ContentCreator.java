package model;

public class ContentCreator extends Producer implements Comparable<ContentCreator> {

    public ContentCreator(String nickName, String id, String imageUrl, String name) {
        super(nickName, id, imageUrl, name);
    }

    /**
     * compareTo= Compares the playbacks of this contentCreator with other one
     * 
     * @param contentCreator: ContentCreator =The contentCreator that will be
     *                        compareted
     * @return int = 1 or 0 if the other contentCreator has more playbacks than
     *         this, or -1 if this has more
     */
    @Override
    public int compareTo(ContentCreator contentCreator) {
        if (contentCreator.getPlaybacks() > super.getPlaybacks()) {
            return 1;
        } else if (contentCreator.getPlaybacks() > super.getPlaybacks()) {
            return 0;
        } else {
            return -1;
        }
    }

}