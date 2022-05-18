package model.vaapen;
import model.ammunisjon.*;

public interface Vaapen {
    public void skyt();
    public void fjernKule(Kule k);
    public void oppgraderVaapen();
    public int hentSkade();
    public int hentKostnadForOppgradering();
}
