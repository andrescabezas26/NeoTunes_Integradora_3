package model;

import java.time.LocalDate;

public class Bill {
    private Audio song;
    private LocalDate saleDate;
    private String idBuyer;

    public Bill(Audio song, String idBuyer) {
        this.song = song;
        this.saleDate = LocalDate.now();
        this.idBuyer=idBuyer;
    }
    
}
