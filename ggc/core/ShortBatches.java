package ggc.core;

import java.util.Comparator;

public class ShortBatches implements Comparator<Batch> {

    @Override
    public int compare(Batch o1, Batch o2) {
        if(o1.getProduct().getId().compareTo(o2.getProduct().getId()) != 0){
            return (o1.getProduct().getId()).compareTo(o2.getProduct().getId());
        }
        else if(o1.getPartner().getId().compareTo(o2.getPartner().getId()) != 0){
            return o1.getPartner().getId().compareTo(o2.getPartner().getId());
        }
        else if(o1.getPrice().compareTo(o2.getPrice()) !=0) { 
            return o1.getPrice().compareTo(o2.getPrice());
        }
       return o1.getStock() - o2.getStock();
    }
    
}
