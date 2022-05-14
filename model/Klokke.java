package model;

public class Klokke implements Runnable{
    Kule kulen;
    
    public Klokke(Kule k){
        kulen = k;
    }

    @Override
    public void run() {
        boolean ikkeEnden = true;
        while (ikkeEnden && !kulen.sjekkTreff()){
            try {Thread.sleep(50);} catch (Exception e){System.out.println(e);}
            ikkeEnden = kulen.beveg();
        }
    }
    
}
