package model.zombier;

public class ZombieKlokke implements Runnable{
    Zombie zombie;
    long hurtighet;
    
    public ZombieKlokke(Zombie z, long h){
        zombie = z;
        hurtighet = h;
    }

    @Override
    public void run() {
        boolean ikkeEnden = true;
        while (ikkeEnden && zombie.hentHelse() > 0){
            try {Thread.sleep(hurtighet);} catch (Exception e){System.out.println(e);}
            ikkeEnden = zombie.beveg();
        }
    }
    
}

