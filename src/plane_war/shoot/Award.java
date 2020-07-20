package plane_war.shoot;

import plane_war.shoot.PlayerPlane;

public interface Award {
    public static final int AWARD_OF_BLOOD = 1;
    public static final int AWARD_OF_FIRE = 2;
    public abstract void award(PlayerPlane playerPlane);

}
