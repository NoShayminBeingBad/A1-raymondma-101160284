package org.example.Card;

public abstract class AdventureCard extends Card implements Comparable<AdventureCard>{

    protected int bp;

    public AdventureCard(String title, int bp) {
        super(title);
        this.bp = bp;
    }

    public int getBP(){
        return bp;
    }

    public String toString(){
        return this.title.toUpperCase().substring(0,1).concat(Integer.toString(bp)) ;
    }

    @Override
    public int compareTo(AdventureCard card) {

        if (card.getTitle().charAt(0) == 'F' && this.getTitle().charAt(0) != card.getTitle().charAt(0)){
            return 1;
        }else if(this.bp == card.getBP()){
            return card.getTitle().charAt(0) - this.title.charAt(0);
        }

        return this.bp - card.getBP();
    }

}
